package ch.oronk.service

import ch.oronk.model.Movie
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
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
        val url = URL(BASE_URL + DISCOVER_API_URL + API_KEY_PART + LANGUAGE_KEY + LANGUAGE + SORT_PART)
        var returnValue:List<Movie> = emptyList()
        var con = url.openConnection()
        if (con is HttpURLConnection) {
            con.requestMethod = "GET"
            println(con.responseCode)
            val message = readInput(con.inputStream)
            println(message)
            println()
            val mapper = ObjectMapper()
            var jsonNode: JsonNode = mapper.readTree(message)
            returnValue = jsonToMovie(jsonNode)
        }
        return returnValue
    }

    private fun readInput(inputStream: InputStream): String? {
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

    private fun jsonToMovie(json: JsonNode?): List<Movie> {
        var movieList = ArrayList<Movie>()
        var results = json?.get("results")
        if (results != null) {
            for (node in results) {
                val name: String? = node.get("title").asText()
                val id: Int? = node.get("id").asInt()
                val description: String? = node.get("overview").asText()
                val img: String? = node.get("poster_path").asText()
                if (name != null && id != null && description != null && img != null) {
                    movieList.add(Movie(id, name, description, img))
                }
            }
        }
        return movieList
    }

}