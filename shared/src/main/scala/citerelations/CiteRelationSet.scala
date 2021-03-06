package edu.holycross.shot.citerelation

import edu.holycross.shot.cite._
import edu.holycross.shot.cex._


import scala.io.Source
import scala.collection.mutable.ArrayBuffer


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter



import scala.scalajs.js
import scala.scalajs.js.annotation._


@JSExportAll case class CiteRelationSet (relations: Set[CiteTriple]) extends LogSupport {

  /** True if relations set is empty.*/
  def isEmpty: Boolean = {
    (relations.size == 0)
  }

  /** Filter set for verb URN matching a given URN.
  *
  * @param u CITE2 URN to match.
  */
  def verb(verbUrn: Cite2Urn): CiteRelationSet = {
    val matchingRelations = relations.filter(_.verb(verbUrn))
      CiteRelationSet(matchingRelations)
  }

  /** Find number of relations in set.
  */
  def size: Int = {
    relations.size
  }

  /** Return a Set of CiteUrns representing all verbs
  *
  */
  def verbs:Set[Cite2Urn] = {
    this.relations.map(_.relation).toSet
  }


  /** Filter set for subject URN matching a given URN.
  *
  * @param u URN to match (either a CTS or CITE2 URN).
  */
  def urn1Match(u: Urn) : CiteRelationSet = {
    val matchingRelations = relations.filter(_.urn1Match(u))
    CiteRelationSet(matchingRelations)
  }

  /** Filter set for object URN matching a given URN.
  *
  * @param u URN to match (either a CTS or CITE2 URN).
  */
  def urn2Match(u: Urn) : CiteRelationSet = {
    val matchingRelations = relations.filter(_.urn2Match(u))
    CiteRelationSet(matchingRelations)
  }


  /** Filter set for triplet matching a given URN
  * (subject or object).
  *
  * @param u URN to match.
  */
  def ~~(u: Urn) : CiteRelationSet = {
    val matchingRelations = relations.filter(_ ~~ u)
    CiteRelationSet(matchingRelations)
  }


  def cex(delimiter: String = "#"): String = {
    val hdr = "#!relations\n"
    val rows = relations.toVector.map(_.cex(delimiter))
    hdr + rows.mkString("\n")
  }

}


/** Factory for creating [[CiteRelationSet]] from Source
* data in CEX format.
*/
object CiteRelationSet extends LogSupport {

  /** Create set from CEX source string.
  *
  * @param cexSrc Source data in CEX format.
  */
  def apply(cexSrc: String, separator: String = "#"): CiteRelationSet = {
    val cex = CexParser(cexSrc)
    val lns = cex.blockString("relations").split("\n").toVector.filter(_.nonEmpty)

    val relations = lns.map(CiteTriple(_))

    /*val colsByLine = lns.map(_.split(separator).toVector.filter(_.size > 0))
    //debug(s"colsbyline: SIZE = ${colsByLine.size}")
    //debug("\t" + colsByLine)

      {

      val triple =  CiteTriple(CiteRelationSet.urnFromString(v(0)), Cite2Urn(v(1)), urnFromString(v(2)))
      triple   } )*/

    if (relations.size > 0) {
      CiteRelationSet(relations.toSet)
    } else {
      CiteRelationSet(Set[CiteTriple]())
    }
  }


  /** Create appropriately typed URN object from String
  * value.
  *
  * @param s String value of either a CtsUrn or Cite2Urn.
  */
  def urnFromString(s: String) : Urn = {
    if (s.startsWith("urn:cite2:")) {
      Cite2Urn(s)
    } else if (s.startsWith("urn:cts:")) {
      CtsUrn(s)
    } else {
      throw(CiteRelationException(s + " is not a URN value."))
    }
  }


}
