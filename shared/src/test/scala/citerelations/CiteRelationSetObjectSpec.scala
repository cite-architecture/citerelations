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
      println(u)
  }


}
