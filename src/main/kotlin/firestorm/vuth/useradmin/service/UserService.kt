package firestorm.vuth.useradmin.service

import firestorm.vuth.useradmin.dto.UserDTO
import firestorm.vuth.useradmin.model.User
import firestorm.vuth.useradmin.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun createUser(user: UserDTO) {

        val newUser = User(
            username = user.username,
            fullname = user.fullname,
            password = user.password
        )

        userRepository.save(newUser)
    }
    fun updateUser(id: Int, newUser: UserDTO): User {
        val user = userRepository.findById(id) ?: throw RuntimeException("User with id $id not found")

        user.username = newUser.username
        user.fullname = newUser.fullname
        user.password = newUser.password

        return userRepository.update(user)
    }
    fun getAll(): List<User> = userRepository.findAllUsers()
    fun getUserById(id: Int) = userRepository.findById(id)
    fun deleteById(id: Int) = userRepository.deleteUser(id)
}