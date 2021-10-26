package ch.oronk

import ch.oronk.service.MovieService
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder

fun SchemaBuilder.movieSchema() {
    /*query("movies"){
        resolver { id: Int ->
            listOf(Movie(1, "Hans", "ursula", "http://"), Movie(1, "Hans", "ursula", "http://"))
        }
    }*/
    query("movies") {
        resolver { group: Int ->
                MovieService.getMoviesForGroup(group)
        }


    }
}