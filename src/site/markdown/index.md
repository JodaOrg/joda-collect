## <i></i> About

**Joda-Collect** provides collections that are not present in the JDK or
[Google Guava](https://github.com/google/guava).

The JDK and Guava contain many collection classes, but occasionally there is something missing.
This project provides a home for those collections.

Joda-Collect is licensed under the business-friendly [Apache 2.0 licence](licenses.html).


## <i></i> Features

Included collection types:

* Grid - A grid data structure, providing access to its values by row and column.
This is similar to Guava's `Table`, but uses `int` for the row and column, allowing optimisations.
Mutable and immutable implementations are provided in sparse and dense variations.


## <i></i> Documentation

Various documentation is available:

* The [Javadoc](apidocs/index.html)
* The [change notes](changes-report.html) for each release
* The [GitHub](https://github.com/JodaOrg/joda-collect) source repository


---

## <i></i> Releases

The 2.x branch (v2.0.0) is compatible with Java SE 21 or later.

The 1.x branch (v1.0.1) is compatible with Java SE 8 or later.

v2.x releases are compatible with v1.x releases - except for the Java SE version and `module-info.class` file.
Guava is a required module, but it cannot be declared as 'transitive' because it is an automatic module.

Joda-Collect [depends](dependencies.html) on Google Guava.

Available in [Maven Central](https://search.maven.org/search?q=g:org.joda%20AND%20a:joda-collect&core=gav).

```xml
<dependency>
  <groupId>org.joda</groupId>
  <artifactId>joda-collect</artifactId>
  <version>2.0.0</version>
</dependency>
```

Java module name: `org.joda.collect`.

---

### For Enterprise

[Available as part of the Tidelift Subscription](https://tidelift.com/subscription/pkg/maven-org-joda-joda-collect?utm_source=maven-org-joda-joda-collect&utm_medium=referral&utm_campaign=enterprise).

Joda and the maintainers of thousands of other packages are working with Tidelift to deliver one
enterprise subscription that covers all of the open source you use.

If you want the flexibility of open source and the confidence of commercial-grade software, this is for you.
[Learn more](https://tidelift.com/subscription/pkg/maven-org-joda-joda-collect?utm_source=maven-org-joda-joda-collect&utm_medium=referral&utm_campaign=enterprise).


### Support

Please use [Stack Overflow](https://stackoverflow.com/search?q=joda-collect) for general usage questions.
GitHub [issues](https://github.com/JodaOrg/joda-collect/issues) and [pull requests](https://github.com/JodaOrg/joda-collect/pulls)
should be used when you want to help advance the project.

Any donations to support the project are accepted via [OpenCollective](https://opencollective.com/joda).

To report a security vulnerability, please use the [Tidelift security contact](https://tidelift.com/security).
Tidelift will coordinate the fix and disclosure.
