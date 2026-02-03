package firestorm.vuth.useradmin

import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException) {
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to ex.message))
    }
}