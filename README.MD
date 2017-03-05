# Text handler for JSON libraries

[![Build Status](https://travis-ci.org/markenwerk/java-utils-json-handler-text.svg?branch=master)](https://travis-ci.org/markenwerk/java-utils-json-handler-text)
[![Coverage Status](https://coveralls.io/repos/github/markenwerk/java-utils-json-handler-text/badge.svg?branch=master)](https://coveralls.io/github/markenwerk/java-utils-json-handler-text?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/571e4e29fcd19a005185676f/badge.svg)](https://www.versioneye.com/user/projects/571e4e29fcd19a005185676f)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-handler-text/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-handler-text)
[![Issues](https://img.shields.io/github/issues/markenwerk/java-utils-json-handler-text.svg)](https://github.com/markenwerk/java-utils-json-handler-text/issues)
[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/markenwerk/java-utils-json-handler-text/blob/master/LICENSE)

## Overview

This library provides text related JSON handlers for other JSON processing libraries.

Consult the [documentation](http://markenwerk.github.io/java-utils-json-handler-xml/javadoc/index.html) and the [usage description](#usage) for further information:

- [Java text handler](#java-text-handler)
- [JSON text handler](#json-text-handler)

## Maven

This library is hosted in the [Maven Central Repository](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-json-handler-text). You can use it with the following coordinates:

```xml
<dependency>
	<groupId>net.markenwerk</groupId>
	<artifactId>utils-json-handler-text</artifactId>
	<version>2.0.1</version>
</dependency>
```
 
## Usage

### Java text handler

An [`AppendingJavaTextJsonHandler`][AppendingJavaTextJsonHandler] is a [`JsonHandler`][JsonHandler] that appends a text, that represents the described JSON document by mimicing the `toString()` behavior of Java collection classes, to an [`Appendable`][Appendable]. 

```java
// a JsonDocument
JsonDocument document = ...

// writes a pretty printed json document into test.json  
Writer writer = new FileWriter(new File("test.json"));
document.handle(new AppendingJavaTextJsonHandler(writer));
writer.close();
```

The appended [`String`][String] for the [`example.json`](https://gist.github.com/toKrause/208992e7625f0b8a9d04e7283f09fc26) has the following content:

```
{null=null, boolean=true, long=-42, double=-23.42, array=[foo, bar]}
```

A [`JavaTextJsonHandler`][JavaTextJsonHandler] is a [`JsonHandler`][JsonHandler] that creates a [`String`][String], containing a text that represents the described JSON document by mimicing the `toString()` behavior of Java collection classes. 

```java
// a JsonDocument
JsonDocument document = ...

// returns a pretty printed json document  
String json = document.handle(new JavaTextJsonHandler(writer));
```

The returned [`String`][String] for the [`example.json`](https://gist.github.com/toKrause/208992e7625f0b8a9d04e7283f09fc26) has the following content:

```
{null=null, boolean=true, long=-42, double=-23.42, array=[foo, bar]}
```

### JSON text handler

An [`AppendingJsonTextJsonHandler`][AppendingJsonTextJsonHandler] is a [`JsonHandler`][JsonHandler] that appends a JSON text, that represents the described JSON document, to an [`Appendable`][Appendable]. 

```java
// a JsonDocument
JsonDocument document = ...

// writes a pretty printed json document into test.json  
Writer writer = new FileWriter(new File("test.json"));
document.handle(new AppendingJsonTextJsonHandler(writer));
writer.close();
```

The appended [`String`][String] for the [`example.json`](https://gist.github.com/toKrause/208992e7625f0b8a9d04e7283f09fc26) has the following content:

```json
{
    "null" : null,
    "boolean" : true,
    "long" : -42,
    "double" : -23.42,
    "array" : [
        "foo",
        "bar"
    ]
}
```

A [`JsonTextJsonHandler`][JsonTextJsonHandler] is a [`JsonHandler`][JsonHandler] that creates a [`String`][String], containing a JSON text that represents the described JSON document. 

```java
// a JsonDocument
JsonDocument document = ...

// returns a pretty printed json document  
String json = document.handle(new JsonTextJsonHandler(writer));
```

The returned [`String`][String] for the [`example.json`](https://gist.github.com/toKrause/208992e7625f0b8a9d04e7283f09fc26) has the following content:

```json
{
    "null" : null,
    "boolean" : true,
    "long" : -42,
    "double" : -23.42,
    "array" : [
        "foo",
        "bar"
    ]
}
```

[AppendingJavaTextJsonHandler]: https://markenwerk.github.io/java-utils-json-handler-text/index.html?net/markenwerk/utils/json/common/handler/text/AppendingJavaTextJsonHandler.html
[AppendingJsonTextJsonHandler]: https://markenwerk.github.io/java-utils-json-handler-text/index.html?net/markenwerk/utils/json/common/handler/text/AppendingJsonTextJsonHandler.html
[JavaTextJsonHandler]: https://markenwerk.github.io/java-utils-json-handler-text/index.html?net/markenwerk/utils/json/common/handler/text/JavaTextJsonHandler.html
[JsonTextJsonHandler]: https://markenwerk.github.io/java-utils-json-handler-text/index.html?net/markenwerk/utils/json/common/handler/text/JsonTextJsonHandler.html

[JsonHandler]: https://markenwerk.github.io/java-utils-json-handler/index.html?net/markenwerk/utils/json/handler/JsonHandler.html

[Appendable]: https://docs.oracle.com/javase/8/docs/api/index.html?java/lang/Appendable.html
[String]: https://docs.oracle.com/javase/8/docs/api/index.html?java/lang/String.html

