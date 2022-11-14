package com.limacor.plugins

import com.limacor.routes.userRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Welcome to Ktor, Lian!")
        }
        userRouting()
    }
}
