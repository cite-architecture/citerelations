package edu.holycross.shot.citerelation

import edu.holycross.shot.cite._


import scala.io.Source
import scala.collection.mutable.ArrayBuffer

import scala.scalajs.js
import js.annotation.JSExport


@JSExport case class CiteTriple(urn1: Urn, relation: Cite2Urn, urn2: Urn) {

  /** True if "subject" matches a given URN (CTS or CITE2 URN).
  *
  * @param urn URN to match.
  */
  def urn1Match (urn: Urn): Boolean = {
    urn match {
      case cts: CtsUrn => {
        urn1 match {
          case cts: CtsUrn =>  cts ~~ urn1
          case _ => false
        }

      }
      case c2: Cite2Urn =>  {
        urn1 match {
          case cite: Cite2Urn => c2 ~~ urn1
          case _ => false
        }
      }
    }
  }


  /** True if "object" matches a given URN (CTS or CITE2 URN).
  *
  * @param urn URN to match.
  */
  def urn2Match (urn: Urn): Boolean = {
    urn match {
      case cts: CtsUrn => {
        urn2 match {
          case cts: CtsUrn =>  cts ~~ urn2
          case _ => false
        }

      }
      case c2: Cite2Urn =>  {
        urn2 match {
          case cite: Cite2Urn => c2 ~~ urn2
          case _ => false
        }
      }
    }
  }


  /** True if either "verb" URN matches a
  * a given URN.
  *
  * @param urn URN to match.
  */
  def verbMatch (verb: Cite2Urn): Boolean = {
    relation ~~ verb
  }

  /** True if either "subject" or "object" URN match a
  * a given URN.
  *
  * @param urn URN to match.
  */
  def ~~(urn: Urn): Boolean = {
    (urn1Match(urn) || urn2Match(urn))
  }

}
