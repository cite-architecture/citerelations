package edu.holycross.shot.citerelation
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class CiteRelationSetObjectSpec extends FlatSpec {

  "The CITE relation set Object" should "convert Strings to URNs" in  {
      val extendedImgStr = "urn:cite2:hmt:vaimg.v1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901"
      val u = CiteRelationSet.urnFromString(extendedImgStr)
      assert(u.toString == extendedImgStr)
  }

  it should "create a relation set from valid CEX source" in {
    val cexSrc = """
#!relations

// Note that block can include contents, and blank lines to improve human legibility:

urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901
urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901#urn:cite2:cite:dseverbs.r1:illustrates#urn:cts:greekLit:tlg0012.tlg001.msA:1.1
urn:cite2:hmt:msA.r1:12r#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013
urn:cite2:hmt:vaimg.r1:VA012RN_0013#urn:cite2:cite:dseverbs.r1:illustrates#urn:cite2:hmt:msA.r1:12r
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.r1:appearsOn#urn:cite2:hmt:msA.r1:12r
urn:cite2:hmt:msA.r1:12r#urn:cite2:cite:dseverbs.r1:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:1.1
"""
    val relations = CiteRelationSet(cexSrc)
    relations match {
      case crs: CiteRelationSet => {
        assert(crs.size == 6)
      }
      case _ => fail ("Should have created CiteRelationSet")
    }
  }

  it should "create an empty relation set if CEX source has no relations block" in {
    val noBlock = """#!citelibrary
name#Demo of DSE structure: Venetus A manuscript, folio 12 recto
urn#urn:cite2:dse:demo.2017a:va12r
license#public domain
"""
    val relations  = CiteRelationSet(noBlock)
    assert(relations.size == 0)
    assert(relations.isEmpty)
  }

it should "create an empty relation set if there are no content lines in the CEX relations block" in {
  val noData = """#!citelibrary
name#Demo of DSE structure: Venetus A manuscript, folio 12 recto
urn#urn:cite2:dse:demo.2017a:va12r
license#public domain

#!relations
//
// Empty block: comments and blank lines, but
// no content lines.


"""
  val relations  = CiteRelationSet(noData)
  assert(relations.size == 0)
  assert(relations.isEmpty)
}

it should "return a Set of Cite2Urns representing the verbs in the RelationSet" in {

    val cexSrc = """
#!relations

// Note that block can include contents, and blank lines to improve human legibility:

urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901
urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901#urn:cite2:cite:dseverbs.r1:illustrates#urn:cts:greekLit:tlg0012.tlg001.msA:1.1
urn:cite2:hmt:msA.r1:12r#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013
urn:cite2:hmt:vaimg.r1:VA012RN_0013#urn:cite2:cite:dseverbs.r1:illustrates#urn:cite2:hmt:msA.r1:12r
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.r1:appearsOn#urn:cite2:hmt:msA.r1:12r
urn:cite2:hmt:msA.r1:12r#urn:cite2:cite:dseverbs.r1:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:1.1
"""
    val relations = CiteRelationSet(cexSrc)
    val allVerbs:Set[Cite2Urn] = relations.verbs
    assert (allVerbs.size == 4)
}


}
