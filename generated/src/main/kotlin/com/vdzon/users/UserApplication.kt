package com.vdzon.users

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.server.html.*
import io.ktor.http.*
import io.ktor.request.receiveParameters
import io.ktor.routing.*
import kotlinx.html.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(CallLogging)
        module()
    }.start(wait = true)
}

fun Application.module() {

    val userService = UserService()

    routing {
        get("/") {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title("Usernames")
                    style {
                        unsafe {
                            +"body{font-family:Arial,sans-serif;}header{background-color:#4CAF50;color:white;text-align:center;padding:1em;}img{max-width:100%;height:auto;}form{margin-top:20px;}"
                        }
                    }
                }
                body {
                    header {
                        h1 { +"Usernames Management" }
                    }
                    div {
                        form(action = "/addUser", method = FormMethod.post) {
                            label { +"Username: " }
                            textInput(name = "username") { required = true }
                            submitInput { value = "Add User" }
                        }
                        h2 { +"Stored Usernames:" }
                        ul {
                            userService.getAllUsernames().forEach { username ->
                                li { 
                                    +username
                                    form(action = "/deleteUser", method = FormMethod.post) {
                                      hiddenInput(name="username") { value=username }
                                      submitInput { value = "Delete" }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        post("/addUser") {
            val params = call.receiveParameters()
            val username = params["username"] ?: ""
            if (username.isNotBlank()) {
                userService.addUsername(username)
                call.respondRedirect("/")
            } else {
                call.respond(HttpStatusCode.BadRequest, "Username cannot be blank")
            }
        }

        post("/deleteUser") {
            val params = call.receiveParameters()
            val username = params["username"] ?: ""
            userService.deleteUsername(username)
            call.respondRedirect("/")
        }
    }
}