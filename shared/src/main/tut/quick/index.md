---
layout: page
title: Quick start
---


## Create a `CiteRelationSet`

Import the package:

```tut:silent
import edu.holycross.shot.citerelation._
```

Create a `CiteRelationSet` from a `String` in CEX format:

```tut:silent
val cex = """
#!relations
// Subject#Relation#Object
urn:cts:greekLit:tlg0012.tlg001.msA:1.title#urn:cite2:dse:verbs.v1:appears_on#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.2022,0.211,0.1732,0.0203
// etc, etc, etc
"""
val relationSet = CiteRelationSet(cex)
```

```tut:invisible
// set up some data
import scala.io.Source
val txt = Source.fromFile("jvm/src/test/resources/venA-Iliad-1.cex").getLines.mkString("\n")
val relationSet = CiteRelationSet(txt)
```


## Query the relations

You'll need to import the `cite` library to work with URNs.


```tut:silent
import edu.holycross.shot.cite._
```

The following examples use an index mapping 611 lines of the *Iliad* to an image's region of interest.


### Find all relations of a given type

```tut:silent
val relation = Cite2Urn("urn:cite2:dse:verbs.v1:appears_on")
val triples = relationSet.verb(relation)
// 611 lines indexed in this data set:
assert(triples.size == 611)
```



### Find all relations of a given subject

```tut:silent
val psg = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1.600")
val triples = relationSet.urn1Match(psg)
// Line appears on one page:
assert(triples.size == 1)
```


### Find all relations of a given Object


```tut:silent
val img = Cite2Urn("urn:cite2:hmt:vaimg.r1:VA023VN_0525")
val triples = relationSet.urn2Match(img)
// 25 lines on a page:
assert(triples.size == 25)
```


### Chain results

```tut:silent
val triples = relationSet.verb(relation).urn1Match(psg)
```


### Working relation sets

A `CiteRelationSet` has a `relations` member that is a set of triples.


```tut:silent
val tripleSet = triples.relations
```

Each triple has a subject, verb and object.

```tut
// arbitrarily select a triple from the set and
// look at its three parts
val oneTriple = tripleSet.toSeq(0)
oneTriple.urn1
oneTriple.relation
oneTriple.urn2
```
