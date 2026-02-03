package firestorm.vuth.useradmin.controller

import firestorm.vuth.useradmin.dto.UserDTO
import firestorm.vuth.useradmin.model.User
import firestorm.vuth.useradmin.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @PostMapping
    fun addUser(@Valid @RequestBody user: UserDTO): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user))
    }

    @GetMapping
    fun getUsers(): ResponseEntity<List<User>> {
        val users = userService.getAll()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserById(id))
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @Valid @RequestBody user: UserDTO): ResponseEntity<User> {
        return ResponseEntity.ok(userService.updateUser(id, user))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteById(id))
    }
}