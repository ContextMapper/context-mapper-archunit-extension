![Context Mapper](https://raw.githubusercontent.com/wiki/ContextMapper/context-mapper-dsl/logo/cm-logo-github-small.png) 
# Context Mapper ArchUnit Extension 
[![Build (master)](https://github.com/ContextMapper/context-mapper-archunit-extension/actions/workflows/build_master.yml/badge.svg)](https://github.com/ContextMapper/context-mapper-archunit-extension/actions/workflows/build_master.yml) [![codecov](https://codecov.io/gh/ContextMapper/context-mapper-archunit-extension/branch/master/graph/badge.svg)](https://codecov.io/gh/ContextMapper/context-mapper-archunit-extension) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Maven Central](https://img.shields.io/maven-central/v/org.contextmapper/context-mapper-archunit-extension.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22org.contextmapper%22%20AND%20a:%22context-mapper-archunit-extension%22)

This library can help you validating your code against a Context Mapper DSL (CML) model. It enables Context Mapper users to ensure that the implemented code (tactic DDD) corresponds to the CML model with [ArchUnit](https://www.archunit.org/). To make this work, you need to annotate your Java classes with the tactic DDD concepts. Our library supports [jMolecules](https://github.com/xmolecules/jmolecules) out of the box; but you can use your own set of annotations as well.

## Usage
You can use this library to write ArchUnit tests by including it into your Gradle or Maven project.

**Gradle:**

```gradle
testImplementation 'org.contextmapper:context-mapper-archunit-extension:1.0.0'
```

**Maven:**

```xml
<dependency>
  <groupId>org.contextmapper</groupId>
  <artifactId>context-mapper-archunit-extension</artifactId>
  <version>1.0.0</version>
</dependency>
```

### JavaDoc
Besides the code example below, you can find the complete JavaDoc of the libraries API [here](https://www.javadoc.io/doc/org.contextmapper/context-mapper-archunit-extension/latest/index.html).

### Use all our test cases
The simplest way to apply all our test predefined test cases is to extend our `AbstractTacticArchUnitTest`. An example:

```java
class TacticArchUnitTestExample extends AbstractTacticArchUnitTest {

    @Override
    protected String getBoundedContextName() {
        return "SampleContext";
    }

    @Override
    protected String getCMLFilePath() {
        return "src/test/cml/test.cml";
    }

    @Override
    protected String getJavaPackageName2Test() {
        return "org.contextmapper.archunit.sample.sampleaggregate1";
    }
}
```

You basically have to provide three parameters:

 * The name of the Bounded Context against you want to test (CML).
 * The path to the CML file.
 * The package that shall be scanned for the JMolecules annotations.

### Implement custom set of rules
In case you don't want apply all our rules, you can also implement your custom test cases. An example:

```java
public class ExampleArchitectureTest {

    private BoundedContext context;
    private JavaClasses classes;

    @BeforeEach
    protected void setup() {
        this.context = new BoundedContextResolver()
                .resolveBoundedContextFromModel("src/main/cml/model.cml", "SampleBoundedContext");
        this.classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("org.example.app");
    }

    @Test
    void aggregatesShouldBeModeledInCML() {
        aggregateClassesShouldBeModeledInCml(context).check(classes);
    }

    @Test
    void entitiesShouldBeModeledInCML() {
        entityClassesShouldBeModeledInCml(context).check(classes);
    }

    @Test
    void valueObjectsShouldBeModeledInCML() {
        valueObjectClassesShouldBeModeledInCml(context).check(classes);
    }
}
```

The three example tests above ensure that Aggregates, Entities, and Value Objects that exist in the Java code, also exist in the CML model. In case a developer adds an Aggregate, Entity, or Value Object to the code that is not part of the CML model, the test will fail.

The example above uses our predefined rules based on the jMolecules annotations. You can find all available rules [here](https://www.javadoc.io/doc/org.contextmapper/context-mapper-archunit-extension/latest/org/contextmapper/archunit/ContextMapperJMoleculesArchRules.html). The next section shows how you can write your own rules, in case you don't want to use jMolecules.

### Use custom annotations
By using only our ArchCondition's it is also possible to implement the tests with other annotations than JMolecules:

```java
public class ExampleArchitectureTest {

    protected BoundedContext context;
    protected JavaClasses classes;

    
    @BeforeEach
    protected void setup() {
        this.context = new BoundedContextResolver()
                .resolveBoundedContextFromModel("src/main/cml/model.cml", "SampleBoundedContext");
        this.classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("org.example.app");
    }

    @Test
    void aggregatesShouldBeModeledInCML() {
        classes().that().areAnnotatedWith(Aggregate.class).should(beModeledAsAggregatesInCML(context));
    }

    @Test
    void entitiesShouldBeModeledInCML() {
        classes().that().areAnnotatedWith(Entity.class).should(beModeledAsEntitiesInCML(context));
    }

    @Test
    void valueObjectsShouldBeModeledInCML() {
        classes().that().areAnnotatedWith(ValueObject.class).should(beModeledAsValueObjectsInCML(context));
    }
}
```

As the example illustrates, in this case you are able to select your classes with your own code (using your own annotations); but you can still use our conditions, that check specific things in the CML model. You can find all conditions that are currently available [here](https://www.javadoc.io/doc/org.contextmapper/context-mapper-archunit-extension/latest/org/contextmapper/archunit/ContextMapperArchConditions.html).

## Available Rules and Conditions
_Hint:_ The available rules are implemented with the jMolecules DDD annotations. However, you can implement the same rules with your
own set of annotations or interfaces by using the corresponding ArchUnit conditions (as documented above).

| Rule / Condition                                    | Description                                                                                                                                                                                                                                                                     |
|-----------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `aggregatesShouldBeModeledInCML`                    | Aggregates that are implemented in the code (for example annotated with @AggregateRoot jMolecules annotation) shall exist in the CML Bounded Context as well.                                                                                                                   |
| `modulesShouldBeModeledInCML`                       | Modules that are implemented in the code (for example annotated with @Module jMolecules annotation) shall exist in the CML Bounded Context as well.                                                                                                                             |
| `entitiesShouldBeModeledInCML`                      | Entities that are implemented in the code (for example annotated with @Entity jMolecules annotation) shall exist in the CML Bounded Context as well.                                                                                                                            |
| `valueObjectsShouldBeModeledInCML`                  | Value Objects that are implemented in the code (for example annotated with @ValueObject jMolecules annotation) shall exist in the CML Bounded Context as well.                                                                                                                  |
| `domainEventsShouldBeModeledInCML`                  | Domain events that are implemented in the code (for example annotated with @DomainEvent jMolecules annotation) shall exist int the CML Bounded Context as well.                                                                                                                 |
| `servicesShouldBeModeledInCML`                      | Services that are implemented in the code (for example annotated with @Service jMolecules annotation) shall exist in the CML Bounded Context as well.                                                                                                                           |
| `repositoriesShouldBeModeledInCML`                  | Repositories that are implemented in the code (for example annotated with @Repository annotation) shall exist in the CML Bounded Context as well.                                                                                                                               |
| `aggregatesShouldAdhereToCmlAggregateStructure`     | This rule ensures that an Aggregate in the code (basically a Java package with a marker on the aggregate root entity; for example annotated with @AggregateRoot jMolecules annotation) consists of the same entities, value objects, and domain events as it is modeled in CML. |
| `entitiesShouldAdhereToCmlEntityStructure`          | This rule ensures that the fields of an entity in the code (for example annotated with @Entity jMolecules annotation) are also modeled in the corresponding CML entity.                                                                                                         |
| `valueObjectsShouldAdhereToCmlValueObjectStructure` | This rule ensures that the fields of a value object in the code (for example annotated with @ValueObject jMolecules annotation) are also modeled in the corresponding CML value object.                                                                                         |
| `domainEventsShouldAdhereToCmlDomainEventStructure` | This rule ensures that the fields of a domain event in the code (for example annotated with @DomainEvent jMolecules annotation) are also modeled in the corresponding CML domain event.                                                                                         |

The rules and conditions are documented in [our JavaDoc](https://www.javadoc.io/doc/org.contextmapper/context-mapper-archunit-extension/latest/index.html) as well.

**Missing some rules/conditions?** Contributions are always welcome! Create PRs or GitHub issues with your ideas/requirements.

## Contributing
Contribution is always welcome! Here are some ways how you can contribute:
 * Create Github issues if you find bugs or just want to give suggestions for improvements.
 * This is an open source project: if you want to code, [create pull requests](https://help.github.com/articles/creating-a-pull-request/) from [forks of this repository](https://help.github.com/articles/fork-a-repo/). Please refer to a Github issue if you contribute this way.
 * If you want to contribute to our documentation and user guides on our website [https://contextmapper.org/](https://contextmapper.org/), create pull requests from forks of the corresponding page repo [https://github.com/ContextMapper/contextmapper.github.io](https://github.com/ContextMapper/contextmapper.github.io) or create issues [there](https://github.com/ContextMapper/contextmapper.github.io/issues).

## Licence
ContextMapper is released under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
