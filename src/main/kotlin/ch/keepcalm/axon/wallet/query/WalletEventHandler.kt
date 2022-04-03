package ch.keepcalm.axon.wallet.query

import ch.keepcalm.axon.wallet.common.CashDepositedEvent
import ch.keepcalm.axon.wallet.common.CashWithdrawnEvent
import ch.keepcalm.axon.wallet.common.WalletCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class WalletEventHandler(private val walletViewRepository: WalletViewRepository) {

    @EventHandler
    fun on(event: WalletCreatedEvent) {
        walletViewRepository.save(WalletView(event.walletId, event.balance, date = event.date))
    }


    @EventHandler
    fun handle(event: CashWithdrawnEvent){
        val result = walletViewRepository.findById(event.walletId).get()
        val balance = result.balance?.minus(event.amount)
        walletViewRepository.save(
            WalletView(walletId = event.walletId, balance= balance, date = event.date)
        )
    }


    @EventHandler
    fun handle(event: CashDepositedEvent){
        val result = walletViewRepository.findById(event.walletId).get()
        val balance = result.balance?.plus(event.amount)
        walletViewRepository.save(
            WalletView(walletId = event.walletId, balance= balance, date = event.date)
        )
    }



    @QueryHandler
    fun answer(query: FindAllWalletsQuery?): List<WalletView> {
        return walletViewRepository.findAll()
    }
}
