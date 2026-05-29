package org.heartimaging.echoindications.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String,
    val user: User
) {
    @Serializable
    data class User(
        val id: Int,
        val username: String,
        val name: String? = null,
        val email: String? = null
    )
}

@Serializable
data class ApiErrorEnvelope(val error: String, val status: Int? = null)
