package com.erictoader.entity

import com.erictoader.response.model.DreamModel
import jakarta.persistence.*

@Entity
@Table(name = "dream")
data class Dream(
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "user_id")
    val userId: Int? = null,
    @Column(name = "description")
    val description: String = "",
    @Column(name = "tags")
    val tags: String = "",
    @Column(name = "sleep_duration")
    val sleepDuration: Float = Float.NaN,
    @Column(name = "energy_level")
    val energyLevel: Float = Float.NaN,
    @Column(name = "stress_level")
    val stressLevel: Float = Float.NaN,
    @Column(name = "sleep_score")
    val sleepScore: Float? = null,
    @Column(name = "entry_date")
    val entryDate: Long? = null,
) : EntityMapper<DreamModel> {

    override fun mapToModel() =
        DreamModel(
            description = this.description,
            sleepDuration = this.sleepDuration,
            energyLevel = this.energyLevel,
            stressLevel = this.stressLevel,
            sleepScore = this.sleepScore ?: Float.MIN_VALUE,
            entryDate = this.entryDate ?: Long.MIN_VALUE,
        )
}
