package edu.holycross.shot.citerelation
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class RelationMatchingSpec extends FlatSpec {


  val appearsOn = Cite2Urn("urn:cite2:hmt:verbs:appearsOn")
  val illustrates = Cite2Urn("urn:cite2:hmt:verbs:illustrates")


  "Urn matching on relations" should "match a CTS URN in the first URN" in {
    val il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val img = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")

    val triple = CiteTriple(il1_1, appearsOn, img)

    val msAiliad = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    assert (triple.urn1Match(msAiliad))
  }

  it should "match a CITE2 URN in the first Urn" in {
    val il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val img = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")

    val triple = CiteTriple( img, illustrates, il1_1)

    val matchImg = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013")
    assert (triple.urn1Match(matchImg))
  }


  it should "match a CTS URN in the second Urn" in pending

  it should "match a CITE2 URN in the second Urn" in pending


  it should "match either side from a CTS URN" in pending

  it should "match either side from a Cite2 URN" in {
    val il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val img = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")

    val triple = CiteTriple(il1_1, appearsOn, img)

    val matchImg = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013")
    assert (triple ~~ matchImg)
  }

  it should "match the verb" in pending

}
