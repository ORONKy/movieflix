package ch.oronk

import com.apurebase.kgraphql.GraphQL
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(GraphQL) {
        playground = true
        schema {movieSchema()}
    }
    routing {
        get("/"){
            call.respondText("Hello server")
        }
    }
}