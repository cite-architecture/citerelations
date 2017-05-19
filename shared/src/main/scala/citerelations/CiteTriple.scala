package edu.holycross.shot.citerelation

import edu.holycross.shot.cite._


import scala.io.Source
import scala.collection.mutable.ArrayBuffer

case class CiteTriple(urn1: Urn, relation: Cite2Urn, urn2: Urn) {

  def urn1Match (urn: Urn) = {
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

    /*
    urn match {
      case cts: CtsUrn => cts.urnMatch(urn1)
      case c2: Cite2Urn =>  c2.urnMatch(urn1)
    }*/
  }

  def urn2Match (urn: Urn) = {
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

  def ~~(urn: Urn) = {
    (urn1Match(urn) || urn2Match(urn))
    /*
    urn match {
      case cts: CtsUrn => (urn1.urnMatch(cts) || urn2.urnMatch(cts))
      case c2: Cite2Urn =>  (urn1.urnMatch(c2) || urn2.urnMatch(c2))
    }*/
  }

}
