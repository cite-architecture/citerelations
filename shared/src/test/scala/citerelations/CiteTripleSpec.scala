package edu.holycross.shot.citerelation
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source


class CiteTripleSpec extends FlatSpec {


  val venetusA = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:")
  val twelveverso = Cite2Urn("urn:cite2:hmt:msA.r1:12v")


  "The CiteTriple object" should "form a triple from a single line of delimited text" in {
    val cex = "urn:cts:greekLit:tlg0012.tlg001.msA:2.1#urn:cite2:cite:dseverbs.r1:appearsOn#urn:cite2:hmt:msA.r1:12v"
    val trip = CiteTriple(cex)
    trip match {
      case ct: CiteTriple => {
        assert(ct.urn1 == CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:2.1"))
        assert(ct.urn2 == Cite2Urn("urn:cite2:hmt:msA.r1:12v"))
        assert(ct.relation == Cite2Urn("urn:cite2:cite:dseverbs.r1:appearsOn"))
      }
      case _ => fail("Should have created a CiteTriple")
    }
  }

  "A CITE triple" should "accept a CTS URN for its first relation" in {
    val il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val img = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")
    val appearsOn = Cite2Urn("urn:cite2:hmt:verbs:appearsOn")

    val triple = CiteTriple(il1_1,appearsOn, img)

    triple.urn1 match {
      case cts: CtsUrn => assert(true)
      case _ => fail("Should have found a CtsUrn")
    }
  }

  it should "compare a specified CTS URN to its first URN" in {
      val cex = "urn:cts:greekLit:tlg0012.tlg001.msA:2.1#urn:cite2:cite:dseverbs.r1:appearsOn#urn:cite2:hmt:msA.r1:12v"
      val triple = CiteTriple(cex)

      assert(triple.ctsUrn1Match(venetusA))
  }

  it should "compare a specified CTS URN to its second URN" in {
    val cex = "urn:cite2:hmt:msA.r1:12v#urn:cite2:cite:dseverbs.r1:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:2.1"
    val triple = CiteTriple(cex)
    assert(triple.ctsUrn2Match(venetusA))
  }


  it should "compare any type of URN to its first URN" in {
    val cex = "urn:cts:greekLit:tlg0012.tlg001.msA:2.1#urn:cite2:cite:dseverbs.r1:appearsOn#urn:cite2:hmt:msA.r1:12v"
    val triple = CiteTriple(cex)

    assert(triple.urn1Match(venetusA))
    assert(triple.urn1Match(twelveverso) == false)
  }


  it should "compare any type of URN to its second URN" in {
    val cex = "urn:cite2:hmt:msA.r1:12v#urn:cite2:cite:dseverbs.r1:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:2.1"
    val triple = CiteTriple(cex)
    assert(triple.urn2Match(venetusA))
    assert(triple.urn2Match(twelveverso) == false)
  }


  it should "compare an identified CITE2 URN to its first URN" in {
    val cex = "urn:cite2:hmt:msA.r1:12v#urn:cite2:cite:dseverbs.r1:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:2.1"
    val triple = CiteTriple(cex)
    assert(triple.citeUrn1Match(twelveverso))
    //assert(triple.citeUrn1Match(venetusA) == false)

  }

  it should "compare an identified CITE2 URN to its second URN" in {
    val cex = "urn:cts:greekLit:tlg0012.tlg001.msA:2.1#urn:cite2:cite:dseverbs.r1:appearsOn#urn:cite2:hmt:msA.r1:12v"
    val triple = CiteTriple(cex)
    assert(triple.citeUrn2Match(twelveverso))
    //assert(triple.citeUrn1Match(venetusA) == false)

  }

  it should "compare an unidentified CITE2 URN to its first URN" in {
    val cex = "urn:cite2:hmt:msA.r1:12v#urn:cite2:cite:dseverbs.r1:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:2.1"
    val triple = CiteTriple(cex)
    assert(triple.urn1Match(twelveverso))
    assert(triple.urn1Match(venetusA) == false)
  }

  it should "compare an unidentified CITE2 URN to its second URN" in {
    val cex = "urn:cts:greekLit:tlg0012.tlg001.msA:2.1#urn:cite2:cite:dseverbs.r1:appearsOn#urn:cite2:hmt:msA.r1:12v"
    val triple = CiteTriple(cex)
    assert(triple.urn2Match(twelveverso))
    assert(triple.urn2Match(venetusA) == false)

  }


}
