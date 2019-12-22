package edu.holycross.shot.citerelation

import edu.holycross.shot.cite._


import scala.io.Source
import scala.collection.mutable.ArrayBuffer


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

import scala.scalajs.js
import scala.scalajs.js.annotation._


@JSExportAll case class CiteTriple(urn1: Urn, relation: Cite2Urn, urn2: Urn) {


  def cex(delimiter: String = "#"): String = {
    s"${urn1}${delimiter}${relation}${delimiter}${urn2}"
  }

  /** True if "subject" matches a given CTS URN.
  *
  * @param urn A CtsUrn to compare to the subject of this relation.
  */
  def ctsUrn1Match(urn: CtsUrn): Boolean = {
    urn1 match {
      case cts: CtsUrn => cts ~~ urn
      case _ => false
    }
  }


  /** True if "object" matches a given CTS URN.
  *
  * @param urn A CtsUrn to compare to the subject of this relation.
  */
  def ctsUrn2Match(urn: CtsUrn): Boolean = {
    urn2 match {
      case cts: CtsUrn => cts ~~ urn
      case _ => false
    }
  }

  /** True if "subject" matches a given CITE2 URN.
  *
  * @param urn A Cite2Urn to compare to the subject of this relation.
  */
  def citeUrn1Match(urn: Cite2Urn): Boolean = {
    urn1 match {
      case cite2: Cite2Urn => cite2 ~~ urn
      case _ => false
    }
  }

  /** True if "object" matches a given CITE2 URN.
  *
  * @param urn A Cite2Urn to compare to the subject of this relation.
  */
  def citeUrn2Match(urn: Cite2Urn): Boolean = {
    urn2 match {
      case cite2: Cite2Urn => cite2 ~~ urn
      case _ => false
    }
  }


  /** True if "subject" matches a given URN (CTS or CITE2 URN).
  *
  * @param urn URN to match.
  */
  def urn1Match (urn: Urn): Boolean = {
    urn match {
      case cts: CtsUrn => {
        ctsUrn1Match(cts)
      }
      case c2: Cite2Urn =>  {
        citeUrn1Match(c2)
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
        ctsUrn2Match(cts)
      }
      case c2: Cite2Urn =>  {
        citeUrn2Match(c2)
      }
    }
  }


  /** True if "verb" URN matches a
  * a given URN.
  *
  * @param urn URN to match.
  */
  def verb (verb: Cite2Urn): Boolean = {
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


/** Factory object for creating [[CiteTriple]]s from CEX source.
*/
object CiteTriple extends LogSupport {

  /** Create a CiteTriple from a single line of delimited text.
  *
  * @param cex CEX description of a single CITE relation.
  * @param delimiter Delimiting string separating S-V-O.
  */
  def apply(cex: String, delimiter: String = "#"): CiteTriple = {
    val columns = cex.split(delimiter)
    columns.size match {
      case 3 =>   CiteTriple(CiteRelationSet.urnFromString(columns(0)), Cite2Urn(columns(1)), CiteRelationSet.urnFromString(columns(2)))
      case _ => {
        val msg = s"Wrong number of columns in ${cex}"
        warn(msg)
        throw new CiteRelationException(msg)
      }
    }

  }
}
