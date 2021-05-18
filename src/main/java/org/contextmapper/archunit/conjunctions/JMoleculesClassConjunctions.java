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
package org.contextmapper.archunit.conjunctions;

import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import org.jmolecules.ddd.annotation.Module;
import org.jmolecules.ddd.annotation.*;
import org.jmolecules.event.annotation.DomainEvent;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class JMoleculesClassConjunctions {

    public static final GivenClassesConjunction aggregateClasses = classes().that().areAnnotatedWith(AggregateRoot.class);

    public static final GivenClassesConjunction moduleClasses = classes().that().areAnnotatedWith(Module.class);

    public static final GivenClassesConjunction entityClasses = classes().that().areAnnotatedWith(Entity.class);

    public static final GivenClassesConjunction valueObjectClasses = classes().that().areAnnotatedWith(ValueObject.class);

    public static final GivenClassesConjunction domainEventClasses = classes().that().areAnnotatedWith(DomainEvent.class);

    public static final GivenClassesConjunction serviceClasses = classes().that().areAnnotatedWith(Service.class);

    public static final GivenClassesConjunction repositoryClasses = classes().that().areAnnotatedWith(Repository.class);

    public static final GivenClassesConjunction factoryClasses = classes().that().areAnnotatedWith(Factory.class);

}
