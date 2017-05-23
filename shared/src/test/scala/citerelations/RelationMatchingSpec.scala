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

  it should "match a Cite2Urn in the first Urn" in {
    val il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val img = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")

    val triple = CiteTriple( img, illustrates, il1_1)

    val matchImg = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013")
    assert (triple.urn1Match(matchImg))
  }


  it should "match either side from a Cite2 URN" in {
    val il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val img = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")

    val triple = CiteTriple(il1_1, appearsOn, img)

    val matchImg = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA012RN_0013")
    assert (triple ~~ matchImg)
  }


// Loading from file is JVM specifics
  it should "filter a relation set by CTS URN on URN 1" in pending /*{
    val  il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val relationSet = CiteRelationSet("src/test/resources/venA-Iliad-1.tsv")
    val filtered = relationSet.urn1Match(il1_1)
    assert (filtered.relations.size == 1)
  }

  it should "filter a relations set by CTS URN on either URN" in {
    val  il1_1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val relationSet = CiteRelationSet("src/test/resources/venA-Iliad-1.tsv")
    val filtered = relationSet.relations.filter(_.urnMatch(il1_1))
    assert (filtered.size == 1)
  }

*/
}
