package com.erictoader.entity

import com.erictoader.response.model.SystemTagModel
import jakarta.persistence.*

@Entity
@Table(name = "system_tag")
data class SystemTag(
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "name")
    val name: String = "",
) : EntityMapper<SystemTagModel> {

    override fun mapToModel() =
        SystemTagModel(
            name = name
        )
}
