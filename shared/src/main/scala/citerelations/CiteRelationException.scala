package edu.holycross.shot
package citerelation {

import scala.scalajs.js
import scala.scalajs.js.annotation._

@JSExportAll  case class CiteRelationException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
    cause.foreach(initCause)
  }

}
