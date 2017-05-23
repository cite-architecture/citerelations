package edu.holycross.shot
package citerelation {

import scala.scalajs.js
import js.annotation.JSExport

@JSExport  case class CiteRelationException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
    cause.foreach(initCause)
  }

}
