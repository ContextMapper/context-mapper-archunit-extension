package org.contextmapper.archunit.conditions;

import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.contextmapper.tactic.dsl.tacticdsl.DomainObject;
import org.contextmapper.tactic.dsl.tacticdsl.ValueObject;

public class AdhereToCmlValueObjectStructure extends AdhereToCmlDomainObjectStructure {

    private AdhereToCmlValueObjectStructure(String description, BoundedContext cmlContext) {
        super(description, cmlContext);
    }

    @Override
    protected Class<? extends DomainObject> getDomainObjectType() {
        return ValueObject.class;
    }

    public static AdhereToCmlValueObjectStructure adhereToCmlValueObjectStructure(BoundedContext cmlContext) {
        return new AdhereToCmlValueObjectStructure("adhere to CML value object structure.", cmlContext);
    }

}
