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
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.contextmapper.tactic.dsl.tacticdsl.Attribute;
import org.contextmapper.tactic.dsl.tacticdsl.DomainObject;
import org.contextmapper.tactic.dsl.tacticdsl.Reference;
import org.eclipse.xtext.EcoreUtil2;

import java.util.Optional;

public abstract class AdhereToCmlDomainObjectStructure extends ArchCondition<JavaClass> {

    private final BoundedContext cmlContext;

    protected AdhereToCmlDomainObjectStructure(String description, BoundedContext cmlContext) {
        super(description, new Object[0]);
        this.cmlContext = cmlContext;
    }

    @Override
    public void check(JavaClass javaClass, ConditionEvents events) {
        Optional<? extends DomainObject> optionalObject = findDomainObject(javaClass, events);
        if (!optionalObject.isPresent())
            return;

        DomainObject cmlObject = optionalObject.get();
        for (JavaField field : javaClass.getFields()) {
            Optional<Attribute> cmlField = cmlObject.getAttributes().stream()
                    .filter(f -> f.getName().equals(field.getName()))
                    .findAny();
            Optional<Reference> cmlReference = cmlObject.getReferences().stream()
                    .filter(r -> r.getName().equals(field.getName()))
                    .findAny();
            events.add(new SimpleConditionEvent(javaClass, cmlField.isPresent() || cmlReference.isPresent(),
                    String.format("The %s '%s' does not have a field or reference called '%s' in CML.",
                            getDomainObjectType().getSimpleName(), javaClass.getSimpleName(), field.getName())));
        }
    }

    protected abstract Class<? extends DomainObject> getDomainObjectType();

    private Optional<? extends DomainObject> findDomainObject(JavaClass javaClass, ConditionEvents events) {
        Optional<? extends DomainObject> object = EcoreUtil2.eAllOfType(cmlContext, getDomainObjectType()).stream()
                .filter(o -> o.getName().equals(javaClass.getSimpleName()))
                .findAny();
        events.add(new SimpleConditionEvent(javaClass, object.isPresent(),
                String.format("The %s '%s' is not modeled in CML.", getDomainObjectType().getSimpleName(), javaClass.getSimpleName())));
        return object;
    }

}
