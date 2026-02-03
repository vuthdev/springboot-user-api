package firestorm.vuth.useradmin.repository

import firestorm.vuth.useradmin.model.User
import jakarta.annotation.PostConstruct
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class UserRepository(private val jdbcTemplate: JdbcTemplate) {

    @PostConstruct
    fun initDB() {
        jdbcTemplate.execute(
            """
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(255) UNIQUE NOT NULL,
                    fullname VARCHAR(255) NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            """.trimIndent()
        )
    }

    private val rowMapper = RowMapper<User> { rs, _ ->
        User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("fullname"),
            rs.getString("password"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        )
    }

    fun save(user: User): User {
        jdbcTemplate.update("""
            INSERT INTO users (username, fullname, password) VALUES (?, ?, ?)
        """.trimIndent(), user.username, user.fullname, user.password)
        return user
    }

    fun update(user: User): User {
        jdbcTemplate.update("""
            UPDATE users SET username = ?, fullname = ?, password = ? WHERE id = ?
        """.trimIndent(), user.username, user.fullname, user.password, user.id)
        return user
    }

    fun findById(id: Int): User? =
        jdbcTemplate.query("SELECT * FROM users WHERE id=?;", rowMapper, id).firstOrNull()

    fun findAllUsers(): List<User> =
        jdbcTemplate.query("SELECT * FROM users", rowMapper)

    fun deleteUser(id: Int) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id)
    }
}