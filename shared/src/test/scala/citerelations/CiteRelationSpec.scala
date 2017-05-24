package edu.holycross.shot.citerelation
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class CiteRelationSpec extends FlatSpec {

val cexSrc = """
#!relations

#  SVO triplets :

urn:cts:greekLit:tlg0012.tlg001.msA:1.title#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.2022,0.211,0.1732,0.0203
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901
"""

  // This is a JVM function
  "A CITE relation set" should "offer a constructor signature for instantiating a corpus from a CEX text source" in  {
    val relations = CiteRelationSet(cexSrc)
    relations match {
      case crset: CiteRelationSet => assert(crset.size == 2)
      case _ => fail("Should have created CiteRelationSet")
    }
  }



}
