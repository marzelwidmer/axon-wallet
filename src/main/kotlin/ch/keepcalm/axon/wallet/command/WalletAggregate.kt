package ch.keepcalm.axon.wallet.command

import ch.keepcalm.axon.wallet.common.CashDepositedEvent
import ch.keepcalm.axon.wallet.common.CashWithdrawnEvent
import ch.keepcalm.axon.wallet.common.WalletCreatedEvent
import mu.KLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate(snapshotTriggerDefinition = "mySnapshotTriggerDefinition")
class WalletAggregate {

    companion object : KLogging()

    @AggregateIdentifier
    private lateinit var walletId: String
    private var balance = 0

    constructor() {}


    @CommandHandler
    constructor(command: CreateWalletCommand) {
        AggregateLifecycle.apply(WalletCreatedEvent(command.walletId, command.balance, date = command.date))
    }

    @EventSourcingHandler
    fun on(event: WalletCreatedEvent) {
        walletId = event.walletId
        balance = event.balance
    }


    @CommandHandler
    fun handle(command: DepositCashCommand) {
        AggregateLifecycle.apply(CashDepositedEvent(walletId, command.amount, date = command.date))
    }

    @EventSourcingHandler
    fun on(event: CashDepositedEvent) {
        balance += event.amount
    }


    @CommandHandler
    @Throws(NotEnoughFundsException::class)
    fun handle(command: WithdrawCashCommand) {
        val amount = command.amount
        if (balance - amount < 0) {
            throw NotEnoughFundsException()
        }
        AggregateLifecycle.apply(CashWithdrawnEvent(walletId = walletId, amount = amount, date = command.date))
    }

    @EventSourcingHandler
    fun on(event: CashWithdrawnEvent) {
        balance -= event.amount
    }
}
