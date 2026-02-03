package firestorm.vuth.useradmin.model

import java.time.LocalDateTime

data class User(
    val id: Int? = null,
    var username: String,
    var fullname: String,
    var password: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)