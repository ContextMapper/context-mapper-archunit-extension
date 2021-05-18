package org.contextmapper.archunit.conditions;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.contextmapper.archunit.cml.BoundedContextResolver;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.contextmapper.archunit.ContextMapperArchRules.entityClassesShouldBeModeledInCml;

class ModeledAsEntityInContextTest {

    private BoundedContext sampleContext;

    @BeforeEach
    public void setup() {
        this.sampleContext = new BoundedContextResolver()
                .resolveBoundedContextFromModel("src/test/cml/test.cml", "SampleContext");
    }

    @Test
    void check_WhenEntityIsModeled_ThenConditionIsFulfilled() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.sampleaggregate1");

        // when, then
        entityClassesShouldBeModeledInCml(sampleContext).check(classes);
    }

    @Test
    void check_WhenEntityIsNotModeled_ThenAddEvent() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.sampleentity_notmodeled");

        // when, then
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
                entityClassesShouldBeModeledInCml(sampleContext).check(classes));
    }

}
