package ch.keepcalm.axon.wallet.command

import org.axonframework.test.aggregate.AggregateTestFixture


class WalletAggegateTests {

    private val fixture: AggregateTestFixture<WalletAggregate> by lazy {
        AggregateTestFixture(WalletAggregate::class.java).apply {
            // Initialize aggregate test
        }
    }
}
