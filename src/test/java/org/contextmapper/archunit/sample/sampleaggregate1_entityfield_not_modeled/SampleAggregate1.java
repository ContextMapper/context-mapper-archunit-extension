package org.contextmapper.archunit.sample.sampleaggregate1_entityfield_not_modeled;

import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Entity;

@AggregateRoot
@Entity
public class SampleAggregate1 {

    private String notModeledField;

}
