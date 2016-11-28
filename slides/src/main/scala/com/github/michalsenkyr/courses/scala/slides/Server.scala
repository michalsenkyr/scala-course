package com.github.michalsenkyr.courses.scala.slides

import akka.actor.ActorSystem
import spray.routing.SimpleRoutingApp

/**
  * @author Michal Šenkýř
  */
object Server extends App with SimpleRoutingApp {
  implicit val system = ActorSystem()

  startServer(interface = "localhost", port = 8000) {
    get {
      compressResponse() {
        getFromResourceDirectory("web")
      }
    }
  }
}
