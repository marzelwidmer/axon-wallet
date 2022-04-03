package ch.keepcalm.axon.wallet.common

import java.time.LocalDateTime

data class WalletCreatedEvent(val walletId: String, val balance: Int, val date: LocalDateTime)
data class CashWithdrawnEvent(val walletId: String, val amount: Int, val date: LocalDateTime)
data class CashDepositedEvent(val walletId: String, val amount: Int, val date: LocalDateTime)

