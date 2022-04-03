package ch.keepcalm.axon.wallet.command

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime

data class CreateWalletCommand(val walletId: String, val balance: Int, val date: LocalDateTime)
data class DepositCashCommand(@TargetAggregateIdentifier val walletId: String, val amount: Int, val date: LocalDateTime)
data class WithdrawCashCommand(@TargetAggregateIdentifier val walletId: String, val amount: Int, val date: LocalDateTime)
