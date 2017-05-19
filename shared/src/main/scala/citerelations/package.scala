package edu.holycross.shot

import edu.holycross.shot.cite._

package object citerelation {


  /** Create appropriately typed URN object from String
  * value.
  *
  * @param s String value of either a CtsUrn or Cite2Urn.
  */
  def urnFromString(s: String) : Urn = {
    if (s.startsWith("urn:cite2:")) {
      Cite2Urn(s)
    } else if (s.startsWith("urn:cts:")) {
      CtsUrn(s)
    } else {
      throw(CiteRelationException(s + " is not a URN value."))
    }

  }

}
