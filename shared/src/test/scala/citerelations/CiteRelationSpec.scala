package edu.holycross.shot.citerelation
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class CiteRelationSpec extends FlatSpec {

val cexSrc = """
#!relations

//  SVO triplets :

// Iliad 1.1
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901
urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901#urn:cite2:cite:dseverbs.r1:illustrates#urn:cts:greekLit:tlg0012.tlg001.msA:1.1

// Iliad 2.1
urn:cts:greekLit:tlg0012.tlg001.msA:2.1#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012VN_0514@0.4956,0.2191,0.3254,0.02158
urn:cite2:hmt:vaimg.r1:VA012VN_0514@0.4956,0.2191,0.3254,0.02158#urn:cite2:cite:dseverbs.r1:illustrates#urn:cts:greekLit:tlg0012.tlg001.msA:2.1

// MS 12r
urn:cite2:hmt:msA.r1:12r#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013
urn:cite2:hmt:vaimg.r1:VA012RN_0013#urn:cite2:cite:dseverbs.r1:illustrates#urn:cite2:hmt:msA.r1:12r

// MS 12v
urn:cite2:hmt:msA.r1:12v#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012VN_0514
urn:cite2:hmt:vaimg.r1:VA012VN_0514#urn:cite2:cite:dseverbs.r1:illustrates#urn:cite2:hmt:msA.r1:12v

// Iliad 1.1 + MS 12r
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.r1:appearsOn#urn:cite2:hmt:msA.r1:12r
urn:cite2:hmt:msA.r1:12r#urn:cite2:cite:dseverbs.r1:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:1.1

// Iliad 2.1 + MS 12v
urn:cts:greekLit:tlg0012.tlg001.msA:2.1#urn:cite2:cite:dseverbs.r1:appearsOn#urn:cite2:hmt:msA.r1:12v
urn:cite2:hmt:msA.r1:12v#urn:cite2:cite:dseverbs.r1:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:2.1
"""

  val relations = CiteRelationSet(cexSrc)


  // Query on notional versions of same objects:
  val msAiliad = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:")
  val matchPage = Cite2Urn("urn:cite2:hmt:msA:12r")
  val matchImg = Cite2Urn("urn:cite2:hmt:vaimg:VA012RN_0013")

  val dseverbs = Cite2Urn("urn:cite2:cite:dseverbs:")
  val hasonit = Cite2Urn("urn:cite2:cite:dseverbs.r1:hasOnIt")

  "A CITE relation set" should "offer a constructor signature for instantiating a corpus from a CEX text source" in  {

    relations match {
      // 3 symmetrical pairs:
      case crset: CiteRelationSet => assert(crset.size == 12)
      case _ => fail("Should have created CiteRelationSet")
    }
  }


  it should "filter a relation set by an unspecified CTS URN on URN 1" in {
    val filtered = relations.urn1Match(msAiliad)
    assert(filtered.size == 4)
  }
  it should "filter a relation set by CITE2 URN on URN 1" in  {
    val filteredPage = relations.urn1Match(matchPage)
    assert(filteredPage.size == 2)

    val filteredImage = relations.urn1Match(matchImg)
    assert(filteredImage.size == 2)
  }


  it should "filter a relation set by CTS URN on URN 2" in {
    val filtered = relations.urn2Match(msAiliad)
    assert(filtered.size == 4)
  }
  it should "filter a relation set by CITE2 URN on URN 2" in {

    val filteredPage = relations.urn2Match(matchPage)
    assert(filteredPage.size == 2)

    val filteredImage = relations.urn2Match(matchImg)
    assert(filteredImage.size == 2)
  }


  it should "filter a relation set by CTS URN on either URN" in {
    val filtered = relations ~~ msAiliad
    assert(filtered.size == 8)

  }
  it should "filter a relation set by CITE2 URN on either URN" in {
    val filteredPage = relations ~~ matchPage
    assert(filteredPage.size == 4)

    val filteredImage = relations ~~ matchImg
    assert(filteredImage.size == 4)
  }

  it should "fiter a relation set by verb URN" in {
    val filtered = relations.verb(dseverbs)
    assert(filtered.size == 12)
  }

  it should "fiter a relation set by specific verb URN" in {
    val filtered = relations.verb(hasonit)
    assert(filtered.size == 2)
  }

  it should "serialize to CEX" in {
    val cex = relations.cex()
    val expected = cexSrc.split("\n").toVector.filter(_.nonEmpty).filterNot(_.startsWith("//")).mkString("\n")
    println("\n\nActual: " + cex.size)
    println(cex)
    println("\n\nExpeted: " + expected.size)
    println(expected)
    val cexV = cex.split("\n").toVector.sorted
    val expV = expected.split("\n").toVector.sorted
    assert (cexV == expV)

  }


}
