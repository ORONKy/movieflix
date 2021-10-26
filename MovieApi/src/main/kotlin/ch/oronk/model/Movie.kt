package ch.oronk.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val id: Int,
    @Json(name = "title") val name: String,
    @Json(name = "overview") val description: String,
    @Json(name = "backdrop_path") val img: String,
)
