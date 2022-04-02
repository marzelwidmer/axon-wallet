package ch.keepcalm.axon.wallet.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateWalletCommand(val walletId: String, val balance: Int)
data class DepositCashCommand(@TargetAggregateIdentifier val walletId: String, val amount: Int)
data class WithdrawCashCommand(@TargetAggregateIdentifier val walletId: String, val amount: Int)
