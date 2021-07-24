package pe.santillan.rappi.data.rest.model

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @field:SerializedName("success") val isSuccess: Boolean,
    @field:SerializedName("expires_at") val expiresAt: String,
    @field:SerializedName("request_token") val token: String,
)