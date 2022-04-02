package ch.keepcalm.axon.wallet

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties

/**
 * Configures a snapshot trigger to create a Snapshot every 5 events.
 * 250 is an arbitrary number used only for testing purposes just to show how the snapshots are stored on Mongo as well.
 */
@ConstructorBinding
@EnableConfigurationProperties(SnapshotThresholdConfigurer::class)
@ConfigurationProperties("axon.aggregate")
data class SnapshotThresholdConfigurer(var snapshotThreshold: Int = 250)
