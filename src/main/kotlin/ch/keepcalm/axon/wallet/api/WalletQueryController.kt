package ch.keepcalm.axon.wallet.api

import ch.keepcalm.axon.wallet.query.FindAllWalletsQuery
import ch.keepcalm.axon.wallet.query.WalletView
import kotlinx.coroutines.reactive.awaitSingle
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/query")
class WalletQueryController(private val queryGateway: QueryGateway) {

    @GetMapping("/wallets", produces = [MediaTypes.HAL_JSON_VALUE])
    suspend fun getAllWallets(): CollectionModel<EntityModel<WalletView>> {
        val result = queryGateway.query(FindAllWalletsQuery(), ResponseTypes.multipleInstancesOf(WalletView::class.java)).get()
        return CollectionModel.wrap(result.asIterable())
            .add(linkTo(methodOn(IndexResource::class.java).index()).withRel(IanaLinkRelations.PREV).toMono().awaitSingle())
            .add(linkTo(methodOn(WalletQueryController::class.java).getAllWallets()).withSelfRel().toMono().awaitSingle())
    }
}

