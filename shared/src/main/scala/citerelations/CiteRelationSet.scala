package edu.holycross.shot.citerelation

import edu.holycross.shot.cite._


import scala.io.Source
import scala.collection.mutable.ArrayBuffer


import scala.scalajs.js
import js.annotation.JSExport


@JSExport case class CiteRelationSet (relations: Set[CiteTriple]) {


  /** Filter set for verb URN matching a given URN.
  *
  * @param u CITE2 URN to match.
  */
  def verb(verbUrn: Cite2Urn): CiteRelationSet = {
    val matchingRelations = relations.filter(_.verbMatch(verbUrn))
      CiteRelationSet(matchingRelations)
  }

  /** Find number of relations in set.
  */
  def size: Int = {
    relations.size
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

}


/** Factory for creating [[CiteRelationSet]] from Source
* data in CEX format.
*/
object CiteRelationSet {

  /** Create set from CEX source string.
  *
  * @param cexSrc Source data in CEX format.
  */
  def apply(cexSrc: String, separator: String = "#"): CiteRelationSet = {
    val lns = cexSrc.split("\n").toVector.map(_.split(separator).toVector)
    val relations = lns.map(v => {
      val triple =      CiteTriple(CiteRelationSet.urnFromString(v(0)), Cite2Urn(v(1)), urnFromString(v(2)))
      triple
   } )
    CiteRelationSet(relations.toSet)
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
