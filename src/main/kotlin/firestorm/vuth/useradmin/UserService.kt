package firestorm.vuth.useradmin

import firestorm.vuth.useradmin.User
import firestorm.vuth.useradmin.UserRepository
import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import kotlin.math.min

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun createUser(user: User): User {
        if (user.username.isBlank()) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Username required")
        if (user.username.length !in 6..100) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "username must be between 6 and 100")

        if (user.fullname.isBlank()) throw  ResponseStatusException(HttpStatus.BAD_REQUEST, "Full name required")
        if (user.fullname.length !in 6..100) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Full name must be between 6 and 100")

        if (user.password.isBlank()) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Password required")
        if (user.password.length < 6) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 6")

        return userRepository.save(user)
    }
    fun updateUser(id: Int, newUser: User): User {
        if (newUser.username.isBlank()) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be blank")
        if (newUser.username.length !in 6..100) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "username must be between 6 and 100")

        if (newUser.fullname.isBlank()) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Full name cannot be blank")
        if (newUser.fullname.length !in 6..100) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Full name must be between 6 and 100")

        if (newUser.password.isBlank()) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be blank")
        if (newUser.password.length < 6) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 6")
        val user = userRepository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with id $id not found")

        user.username = newUser.username
        user.fullname = newUser.fullname
        user.password = newUser.password

        return userRepository.update(user)
    }
    fun getAll(): List<User> = userRepository.findAllUsers()
    fun getUserById(id: Int) = userRepository.findById(id)
    fun deleteById(id: Int) = userRepository.deleteUser(id)
}