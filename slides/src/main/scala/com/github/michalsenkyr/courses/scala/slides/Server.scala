package com.github.michalsenkyr.courses.scala.slides

import akka.actor.ActorSystem
import spray.routing.SimpleRoutingApp

import scala.util.Try

/**
  * @author Michal Šenkýř
  */
object Server extends App with SimpleRoutingApp {
  implicit val system = ActorSystem()

  startServer(interface = "localhost", port = Try(args(0)).map(_.toInt).getOrElse(8000)) {
    pathSingleSlash {
      get {
        compressResponse() {
          getFromResource("web/index.html")
        }
      }
    } ~ get {
      compressResponse() {
        getFromResourceDirectory("web")
      }
    }
  }
}
