Joda-Collect
------------

Joda-Collect provides collections that are not present in the JDK or [Google Guava](https://github.com/google/guava).

The project is related to [Joda-Primitives](https://www.joda.org/joda-primitives/) which provides primitive versions of the collection interfaces.
This project is separate, as the use cases are likely to be different.

Joda-Collect is licensed under the business-friendly [Apache 2.0 licence](https://www.joda.org/joda-collect/licenses.html).


### Contents

Joda-Collect contains the following collections:

* Grid - a grid data structure, providing access to its values by row and column.
This is similar to Guava's `Table`, but uses `int` for the row and column, allowing optimisations.
Mutable and immutable implementations are provided in sparse and dense variations.


### Documentation
Various documentation is available:

* The [home page](https://www.joda.org/joda-collect/)
* The [Javadoc](https://www.joda.org/joda-collect/apidocs/index.html)
* The change notes for the [releases](https://www.joda.org/joda-collect/changes-report.html)


### Releases
The 2.x branch is compatible with Java SE 21 or later.

The 1.x branch is compatible with Java SE 8 or later.

v2.x releases are compatible with v1.x releases - except for the Java SE version and `module-info.class` file.
Guava is a required module, but it cannot be declared as 'transitive' because it is an automatic module.

Joda-Collect depends on [Google-Guava](https://github.com/google/guava).

Available in the [Maven Central repository](https://search.maven.org/search?q=g:org.joda%20AND%20a:joda-collect&core=gav)

![Tidelift dependency check](https://tidelift.com/badges/github/JodaOrg/joda-collect)


### For enterprise
Available as part of the Tidelift Subscription.

Joda and the maintainers of thousands of other packages are working with Tidelift to deliver one enterprise subscription that covers all of the open source you use.

If you want the flexibility of open source and the confidence of commercial-grade software, this is for you.

[Learn more](https://tidelift.com/subscription/pkg/maven-org-joda-joda-collect?utm_source=maven-org-joda-joda-collect&utm_medium=github)


### Support
Please use [Stack Overflow](https://stackoverflow.com/search?q=joda-collect) for general usage questions.
GitHub [issues](https://github.com/JodaOrg/joda-collect/issues) and [pull requests](https://github.com/JodaOrg/joda-collect/pulls)
should be used when you want to help advance the project.

Any donations to support the project are accepted via [OpenCollective](https://opencollective.com/joda).

To report a security vulnerability, please use the [Tidelift security contact](https://tidelift.com/security).
Tidelift will coordinate the fix and disclosure.


### Release process

* Update version (index.md, changes.xml)
* Commit and push
* `git push origin HEAD:refs/tags/release`
* Code and Website will be built and released by GitHub Actions

Release from local:

* Ensure `gpg-agent` is running
* `mvn clean release:clean release:prepare release:perform`
