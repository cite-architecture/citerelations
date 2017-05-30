package edu.holycross.shot.citerelation
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class DseRelationSpec extends FlatSpec {


  "A CITE relation set" should "parse the standard DSE test data set" in {
    val dseExample = """
#!relations
# 1. Relation of text passages to text-bearing surface:
#urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.2017a:appearsOn#urn:cite2:hmt:msA.2017a:12r
#urn:cite2:hmt:msA.2017a:12r#urn:cite2:cite:dseverbs.2017a:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:1.1
# 2. Relation of text passages to documentary image:
#urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.2017a:illustratedBy#urn:cite2:hmt:vaimg.v1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901
urn:cite2:hmt:vaimg.v1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901#urn:cite2:cite:dseverbs.2017a:illustrates#urn:cts:greekLit:tlg0012.tlg001.msA:1.1
# 3. Relation of text-bearing surface to documentary image:
#urn:cite2:hmt:msA.2017a:12r#urn:cite2:cite:dseverbs.2017a:illustratedBy#urn:cite2:hmt:vaimg.2017a:VA012RN_0013
#urn:cite2:hmt:vaimg.2017a:VA012RN_0013#urn:cite2:cite:dseverbs.2017a:illustrates#urn:cite2:hmt:msA.2017a:12r
"""

    val relations = CiteRelationSet(dseExample)

  }



}
