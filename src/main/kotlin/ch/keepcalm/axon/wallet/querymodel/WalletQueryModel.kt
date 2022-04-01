package ch.keepcalm.axon.wallet.querymodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository

//import javax.persistence.Entity
//import javax.persistence.Id

//@Entity
@Document
data class WalletView(
        @Id val walletId: String? = null,
        val balance: Int? = null
)

