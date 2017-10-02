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
There are no full releases yet!
[Release 0.8](http://www.joda.org/joda-collect/download.html) is the current latest release.
The code is fully tested, but there may yet be bugs and the API may yet change.
There should be no great reason why it cannot be used in production if you can cope with future API change.

Joda-Collect runs on JDK 1.6 or later and requires [Google-Guava](http://code.google.com/p/guava-libraries/).

Available in the [Maven Central repository](http://search.maven.org/#artifactdetails|org.joda|joda-collect|0.8|jar)


### Support
Please use GitHub issues and Pull Requests for support.
