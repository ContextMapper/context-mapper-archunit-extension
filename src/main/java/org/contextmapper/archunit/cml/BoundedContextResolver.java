package org.contextmapper.archunit.cml;

import org.contextmapper.dsl.cml.CMLResource;
import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.contextmapper.dsl.standalone.ContextMapperStandaloneSetup;
import org.contextmapper.dsl.standalone.StandaloneContextMapperAPI;
import org.eclipse.xtext.EcoreUtil2;

public class BoundedContextResolver {

    public BoundedContext resolveBoundedContextFromModel(final String modelPath, final String boundedContextName) {
        StandaloneContextMapperAPI api = ContextMapperStandaloneSetup.getStandaloneAPI();
        CMLResource cml = api.loadCML(modelPath);
        return EcoreUtil2.eAllOfType(cml.getContextMappingModel(), BoundedContext.class).stream()
                .filter(bc -> bc.getName().equals(boundedContextName))
                .findAny()
                .orElseThrow(() -> new BoundedContextDoesNotExistException(boundedContextName));
    }

}
