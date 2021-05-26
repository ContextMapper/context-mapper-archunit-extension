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

    public static ModeledAsAggregateInContext beModeledAsAggregatesInCML(BoundedContext cmlContext) {
        return ModeledAsAggregateInContext.beModeledAsAggregatesInCML(cmlContext);
    }

    public static ModeledAsEntityInContext beModeledAsEntityInCML(BoundedContext cmlContext) {
        return ModeledAsEntityInContext.beModeledAsEntityInCML(cmlContext);
    }

    public static ModeledAsValueObjectInContext beModeledAsValueObjectInCML(BoundedContext cmlContext) {
        return ModeledAsValueObjectInContext.beModeledAsValueObjectInCML(cmlContext);
    }

    public static ModeledAsModuleInContext beModeledAsModulesInCML(BoundedContext cmlContext) {
        return ModeledAsModuleInContext.beModeledAsModulesInCML(cmlContext);
    }

    public static ModeledAsDomainEventInContext beModeledAsDomainEventInCML(BoundedContext cmlContext) {
        return ModeledAsDomainEventInContext.beModeledAsDomainEventInCML(cmlContext);
    }

    public static ModeledAsServiceInContext beModeledAsServiceInCML(BoundedContext cmlContext) {
        return ModeledAsServiceInContext.beModeledAsServiceInCML(cmlContext);
    }

    public static ModeledAsRepositoryInContext beModeledAsRepositoryInCML(BoundedContext boundedContext) {
        return ModeledAsRepositoryInContext.beModeledAsRepositoryInCML(boundedContext);
    }

    public static AdhereToCmlAggregateStructure adhereToCmlAggregateStructure(BoundedContext boundedContext,
                                                                              TacticDDDAnnotationSet tacticDDDAnnotationSet) {
        return AdhereToCmlAggregateStructure.adhereToCmlAggregateStructure(boundedContext, tacticDDDAnnotationSet);
    }

}
