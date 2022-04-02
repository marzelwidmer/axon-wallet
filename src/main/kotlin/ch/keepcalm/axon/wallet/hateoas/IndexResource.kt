package ch.keepcalm.axon.wallet.hateoas

import ch.keepcalm.axon.wallet.commandmodel.WalletCommandController
import ch.keepcalm.axon.wallet.querymodel.WalletQueryController
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn
import org.springframework.hateoas.support.WebStack
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class IndexResource() {

    companion object REL {
        const val REL_QUERY = "query"
        const val REL_COMMAND = "command"
    }


    @GetMapping("/", produces = [MediaTypes.HAL_JSON_VALUE])
    suspend fun index(): EntityModel<Unit> {
        return EntityModel.of(Unit, linkTo(methodOn(IndexResource::class.java).index()).withSelfRel().toMono().awaitSingle())
            .add(linkTo(methodOn(WalletQueryController::class.java).getAllWallets()).withRel(REL_QUERY).toMono().awaitSingle())
            .add(linkTo(methodOn(WalletCommandController::class.java).createWallet()).withRel(REL_COMMAND).toMono().awaitSingle())
    }

}

