package com.limacor.routes

import com.limacor.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val users = mutableListOf(
    User(1, "Lian Martinez", 26, "julian.itsa8@gmail.com"),
    User(2, "Kotlin Worudo", 99, "kotlin.worudo@testing.io")
)

fun Route.userRouting() {
    route("/user") {
        get {
            if (users.isNotEmpty()) {
                call.respond(users)
            } else {
                call.respondText("There is not users", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Id not found",
                status = HttpStatusCode.BadRequest
            )
            val user = users.find { it.id == id.toInt() } ?: return@get call.respondText(
                "User with id $id not found",
                status = HttpStatusCode.NotFound
            )
            call.respond(user)
        }
        post {
            val user = call.receive<User>()
            users.add(user)
            call.respondText(
                "User added successfully",
                status = HttpStatusCode.Created
            )
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Id not found",
                status = HttpStatusCode.BadRequest
            )
            if (users.removeIf { it.id == id.toInt()}) {
                call.respondText(
                    "User deleted successfully",
                    status = HttpStatusCode.Accepted
                )
            } else {
                call.respondText(
                    "User not found or already deleted",
                    status = HttpStatusCode.NotFound
                )
            }
        }
    }
}