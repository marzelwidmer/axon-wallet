package ch.keepcalm.axon.wallet.commandmodel

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.GetMapping
import java.util.UUID
import ch.keepcalm.axon.wallet.coreapi.CreateWalletCommand
import org.axonframework.commandhandling.callbacks.LoggingCallback
import ch.keepcalm.axon.wallet.coreapi.DepositCashCommand
import ch.keepcalm.axon.wallet.coreapi.WithdrawCashCommand

@RestController
@RequestMapping("/command")
class WalletCommandController(private val commandGateway: CommandGateway) {

    @GetMapping
    fun createWallet() {
        val walletId = UUID.randomUUID().toString()
        commandGateway.send(CreateWalletCommand(walletId, 1000), LoggingCallback.INSTANCE)

        commandGateway.send(DepositCashCommand(walletId, 42), LoggingCallback.INSTANCE)
        commandGateway.send(DepositCashCommand(walletId, 42), LoggingCallback.INSTANCE)


        commandGateway.send(DepositCashCommand(walletId, 42), LoggingCallback.INSTANCE)
        commandGateway.send(DepositCashCommand(walletId, 42), LoggingCallback.INSTANCE)
        commandGateway.send(DepositCashCommand(walletId, 42), LoggingCallback.INSTANCE)
        commandGateway.send(DepositCashCommand(walletId, 42), LoggingCallback.INSTANCE)
        commandGateway.send(DepositCashCommand(walletId, 42), LoggingCallback.INSTANCE)

        commandGateway.send(WithdrawCashCommand(walletId, 84), LoggingCallback.INSTANCE)

    //        commandGateway.send(new WithdrawCashCommand(walletId, 1337), LoggingCallback.INSTANCE);

    }
}
