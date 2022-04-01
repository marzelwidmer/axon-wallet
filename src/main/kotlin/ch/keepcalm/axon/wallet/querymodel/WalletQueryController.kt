package ch.keepcalm.axon.wallet.querymodel

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import java.util.concurrent.CompletableFuture
import ch.keepcalm.axon.wallet.querymodel.WalletView
import ch.keepcalm.axon.wallet.coreapi.FindAllWalletsQuery
import org.axonframework.messaging.responsetypes.ResponseTypes

@RestController
@RequestMapping("/query")
class WalletQueryController(private val queryGateway: QueryGateway) {

    @GetMapping("/wallets")
    fun wallets(): CompletableFuture<List<WalletView>> {
        return queryGateway.query(FindAllWalletsQuery(), ResponseTypes.multipleInstancesOf(WalletView::class.java))
    }
}
