package ch.keepcalm.axon.wallet.common

data class WalletCreatedEvent(val walletId: String, val balance: Int)
data class CashWithdrawnEvent(val walletId: String, val amount: Int)
data class CashDepositedEvent(val walletId: String, val amount: Int)

