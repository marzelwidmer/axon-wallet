package ch.keepcalm.axon.wallet.querymodel

import ch.keepcalm.axon.wallet.coreapi.CashWithdrawnEvent
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


    @EventHandler
    fun handle(event: CashWithdrawnEvent){
        val result = walletViewRepository.findById(event.walletId).get()
        val balance = result.balance?.minus(event.amount)
        walletViewRepository.save(
            WalletView(walletId = event.walletId, balance= balance)
        )
    }

    @QueryHandler
    fun answer(query: FindAllWalletsQuery?): List<WalletView> {
        return walletViewRepository.findAll()
    }
}
