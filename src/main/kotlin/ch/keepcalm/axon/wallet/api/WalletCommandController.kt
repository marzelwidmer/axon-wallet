package ch.keepcalm.axon.wallet.api

import ch.keepcalm.axon.wallet.command.CreateWalletCommand
import ch.keepcalm.axon.wallet.command.DepositCashCommand
import ch.keepcalm.axon.wallet.command.WithdrawCashCommand
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import java.util.UUID
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.hateoas.MediaTypes
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/command")
class WalletCommandController(private val commandGateway: ReactorCommandGateway) {

    @GetMapping(value = ["/create"], produces = [MediaTypes.HAL_JSON_VALUE])
    suspend fun createWallet() : HttpEntity<Any> {
        val walletId = UUID.randomUUID().toString()
        commandGateway.send<Any>(CreateWalletCommand(walletId, 1000)).awaitSingleOrNull()
        commandGateway.send<Any>(WithdrawCashCommand(walletId, 42)).awaitSingleOrNull()
        commandGateway.send<Any>(DepositCashCommand(walletId, 42)).awaitSingleOrNull()

        return ResponseEntity(HttpStatus.CREATED)

    }
}
