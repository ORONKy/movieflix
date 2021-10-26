package ch.oronk.service

import ch.oronk.model.Movie
import ch.oronk.model.MovieJsonWraper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class TMDbService {
    private val BASE_URL = "https://api.themoviedb.org/3"
    private val DISCOVER_API_URL = "/discover/movie"
    private val API_KEY_PART = "?api_key=bfb47e605bfc752aafaf66ccf851c020"
    private val LANGUAGE_KEY = "&language="
    private val LANGUAGE = "en-US"
    private val SORT_PART = "&sort_by=popularity.desc"

    fun getRandomMovie(): List<Movie> {
        val url = URL(BASE_URL+DISCOVER_API_URL+API_KEY_PART+LANGUAGE_KEY+LANGUAGE+SORT_PART)

        var con = url.openConnection()
        if (con is HttpURLConnection) {
            con.requestMethod = "GET"
            println(con.responseCode)
            val message = readInput(con.inputStream)
            println(message)
            println()
            jsonToMovie(message)
        }
        return listOf()
    }

    private fun readInput(inputStream: InputStream): String?{
        val input = BufferedReader(
            InputStreamReader(inputStream)
        )
        var inputLine: String?
        val content = StringBuffer()
        while (input.readLine().also { inputLine = it } != null) {
            content.append(inputLine)
        }
        input.close()
        return content.toString()
    }

    private fun jsonToMovie(json: String?) :List<Movie> {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<MovieJsonWraper>(MovieJsonWraper::class.java)
        val movies = adapter.fromJson(json)
        println(movies)
        return movies?.list ?: listOf()
    }

}