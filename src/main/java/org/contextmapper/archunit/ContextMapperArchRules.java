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
