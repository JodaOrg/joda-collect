Joda-Collect
------------

Joda-Collect provides collections that are not present in the JDK or [Google Guava](http://code.google.com/p/guava-libraries/).

The project is related to [Joda-Primitives](http://www.joda.org/joda-primitives/) which provides primitive versions of the collection interfaces.
This project is separate, as the use cases are likely to be different.

Joda-Collect is licensed under the business-friendly [Apache 2.0 licence](http://www.joda.org/joda-collect/license.html).


### Contents

Joda-Collect contains the following collections:

* Grid - a grid data structure, providing access to its values by row and column.
This is similar to Guava's `Table`, but uses `int` for the row and column, allowing optimisations.
Mutable and immutable implementations are provided in sparse and dense variations.


### Documentation
Various documentation is available:

* The [home page](http://www.joda.org/joda-collect/)
* The [Javadoc](http://www.joda.org/joda-collect/apidocs/index.html)
* The change notes for the [releases](http://www.joda.org/joda-collect/changes-report.html)


### Releases
[Release 1.0.1](http://www.joda.org/joda-collect/download.html) is the current latest release.
This release is considered stable and worthy of the 1.x tag.
It depends on Java SE 8 or later and requires [Google-Guava](http://code.google.com/p/guava-libraries/).

Available in the [Maven Central repository](http://search.maven.org/#artifactdetails|org.joda|joda-collect|1.0.1|jar)


### Support
Please use GitHub issues and Pull Requests for support.


### Release process

* Update version (README.md, index.md, changes.xml)
* Commit and push
* `mvn clean release:clean release:prepare release:perform`
* Website will be built and released by Travis
