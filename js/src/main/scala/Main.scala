package edu.holycross.shot.citerelation

import edu.holycross.shot.cite._

import scala.scalajs.js

/** Verify that JS export works correctly. */
object Main extends js.JSApp {
  def main(): Unit = {
    val cexSrc = """
#!relations
urn:cts:greekLit:tlg0012.tlg001.msA:1.title#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.2022,0.211,0.1732,0.0203
"""

//  urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901
  println("Run in JS environment.")
  val il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  val img = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")
  val appearsOn = Cite2Urn("urn:cite2:hmt:verbs:appearsOn")


  val triple = CiteTriple(il1_1,appearsOn, img)
  println("Made a CiteTriple " + triple)

  val exc = CiteRelationException("Made up exception")
  println("Made an exception " + exc)

  val tinySet = CiteRelationSet (Set(triple))
  println("Made a set with one relation " + tinySet)

  val relations = CiteRelationSet(cexSrc,"#")
  println("Made a relation set with  " + relations.size + " relation.")


  }
}
