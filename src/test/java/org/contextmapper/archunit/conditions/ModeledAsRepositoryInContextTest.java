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
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.contextmapper.archunit.cml.BoundedContextResolver;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.contextmapper.archunit.ContextMapperArchRules.repositoryClassesShouldBeModeledInCml;

class ModeledAsRepositoryInContextTest {

    private BoundedContext sampleContext;

    @BeforeEach
    public void setup() {
        this.sampleContext = new BoundedContextResolver()
                .resolveBoundedContextFromModel("src/test/cml/test.cml", "SampleContext");
    }

    @Test
    void check_WhenRepositoryIsModeled_ThenConditionIsFulfilled() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.sampleaggregate1");

        // when, then
        repositoryClassesShouldBeModeledInCml(sampleContext).check(classes);
    }

    @Test
    void check_WhenRepositoryIsNotModeled_ThenAddEvent() {
        // given
        JavaClasses classes = new ClassFileImporter()
                .importPackages("org.contextmapper.archunit.sample.samplerepository_notmodeled");

        // when, then
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
                repositoryClassesShouldBeModeledInCml(sampleContext).check(classes));
    }

}
