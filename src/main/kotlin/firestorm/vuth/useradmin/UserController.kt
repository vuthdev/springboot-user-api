package firestorm.vuth.useradmin

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
    fun addUser(@RequestBody user: User): ResponseEntity<Any> {
        userService.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(mapOf("success" to true))
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
    fun updateUser(@PathVariable id: Int, @RequestBody user: User): ResponseEntity<Any> {
        userService.updateUser(id, user)
        return ResponseEntity.ok(mapOf("success" to true))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteById(id))
    }
}