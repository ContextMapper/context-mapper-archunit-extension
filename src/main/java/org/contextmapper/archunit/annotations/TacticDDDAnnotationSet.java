package org.contextmapper.archunit.annotations;

import java.lang.annotation.Annotation;

public interface TacticDDDAnnotationSet {

    Class<? extends Annotation> aggregateRootAnnotation();

    Class<? extends Annotation> entityAnnotation();

    Class<? extends Annotation> valueObjectAnnotation();

    Class<? extends Annotation> domainEventAnnotation();

    Class<? extends Annotation> serviceAnnotation();

    Class<? extends Annotation> moduleAnnotation();

    Class<? extends Annotation> repositoryAnnotation();

}
