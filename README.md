# `citerelations`


## What it is

`citerelations` is a cross-platform library for working with sets of triples describing relations among citable objects.

## Current version: 2.7.0

Status:  in active development.  See [release notes](releases.md)

## License

[GPL 3.0](https://opensource.org/licenses/gpl-3.0.html)


## Documentation

See <https://cite-architecture.github.io/citerelations/>.

## Using, building, testing

`citerelations` is compiled for both the JVM and ScalaJS using scala versions 2.10, 2.11 and 2.12.


Binaries for all platforms are available from jcenter.

If you are using sbt, include Resolver.jcenterRepo in your list of resolvers

    resolvers += Resolver.jcenterRepo

and add this to your library dependencies:

    "edu.holycross.shot" %%% "citerelations" % VERSION

For maven, ivy or gradle equivalents, refer to https://bintray.com/neelsmith/maven/citerelations.

To build from source and test, use normal sbt commands (`compile`, `test` ...).
