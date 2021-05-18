package org.contextmapper.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.contextmapper.archunit.cml.BoundedContextResolver;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.contextmapper.archunit.ContextMapperArchRules.*;

/**
 * An abstract test class that can be extended in order to execute all our ArchRules for a single Bounded Context.
 */
public abstract class AbstractTacticArchUnitTest {

    protected BoundedContext context;
    protected JavaClasses classes;

    /**
     * Implement this method to define the Bounded Context name.
     */
    protected abstract String getBoundedContextName();

    /**
     * Implement this method to define against which CML file you want to test.
     */
    protected abstract String getCMLFilePath();

    /**
     * Implement this method to defined the java package within which you want to test.
     */
    protected abstract String getJavaPackageName2Test();

    @BeforeEach
    protected void setup() {
        this.context = new BoundedContextResolver()
                .resolveBoundedContextFromModel(getCMLFilePath(), getBoundedContextName());
        this.classes = importClasses();
    }

    /**
     * Override this method to change class import behavior.
     */
    protected JavaClasses importClasses() {
        return new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages(getJavaPackageName2Test());
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
