package edu.holycross.shot.citerelation
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class RelationMatchingSpec extends FlatSpec {


  // DSE Verbs:
  val illustratedBy = Cite2Urn("urn:cite2:cite:dseverbs.2017a:illustratedBy")
  val illustrates = Cite2Urn("urn:cite2:cite:dseverbs.2017a:illustrates")

  val appearsOn = Cite2Urn("urn:cite2:cite:dseverbs.2017a:appearsOn")
  val hasOnIt = Cite2Urn("urn:cite2:cite:dseverbs.2017a:hasOnIt")

  // Related specific objects:
  val il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  val img = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")
  val pg12 = Cite2Urn("urn:cite2:hmt:msA.r1:12r")


  // Notional versions of same objects:
  val msAiliad = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  val matchPage = Cite2Urn("urn:cite2:hmt:msA:12r")
  val matchImg = Cite2Urn("urn:cite2:hmt:vaimg:VA012RN_0013")


  "Urn matching on relations" should "match a CTS URN in the first URN" in {
    val triple = CiteTriple(il1_1, illustratedBy, img)
    assert (triple.urn1Match(msAiliad))
  }

  it should "match a CITE2 URN in the first Urn" in {
    val triple = CiteTriple( img, illustrates, il1_1)
    assert (triple.urn1Match(matchImg))
  }


  it should "match a CTS URN in the second Urn" in {
    val triple = CiteTriple(img,illustrates,il1_1)
    assert(triple.urn2Match(msAiliad))
  }

  it should "match a CITE2 URN in the second Urn" in {
    val triple = CiteTriple(il1_1,appearsOn,pg12)
    assert(triple.urn2Match(matchPage))
  }


  it should "match either side from a CTS URN" in {
    val triple = CiteTriple(il1_1, illustratedBy, img)
    assert (triple ~~ msAiliad)

    val triple2 = CiteTriple(img,illustrates,il1_1)
    assert (triple ~~ msAiliad)
  }

  it should "match either side from a Cite2 URN" in {
    val triple = CiteTriple(pg12, illustratedBy, img)
    assert (triple ~~ matchImg)

    val triple2 = CiteTriple(img,illustrates,pg12)
    assert (triple ~~ matchImg)
  }

  it should "match the verb" in {
    val triple = CiteTriple(pg12, illustratedBy, img)
    val dseClass = Cite2Urn("urn:cite2:cite:dseverbs:")
    assert(triple.verbMatch(dseClass))
  }

}
