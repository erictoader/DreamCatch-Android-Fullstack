package com.erictoader.entity

import com.erictoader.response.model.UserModel
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "email")
    val email: String = "",
    @Column(name = "username")
    val username: String = "",
    @Column(name = "password")
    val password: String = "",
    @Column(name = "first_name")
    val firstName: String = "",
    @Column(name = "last_name")
    val lastName: String = ""
) : EntityMapper<UserModel> {

    override fun mapToModel() =
        UserModel(
            id = this.id,
            email = this.email,
            username = this.username,
            password = this.password,
            firstName = this.firstName,
            lastName = this.lastName
        )
}
