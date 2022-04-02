package ch.keepcalm.axon.wallet

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition
import org.axonframework.eventsourcing.SnapshotTriggerDefinition
import org.axonframework.eventsourcing.Snapshotter
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class AxonSnapshotConfig (private val snapshotThresholdConfigurer: SnapshotThresholdConfigurer) {


    @Bean
    fun mySnapshotTriggerDefinition(snapshotter: Snapshotter?): SnapshotTriggerDefinition {
        return EventCountSnapshotTriggerDefinition(snapshotter, snapshotThresholdConfigurer.snapshotThreshold)
    }
}
