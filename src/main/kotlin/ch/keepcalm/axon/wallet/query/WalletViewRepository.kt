package ch.keepcalm.axon.wallet.query

import org.springframework.data.mongodb.repository.MongoRepository

interface WalletViewRepository : MongoRepository<WalletView, String>
