package pe.santillan.rappi.data.rest.model

import com.google.gson.annotations.SerializedName

data class SessionDto(
    @field:SerializedName("success") val isSuccess: Boolean,
    @field:SerializedName("session_id") val session: String,
)
