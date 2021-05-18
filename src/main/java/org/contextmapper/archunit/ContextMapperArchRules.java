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

import com.tngtech.archunit.lang.ArchRule;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;

import static org.contextmapper.archunit.ContextMapperArchConditions.*;
import static org.contextmapper.archunit.conjunctions.JMoleculesClassConjunctions.*;

public class ContextMapperArchRules {

    /**
     * Ensures that Aggregates in the code (classes annotated with @AggregateRoot) are modeled as Aggregates in CML.
     *
     * @param boundedContext the Bounded Context within which the Aggregate should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule aggregateClassesShouldBeModeledInCml(final BoundedContext boundedContext) {
        return aggregateClasses.should(beModeledAsAggregatesInCML(boundedContext));
    }

    /**
     * Ensures that entities in the code (classes annotated with @Entity) are modeled as entities in CML.
     *
     * @param boundedContext the Bounded Context within which the Aggregate should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule entityClassesShouldBeModeledInCml(final BoundedContext boundedContext) {
        return entityClasses.should(beModeledAsEntityInCML(boundedContext));
    }

    /**
     * Ensures that value objects in the code (classes annotated with @ValueObject) are modeled as value objects in CML.
     *
     * @param boundedContext the Bounded Context within which the Aggregate should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule valueObjectClassesShouldBeModeledInCml(final BoundedContext boundedContext) {
        return valueObjectClasses.should(beModeledAsValueObjectInCML(boundedContext));
    }

}
