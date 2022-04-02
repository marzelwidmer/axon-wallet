package ch.keepcalm.axon.wallet.query

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

//import javax.persistence.Entity
//import javax.persistence.Id

//@Entity
@Document
data class WalletView(
        @Id val walletId: String? = null,
        val balance: Int? = null
)

