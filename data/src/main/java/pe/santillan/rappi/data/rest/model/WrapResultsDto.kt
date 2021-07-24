package pe.santillan.rappi.data.rest.model

import com.google.gson.annotations.SerializedName

data class WrapResultsDto<T>(
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("total_results") val totalResults: Int,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("results") val results: List<T>,
)
