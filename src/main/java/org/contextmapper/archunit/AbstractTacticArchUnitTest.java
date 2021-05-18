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
