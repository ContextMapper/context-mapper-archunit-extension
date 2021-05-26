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
import org.contextmapper.archunit.annotations.JMoleculesTacticAnnotationSet;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.all;
import static org.contextmapper.archunit.ContextMapperArchConditions.*;
import static org.contextmapper.archunit.conjunctions.JMoleculesClassConjunctions.*;
import static org.contextmapper.archunit.transformers.JMoleculesAggregatePackageTransformer.aggregatePackages;

public class ContextMapperJMoleculesArchRules {

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
     * Ensures that modules in the code (package-info.java classes annotated with @Module) are modeled as modules in CML.
     *
     * @param boundedContext the Bounded Context within which the Module should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule modulePackagesShouldBeModeledInCml(final BoundedContext boundedContext) {
        return moduleClasses.should(beModeledAsModulesInCML(boundedContext));
    }

    /**
     * Ensures that entities in the code (classes annotated with @Entity) are modeled as entities in CML.
     *
     * @param boundedContext the Bounded Context within which the Entity should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule entityClassesShouldBeModeledInCml(final BoundedContext boundedContext) {
        return entityClasses.should(beModeledAsEntityInCML(boundedContext));
    }

    /**
     * Ensures that value objects in the code (classes annotated with @ValueObject) are modeled as value objects in CML.
     *
     * @param boundedContext the Bounded Context within which the Value Object should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule valueObjectClassesShouldBeModeledInCml(final BoundedContext boundedContext) {
        return valueObjectClasses.should(beModeledAsValueObjectInCML(boundedContext));
    }

    /**
     * Ensures that domain events in the code (classes annotated with @DomainEvent) are modeled as domain events in CML.
     *
     * @param boundedContext the Bounded Context within which the domain event should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule domainEventClassesShouldBeModeledInCml(final BoundedContext boundedContext) {
        return domainEventClasses.should(beModeledAsDomainEventInCML(boundedContext));
    }

    /**
     * Ensures that services in the code (classes annotated with @Service) are modeled as services in CML.
     *
     * @param boundedContext the Bounded Context within which the service should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule serviceClassesShouldBeModeledInCml(final BoundedContext boundedContext) {
        return serviceClasses.should(beModeledAsServiceInCML(boundedContext));
    }

    /**
     * Ensures that repositories in the code (classes annotated with @Repository) are modeled as repositories in CML.
     *
     * @param boundedContext the Bounded Context within which the repository should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule repositoryClassesShouldBeModeledInCml(final BoundedContext boundedContext) {
        return repositoryClasses.should(beModeledAsRepositoryInCML(boundedContext));
    }

    /**
     * Ensures that the classes (entities, value objects, and domain events) inside an Aggregates package are also
     * contained in the corresponding Aggregate of the CML model.
     *
     * @param boundedContext the Bounded Context within which the Aggregate should be modelled
     * @return returns an ArchRule object
     */
    public static ArchRule aggregatesShouldAdhereToCmlStructure(final BoundedContext boundedContext) {
        return all(aggregatePackages).should(adhereToCmlAggregateStructure(boundedContext, JMoleculesTacticAnnotationSet.instance()));
    }

    /**
     * Ensures that entities in the code (classes annotated with @Entity) only contain fields that are modeled in the
     * corresponding CML entities as well.
     *
     * @param boundedContext the Bounded Context within which entities shall be checked
     * @return returns an ArchRule object
     */
    public static ArchRule entitiesShouldAdhereToCmlEntityStructure(final BoundedContext boundedContext) {
        return entityClasses.should(adhereToCmlEntityStructure(boundedContext));
    }

    /**
     * Ensures that value objects in the code (classes annotated with @ValueObject) only contain fields that are modeled
     * in the corresponding CML value objects as well.
     *
     * @param boundedContext the Bounded Context within which value objects shall be checked
     * @return returns an ArchRule object
     */
    public static ArchRule valueObjectsShouldAdhereToCmlValueObjectStructure(final BoundedContext boundedContext) {
        return valueObjectClasses.should(adhereToCmlValueObjectStructure(boundedContext));
    }

    /**
     * Ensures that domain events in the code (classes annotated with @DomainEvent) only contain fields that are modeled
     * in the corresponding CML domain events as well.
     *
     * @param boundedContext the Bounded Context within which domain events shall be checked
     * @return return an ArchRule object
     */
    public static ArchRule domainEventsShouldAdhereToCmlDomainEventStructure(final BoundedContext boundedContext) {
        return domainEventClasses.should(adhereToCmlDomainEventStructure(boundedContext));
    }

}
