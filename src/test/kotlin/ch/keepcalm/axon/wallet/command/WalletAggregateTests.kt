package ch.keepcalm.axon.wallet.command

import ch.keepcalm.axon.wallet.common.CashDepositedEvent
import ch.keepcalm.axon.wallet.common.WalletCreatedEvent
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.Test
import java.util.*

class WalletAggregateTests {

    // Initialize aggregate test
    private val fixture: AggregateTestFixture<WalletAggregate> by lazy {
        AggregateTestFixture(WalletAggregate::class.java)
    }

    @Test
    fun `Create wallet command will expect a WalletCreatedEvent and expect successful handler execution`() {
        val walletId = UUID.randomUUID().toString()
        fixture
            .`when`(CreateWalletCommand(walletId = walletId, balance = 0))
            .expectEvents(WalletCreatedEvent(walletId = walletId, balance = 0))
    }

    @Test
    fun `Wallet with a balance of 0, will expect 100 amount when DepositCashCommand of 100`() {
        val walletId = UUID.randomUUID().toString()
        fixture
            .given(WalletCreatedEvent(walletId = walletId, balance = 0))
            .`when`(DepositCashCommand(walletId = walletId, amount = 100))
            .expectSuccessfulHandlerExecution()
            .expectEvents(CashDepositedEvent(walletId = walletId, amount = 100))
    }

    @Test
    fun `Expect 'NotEnoughFundsException' when withdraw more cache then balance is`() {
        val walletId = UUID.randomUUID().toString()
        fixture
            .given(WalletCreatedEvent(walletId = walletId, balance = 100))
            .`when`(WithdrawCashCommand(walletId = walletId, amount = 101))
            .expectException(NotEnoughFundsException::class.java)
    }

}
