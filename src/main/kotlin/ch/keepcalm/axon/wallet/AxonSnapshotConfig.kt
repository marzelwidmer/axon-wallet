package ch.keepcalm.axon.wallet

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition
import org.axonframework.eventsourcing.SnapshotTriggerDefinition
import org.axonframework.eventsourcing.Snapshotter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class AxonSnapshotConfig  {

    /**
     * Configures a snapshot trigger to create a Snapshot every 5 events.
     * 5 is an arbitrary number used only for testing purposes just to show how the snapshots are stored on Mongo as well.
     */
    @Bean
    fun mySnapshotTriggerDefinition(snapshotter: Snapshotter?,
                                    @Value("\${axon.aggregate.wallet.snapshot-threshold:5}") threshold: Int): SnapshotTriggerDefinition {
        return EventCountSnapshotTriggerDefinition(snapshotter, threshold)
    }
}
