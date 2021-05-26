package org.contextmapper.archunit.annotations;

import org.jmolecules.ddd.annotation.Module;
import org.jmolecules.ddd.annotation.*;
import org.jmolecules.event.annotation.DomainEvent;

import java.lang.annotation.Annotation;

public class JMoleculesTacticAnnotationSet implements TacticDDDAnnotationSet {

    @Override
    public Class<? extends Annotation> aggregateRootAnnotation() {
        return AggregateRoot.class;
    }

    @Override
    public Class<? extends Annotation> entityAnnotation() {
        return Entity.class;
    }

    @Override
    public Class<? extends Annotation> valueObjectAnnotation() {
        return ValueObject.class;
    }

    @Override
    public Class<? extends Annotation> domainEventAnnotation() {
        return DomainEvent.class;
    }

    @Override
    public Class<? extends Annotation> serviceAnnotation() {
        return Service.class;
    }

    @Override
    public Class<? extends Annotation> moduleAnnotation() {
        return Module.class;
    }

    @Override
    public Class<? extends Annotation> repositoryAnnotation() {
        return Repository.class;
    }

    public static TacticDDDAnnotationSet instance() {
        return new JMoleculesTacticAnnotationSet();
    }

}
