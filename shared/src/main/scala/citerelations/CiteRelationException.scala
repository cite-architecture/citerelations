package edu.holycross.shot
package citerelation {

  case class CiteRelationException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
    cause.foreach(initCause)
  }

}
