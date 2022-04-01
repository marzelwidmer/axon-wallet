package ch.keepcalm.axon.wallet.querymodel

import org.springframework.data.mongodb.repository.MongoRepository

interface WalletViewRepository : MongoRepository<WalletView, String>
