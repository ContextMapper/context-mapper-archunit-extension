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

import org.contextmapper.archunit.annotations.TacticDDDAnnotationSet;
import org.contextmapper.archunit.conditions.*;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;

public class ContextMapperArchConditions {

    private ContextMapperArchConditions() {
    }

    /**
     * Checks whether a class, given by its simple name, is represented as an Aggregate in CML.
     *
     * @param cmlContext the CML Bounded Context within which the check searches for the Aggregates
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static ModeledAsAggregateInContext beModeledAsAggregatesInCML(BoundedContext cmlContext) {
        return ModeledAsAggregateInContext.beModeledAsAggregatesInCML(cmlContext);
    }

    /**
     * Checks whether a class, given by its simple name, is represented as an Entity in CML.
     *
     * @param cmlContext the CML Bounded Context within which the check searches for the Entities
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static ModeledAsEntityInContext beModeledAsEntityInCML(BoundedContext cmlContext) {
        return ModeledAsEntityInContext.beModeledAsEntityInCML(cmlContext);
    }

    /**
     * Checks whether a class, given by its simple name, is represented as a Value Object in CML.
     *
     * @param cmlContext the CML Bounded Context within which the check searches for the Value Objects
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static ModeledAsValueObjectInContext beModeledAsValueObjectInCML(BoundedContext cmlContext) {
        return ModeledAsValueObjectInContext.beModeledAsValueObjectInCML(cmlContext);
    }

    /**
     * Checks whether a module (a package-info.java class with an @Module jMolecules annotation that sets the module name)
     * is represented as a corresponding module in CML.
     * <p>
     * Limitation: this rule works with the jMolecules annotation only!
     *
     * @param cmlContext the CML Bounded Context within which the check searches for the Module
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static ModeledAsModuleInContext beModeledAsModulesInCML(BoundedContext cmlContext) {
        return ModeledAsModuleInContext.beModeledAsModulesInCML(cmlContext);
    }

    /**
     * Checks whether a class, given by its simple name, is represented as a Domain Event in CML.
     *
     * @param cmlContext the CML Bounded Context within which the check searches for the Domain Events
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static ModeledAsDomainEventInContext beModeledAsDomainEventInCML(BoundedContext cmlContext) {
        return ModeledAsDomainEventInContext.beModeledAsDomainEventInCML(cmlContext);
    }

    /**
     * Checks whether a class, given by its simple name, is represented as a service in CML.
     *
     * @param cmlContext the CML Bounded Context within which the check searches for the services
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static ModeledAsServiceInContext beModeledAsServiceInCML(BoundedContext cmlContext) {
        return ModeledAsServiceInContext.beModeledAsServiceInCML(cmlContext);
    }

    /**
     * Checks whether a class, given by its simple name, is represented as a repository in CML.
     *
     * @param boundedContext the CML Bounded Context within which the check searches for the repositories
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static ModeledAsRepositoryInContext beModeledAsRepositoryInCML(BoundedContext boundedContext) {
        return ModeledAsRepositoryInContext.beModeledAsRepositoryInCML(boundedContext);
    }

    /**
     * Checks whether an Java package representing an Aggregate, contains the same objects (Entities, Value Objects, Domain Events)
     * as the corresponding Aggregate in CML.
     *
     * @param boundedContext         the CML Bounded Context within which the check searches for the Aggregates
     * @param tacticDDDAnnotationSet the set of Java annotations that is used to identify entities, value objects, and domain events within
     *                               the package. Provide an implementation of the {@link TacticDDDAnnotationSet} interface (
     *                               custom or, for example, {@link org.contextmapper.archunit.annotations.JMoleculesTacticAnnotationSet}).
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaPackage}
     */
    public static AdhereToCmlAggregateStructure adhereToCmlAggregateStructure(BoundedContext boundedContext,
                                                                              TacticDDDAnnotationSet tacticDDDAnnotationSet) {
        return AdhereToCmlAggregateStructure.adhereToCmlAggregateStructure(boundedContext, tacticDDDAnnotationSet);
    }

    /**
     * Checks whether a classes fields are modeled in a CML entity.
     *
     * @param boundedContext the CML Bounded Context within which the check searches for the corresponding entities (by the classes simple names)
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static AdhereToCmlEntityStructure adhereToCmlEntityStructure(BoundedContext boundedContext) {
        return AdhereToCmlEntityStructure.adhereToCmlEntityStructure(boundedContext);
    }

    /**
     * Checks whether a classes fields are modeled in a CML value object.
     *
     * @param boundedContext the CML Bounded Context within which the check searches for the corresponding value objects (by the classes simple names)
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static AdhereToCmlValueObjectStructure adhereToCmlValueObjectStructure(BoundedContext boundedContext) {
        return AdhereToCmlValueObjectStructure.adhereToCmlValueObjectStructure(boundedContext);
    }

    /**
     * Checks whether a classes fields are modeled in a CML domain event.
     *
     * @param boundedContext the CML Bounded Context within which the check searches for the corresponding domain events (by the classes simple names)
     * @return returns an {@link com.tngtech.archunit.lang.ArchCondition} for a {@link com.tngtech.archunit.core.domain.JavaClass}
     */
    public static AdhereToCmlDomainEventStructure adhereToCmlDomainEventStructure(BoundedContext boundedContext) {
        return AdhereToCmlDomainEventStructure.adhereToCmlDomainEventStructure(boundedContext);
    }

}
