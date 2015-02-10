## <i></i> About

**Joda-Collect** provides collections that are not present in the JDK or
[Google Guava](http://code.google.com/p/guava-libraries/).

The JDK and Guava contain many collection classes, but occasionally there is something missing.
This project provides a home for those collections.

Joda-Collect is licensed under the business-friendly [Apache 2.0 licence](license.html).


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

There are no full releases yet!
[Release 0.7](download.html) is the current development release intended for feedback.
The code is fully tested, but there may yet be bugs and the API may yet change.
There should be no great reason why it cannot be used in production if you can cope with future API change.

Joda-Collect requires Java SE 6 or later and [depends](dependencies.html) on Google Guava.

Available in [Maven Central](http://search.maven.org/#artifactdetails%7Corg.joda%7Cjoda-collect%7C0.7%7Cjar).

```xml
<dependency>
  <groupId>org.joda</groupId>
  <artifactId>joda-collect</artifactId>
  <version>0.7</version>
</dependency>
```

---

### Support

Support on bugs, library usage or enhancement requests is available on a best efforts basis.

To suggest enhancements or contribute, please [fork the source code](https://github.com/JodaOrg/joda-collect)
on GitHub and send a Pull Request.

Alternatively, use GitHub [issues](https://github.com/JodaOrg/joda-collect/issues).
