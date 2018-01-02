---
layout: page
title: "CITE relations: home page"
---


## The model

Two citable objects can be related in a S-V-O statement that is conceptually comparable to the model of a [triple in the Resource Description Framework](https://www.w3.org/TR/rdf-concepts/) (RDF).  In RDF, the subject and verb of a triple may be any form of URI, and objects may include various kinds of data. In CITE relations, each element of S-V-O group must be a technology-independent URN.



Indexing follows the CITE data exchange model relating two citable objects in a S-V-O statement.


### Representing CITE relations in CEX

CITE relations are naturally represented in CEX as three columns of delimited text.   The subject and object elements of each statement must be URN values (either CITE2 URNs, or CTS URNs). The verb of each statement must be a CITE2 URN. See <https://cite-architecture.github.io/citedx/CEX-spec-3.0.1/#relations>.

## The `citerelations` code library


`citerelations` is a cross-platform library written in Scala for working with CITE relations.

-   [Quick start](quick)
-   Current [API docs](api/edu/holycross/shot/citerelation/index.html)
-   [Github repository](https://github.com/cite-architecture/citerelation)
