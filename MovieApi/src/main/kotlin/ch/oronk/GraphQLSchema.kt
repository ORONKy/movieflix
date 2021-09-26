package ch.oronk

import ch.oronk.model.Movie
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder

fun SchemaBuilder.movieSchema(){
    query("movies"){
        resolver { id: Int ->
            listOf(Movie(1, "Hans", "ursula", "http://"), Movie(1, "Hans", "ursula", "http://"))
        }
    }
}