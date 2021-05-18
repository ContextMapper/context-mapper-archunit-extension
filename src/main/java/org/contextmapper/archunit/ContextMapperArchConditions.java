package org.contextmapper.archunit;

import org.contextmapper.archunit.conditions.ModeledAsAggregateInContext;
import org.contextmapper.archunit.conditions.ModeledAsEntityInContext;
import org.contextmapper.archunit.conditions.ModeledAsValueObjectInContext;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;

public class ContextMapperArchConditions {

    public static ModeledAsAggregateInContext beModeledAsAggregatesInCML(BoundedContext cmlContext) {
        return ModeledAsAggregateInContext.beModeledAsAggregatesInCML(cmlContext);
    }

    public static ModeledAsEntityInContext beModeledAsEntityInCML(BoundedContext cmlContext) {
        return ModeledAsEntityInContext.beModeledAsEntityInCML(cmlContext);
    }

    public static ModeledAsValueObjectInContext beModeledAsValueObjectInCML(BoundedContext cmlContext) {
        return ModeledAsValueObjectInContext.beModeledAsValueObjectInCML(cmlContext);
    }

}
