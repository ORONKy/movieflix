package ch.oronk.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieJsonWraper(@Json(name="results") val list: List<Movie>)
