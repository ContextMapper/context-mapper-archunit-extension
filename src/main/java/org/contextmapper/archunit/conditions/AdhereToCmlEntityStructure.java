package org.contextmapper.archunit.conditions;

import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.contextmapper.tactic.dsl.tacticdsl.DomainObject;
import org.contextmapper.tactic.dsl.tacticdsl.Entity;

public class AdhereToCmlEntityStructure extends AdhereToCmlDomainObjectStructure {

    private AdhereToCmlEntityStructure(String description, BoundedContext cmlContext) {
        super(description, cmlContext);
    }

    @Override
    protected Class<? extends DomainObject> getDomainObjectType() {
        return Entity.class;
    }

    public static AdhereToCmlEntityStructure adhereToCmlEntityStructure(BoundedContext cmlContext) {
        return new AdhereToCmlEntityStructure("adhere to CML entity structure.", cmlContext);
    }

}
