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
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.contextmapper.tactic.dsl.tacticdsl.ValueObject;
import org.eclipse.xtext.EcoreUtil2;

import java.util.List;

public class ModeledAsValueObjectInContext extends ArchCondition<JavaClass> {

    private final BoundedContext cmlContext;

    private ModeledAsValueObjectInContext(String description, BoundedContext cmlContext) {
        super(description, new Object[0]);
        this.cmlContext = cmlContext;
    }

    @Override
    public void check(JavaClass javaClass, ConditionEvents events) {
        List<ValueObject> valueObjects = EcoreUtil2.eAllOfType(cmlContext, ValueObject.class);
        events.add(new SimpleConditionEvent(javaClass, valueObjects.stream()
                .anyMatch(vo -> vo.getName().equals(javaClass.getSimpleName())),
                String.format("The value object '%s' is not modeled in CML.", javaClass.getSimpleName())));
    }

    public static ModeledAsValueObjectInContext beModeledAsValueObjectInCML(BoundedContext cmlContext) {
        return new ModeledAsValueObjectInContext("be modeled as value object in CML.", cmlContext);
    }

}
