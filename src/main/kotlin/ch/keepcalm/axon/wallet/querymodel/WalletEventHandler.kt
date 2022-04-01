package ch.keepcalm.axon.wallet.querymodel

import ch.keepcalm.axon.wallet.coreapi.FindAllWalletsQuery
import ch.keepcalm.axon.wallet.coreapi.WalletCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class WalletEventHandler(private val walletViewRepository: WalletViewRepository) {

    @EventHandler
    fun on(event: WalletCreatedEvent) {
        walletViewRepository.save(WalletView(event.walletId, event.balance))
    }

    @QueryHandler
    fun answer(query: FindAllWalletsQuery?): List<WalletView> {
        return walletViewRepository.findAll()
    }
}
