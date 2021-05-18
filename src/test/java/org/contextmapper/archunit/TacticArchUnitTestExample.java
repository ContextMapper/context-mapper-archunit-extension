package org.contextmapper.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

class TacticArchUnitTestExample extends AbstractTacticArchUnitTest {

    @Override
    protected String getBoundedContextName() {
        return "SampleContext";
    }

    @Override
    protected String getCMLFilePath() {
        return "src/test/cml/test.cml";
    }

    @Override
    protected String getJavaPackageName2Test() {
        return "org.contextmapper.archunit.sample.sampleaggregate1";
    }

    @Override
    protected JavaClasses importClasses() {
        return new ClassFileImporter()
                .importPackages(getJavaPackageName2Test());
    }
}
