package pe.santillan.rappi.data.domain

data class Token(
    val isSuccess: Boolean,
    val expiresAt: String,
    val token: String,
)