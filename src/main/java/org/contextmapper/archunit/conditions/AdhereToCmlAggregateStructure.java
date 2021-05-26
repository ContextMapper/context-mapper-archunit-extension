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

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.contextmapper.archunit.annotations.TacticDDDAnnotationSet;
import org.contextmapper.dsl.contextMappingDSL.Aggregate;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.contextmapper.tactic.dsl.tacticdsl.DomainEvent;
import org.contextmapper.tactic.dsl.tacticdsl.DomainObject;
import org.contextmapper.tactic.dsl.tacticdsl.Entity;
import org.contextmapper.tactic.dsl.tacticdsl.ValueObject;
import org.eclipse.xtext.EcoreUtil2;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdhereToCmlAggregateStructure extends ArchCondition<JavaPackage> {

    private final BoundedContext cmlContext;
    private final TacticDDDAnnotationSet tacticDDDAnnotationTypes;

    private AdhereToCmlAggregateStructure(String description, BoundedContext cmlContext,
                                          TacticDDDAnnotationSet tacticDDDAnnotationTypes) {
        super(description, new Object[0]);
        this.cmlContext = cmlContext;
        this.tacticDDDAnnotationTypes = tacticDDDAnnotationTypes;
    }

    @Override
    public void check(JavaPackage javaPackage, ConditionEvents events) {
        Optional<JavaClass> aggregateRootClass = findAggregateRootClass(javaPackage, events);
        if (!aggregateRootClass.isPresent())
            return;

        Optional<Aggregate> cmlAggregate = findCmlAggregate(javaPackage, events, aggregateRootClass);
        if (!cmlAggregate.isPresent())
            return;

        checkEntities(cmlAggregate.get(), javaPackage, events);
        checkValueObjects(cmlAggregate.get(), javaPackage, events);
        checkDomainEvents(cmlAggregate.get(), javaPackage, events);
    }

    private void checkEntities(Aggregate cmlAggregate, JavaPackage javaPackage, ConditionEvents events) {
        checkDomainObject(cmlAggregate, javaPackage, events, this.tacticDDDAnnotationTypes.entityAnnotation(), Entity.class);
    }

    private void checkValueObjects(Aggregate cmlAggregate, JavaPackage javaPackage, ConditionEvents events) {
        checkDomainObject(cmlAggregate, javaPackage, events, this.tacticDDDAnnotationTypes.valueObjectAnnotation(), ValueObject.class);
    }

    private void checkDomainEvents(Aggregate cmlAggregate, JavaPackage javaPackage, ConditionEvents events) {
        checkDomainObject(cmlAggregate, javaPackage, events, this.tacticDDDAnnotationTypes.domainEventAnnotation(), DomainEvent.class);
    }

    private void checkDomainObject(Aggregate cmlAggregate, JavaPackage javaPackage, ConditionEvents events,
                                   Class<? extends Annotation> annotation, Class<? extends DomainObject> cmlType) {
        for (JavaClass domainObjectClass : javaPackage.getClasses().stream()
                .filter(c -> c.isAnnotatedWith(annotation))
                .collect(Collectors.toSet())) {
            Optional<? extends DomainObject> cmlObject = EcoreUtil2.eAllOfType(cmlAggregate, cmlType).stream()
                    .filter(domainObject -> domainObject.getName().equals(domainObjectClass.getSimpleName()))
                    .findAny();
            events.add(new SimpleConditionEvent(domainObjectClass, cmlObject.isPresent(),
                    String.format("The Aggregate '%s' does not contain a " + cmlType.getSimpleName() + " '%s' in CML.", cmlAggregate.getName(),
                            domainObjectClass.getSimpleName())));
        }
    }

    private Optional<Aggregate> findCmlAggregate(JavaPackage javaPackage, ConditionEvents events, Optional<JavaClass> aggregateRootClass) {
        Optional<Aggregate> cmlAggregate = EcoreUtil2.eAllOfType(cmlContext, Aggregate.class).stream()
                .filter(a -> a.getName().equals(aggregateRootClass.get().getSimpleName()))
                .findAny();
        events.add(new SimpleConditionEvent(javaPackage, cmlAggregate.isPresent(),
                String.format("The aggregate '%s' is not modeled in CML.", aggregateRootClass.get().getSimpleName())));
        return cmlAggregate;
    }

    private Optional<JavaClass> findAggregateRootClass(JavaPackage javaPackage, ConditionEvents events) {
        Optional<JavaClass> aggregateRootClass = javaPackage.getClasses().stream()
                .filter(c -> c.isAnnotatedWith(tacticDDDAnnotationTypes.aggregateRootAnnotation()))
                .findAny();
        events.add(new SimpleConditionEvent(javaPackage, aggregateRootClass.isPresent(),
                String.format("The aggregate root class for package '%s' cannot be found.", javaPackage.getName())));
        return aggregateRootClass;
    }

    public static AdhereToCmlAggregateStructure adhereToCmlAggregateStructure(BoundedContext cmlContext,
                                                                              TacticDDDAnnotationSet tacticDDDAnnotationTypes) {
        return new AdhereToCmlAggregateStructure("adhere to CML Aggregate structure.", cmlContext, tacticDDDAnnotationTypes);
    }

}
