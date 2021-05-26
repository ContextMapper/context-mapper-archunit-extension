package org.contextmapper.archunit.transformers;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.util.stream.Collectors;

public class JMoleculesAggregatePackageTransformer extends AbstractClassesTransformer<JavaPackage> {

    protected JMoleculesAggregatePackageTransformer(String description) {
        super(description);
    }

    @Override
    public Iterable<JavaPackage> doTransform(JavaClasses javaClasses) {
        return javaClasses.stream()
                .filter(c -> c.isAnnotatedWith(AggregateRoot.class))
                .map(c -> c.getPackage())
                .collect(Collectors.toSet());
    }

    public static JMoleculesAggregatePackageTransformer aggregatePackages =
            new JMoleculesAggregatePackageTransformer("aggregate packages");

}
