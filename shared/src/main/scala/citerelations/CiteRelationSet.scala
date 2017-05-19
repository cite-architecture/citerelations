package edu.holycross.shot.citerelation

import edu.holycross.shot.cite._


import scala.io.Source
import scala.collection.mutable.ArrayBuffer


case class CiteRelationSet (relations: Set[CiteTriple]) {

  def urn1Match(u: Urn) : CiteRelationSet = {
    val matchingRelations = relations.filter(_.urn1Match(u))
    CiteRelationSet(matchingRelations)
  }
  def urn2Match(u: Urn) : CiteRelationSet = {
    val matchingRelations = relations.filter(_.urn2Match(u))
    CiteRelationSet(matchingRelations)
  }


  def urnMatch(u: Urn) : CiteRelationSet = {
    val matchingRelations = relations.filter(_ ~~ u)
    CiteRelationSet(matchingRelations)
  }

}

object CiteRelationSet {

  def apply(f: String, separator: String = "\t"): CiteRelationSet = {
    val stringPairs = Source.fromFile(f).getLines.toVector.map(_.split(separator))

    val relations = stringPairs.tail.map( arr => CiteTriple(urnFromString(arr(0)), Cite2Urn(arr(1)), urnFromString(arr(2))) )
    CiteRelationSet(relations.toSet)
  }




}
