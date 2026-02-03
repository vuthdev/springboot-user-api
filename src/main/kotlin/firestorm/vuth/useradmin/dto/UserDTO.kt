package firestorm.vuth.useradmin.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserDTO(
    @NotBlank(message = "Name is required")
    @Size(min = 6, max = 100, message = "Name must be between 1 and 100")
    var username: String,

    @NotBlank(message = "Full name is required")
    @Size(min = 6, max = 100, message = "Name must be between 1 and 100")
    var fullname: String,

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    var password: String,
)