package org.contextmapper.archunit.conditions;

import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.contextmapper.tactic.dsl.tacticdsl.DomainEvent;
import org.contextmapper.tactic.dsl.tacticdsl.DomainObject;

public class AdhereToCmlDomainEventStructure extends AdhereToCmlDomainObjectStructure {

    private AdhereToCmlDomainEventStructure(String description, BoundedContext cmlContext) {
        super(description, cmlContext);
    }

    @Override
    protected Class<? extends DomainObject> getDomainObjectType() {
        return DomainEvent.class;
    }

    public static AdhereToCmlDomainEventStructure adhereToCmlDomainEventStructure(BoundedContext cmlContext) {
        return new AdhereToCmlDomainEventStructure("adhere to CML domain event structure.", cmlContext);
    }

}
