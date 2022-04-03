package ch.keepcalm.axon.wallet.command

import ch.keepcalm.axon.wallet.common.CashDepositedEvent
import ch.keepcalm.axon.wallet.common.WalletCreatedEvent
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*


class WalletAggregateTests {

    // Initialize aggregate test
    private val fixture: AggregateTestFixture<WalletAggregate> by lazy {
        AggregateTestFixture(WalletAggregate::class.java)
    }

    @Test
    fun `Create wallet command will expect a WalletCreatedEvent and expect successful handler execution`() {
        val walletId = UUID.randomUUID().toString()
        val now = LocalDateTime.now()

        fixture
            .`when`(CreateWalletCommand(walletId = walletId, balance = 0, date = now))
            .expectEvents(WalletCreatedEvent(walletId = walletId, balance = 0, date = now))
    }

    @Test
    fun `Wallet with a balance of 0, will expect 100 amount when DepositCashCommand of 100`() {
        val walletId = UUID.randomUUID().toString()
        val now = LocalDateTime.now()

        fixture
            .given(WalletCreatedEvent(walletId = walletId, balance = 0, date = now))
            .`when`(DepositCashCommand(walletId = walletId, amount = 100, date = now))
            .expectSuccessfulHandlerExecution()
            .expectEvents(CashDepositedEvent(walletId = walletId, amount = 100, date = now))
    }

    @Test
    fun `Expect 'NotEnoughFundsException' when withdraw more cache then balance is`() {
        val walletId = UUID.randomUUID().toString()
        val now = LocalDateTime.now()
        fixture
            .given(WalletCreatedEvent(walletId = walletId, balance = 100, date = now))
            .`when`(WithdrawCashCommand(walletId = walletId, amount = 101, date = now))
            .expectException(NotEnoughFundsException::class.java)
    }

}
