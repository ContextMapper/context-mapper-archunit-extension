/*
 * Copyright 2021 The Context Mapper Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.contextmapper.archunit.conditions;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import com.tngtech.archunit.lang.ClassesTransformer;
import org.contextmapper.archunit.annotations.JMoleculesTacticAnnotationSet;
import org.contextmapper.archunit.cml.BoundedContextResolver;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.contextmapper.archunit.ContextMapperArchConditions.adhereToCmlAggregateStructure;
import static org.contextmapper.archunit.ContextMapperJMoleculesArchRules.aggregatesShouldAdhereToCmlStructure;

class AdhereToCmlAggregateStructureTest {

    private BoundedContext sampleContext;

    @BeforeEach
    public void setup() {
        this.sampleContext = new BoundedContextResolver()
                .resolveBoundedContextFromModel("src/test/cml/test.cml", "SampleContext");
    }

    @Test
    void check_WhenThereIsNoAggregateRootAnnotation_ThenAddEvent() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.samplemodule1");

        // when, then
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
            all(packages).should(adhereToCmlAggregateStructure(sampleContext, JMoleculesTacticAnnotationSet.instance()))
                    .check(classes);
        });
    }

    @Test
    void check_WhenTheAggregateDoesNotExistInCML_ThenAddEvent() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.sampleaggregate_notmodeled");

        // when, then
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
            all(packages).should(adhereToCmlAggregateStructure(sampleContext, JMoleculesTacticAnnotationSet.instance()))
                    .check(classes);
        });
    }

    @Test
    void check_WhenAnAggregatesEntityIsNotModeled_ThenAddEvent() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.sampleaggregate1_entity_not_modeled");

        // when, then
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
            all(packages).should(adhereToCmlAggregateStructure(sampleContext, JMoleculesTacticAnnotationSet.instance()))
                    .check(classes);
        });
    }

    @Test
    void check_WhenAnAggregatesValueObjectIsNotModeled_ThenAddEvent() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.sampleaggregate1_vo_not_modeled");

        // when, then
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
            all(packages).should(adhereToCmlAggregateStructure(sampleContext, JMoleculesTacticAnnotationSet.instance()))
                    .check(classes);
        });
    }

    @Test
    void check_WhenAnAggregatesDomainEventIsNotModeled_ThenAddEvent() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.sampleaggregate1_domainevent_not_modeled");

        // when, then
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
            all(packages).should(adhereToCmlAggregateStructure(sampleContext, JMoleculesTacticAnnotationSet.instance()))
                    .check(classes);
        });
    }

    @Test
    void check_WhenAllObjectsAreModeled_ThenConditionIsFulfilled() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.sampleaggregate1");

        // when, then
        aggregatesShouldAdhereToCmlStructure(sampleContext).check(classes);
    }

    private static final ClassesTransformer<JavaPackage> packages = new AbstractClassesTransformer<JavaPackage>("packages") {
        @Override
        public Iterable<JavaPackage> doTransform(JavaClasses classes) {
            return classes.stream().map(c -> c.getPackage()).collect(Collectors.toSet());
        }
    };

}
