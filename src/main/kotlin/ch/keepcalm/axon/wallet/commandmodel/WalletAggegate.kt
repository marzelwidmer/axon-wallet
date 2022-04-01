package ch.keepcalm.axon.wallet.commandmodel

import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.commandhandling.CommandHandler
import ch.keepcalm.axon.wallet.coreapi.CreateWalletCommand
import org.axonframework.modelling.command.AggregateLifecycle
import ch.keepcalm.axon.wallet.coreapi.WalletCreatedEvent
import ch.keepcalm.axon.wallet.coreapi.DepositCashCommand
import ch.keepcalm.axon.wallet.coreapi.CashDepositedEvent
import kotlin.Throws
import ch.keepcalm.axon.wallet.coreapi.NotEnoughFundsException
import ch.keepcalm.axon.wallet.coreapi.WithdrawCashCommand
import ch.keepcalm.axon.wallet.coreapi.CashWithdrawnEvent
import mu.KLogging
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate

//@Aggregate
@Aggregate(snapshotTriggerDefinition = "mySnapshotTriggerDefinition")
class WalletAggegate {

    companion object : KLogging()

    @AggregateIdentifier
    private lateinit var walletId: String

    private var balance = 0

    constructor() {}

    @CommandHandler
    constructor(command: CreateWalletCommand) {
        AggregateLifecycle.apply(WalletCreatedEvent(command.walletId, command.balance))
    }
    @EventSourcingHandler
    fun on(event: WalletCreatedEvent) {
        walletId = event.walletId
        balance = event.balance
    }

    @CommandHandler
    fun handle(command: DepositCashCommand) {
        AggregateLifecycle.apply(CashDepositedEvent(walletId!!, command.amount))
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
        AggregateLifecycle.apply(CashWithdrawnEvent(walletId!!, amount))
    }
    @EventSourcingHandler
    fun on(event: CashWithdrawnEvent) {
        balance -= event.amount
    }
}
