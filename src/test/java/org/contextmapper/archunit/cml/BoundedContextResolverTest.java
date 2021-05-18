package org.contextmapper.archunit.cml;

import org.contextmapper.dsl.contextMappingDSL.BoundedContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BoundedContextResolverTest {

    @Test
    void resolveBoundedContextFromModel_WhenBoundedContextExists_ThenReturnIt() {
        // given
        final String cml = "src/test/cml/test.cml";
        final String bcName = "SampleContext";

        // when
        final BoundedContext bc = new BoundedContextResolver().resolveBoundedContextFromModel(cml, bcName);

        // then
        assertThat(bc).isNotNull();
        assertThat(bc.getName()).isEqualTo(bcName);
    }

    @Test
    void resolveBoundedContextFromModel_WhenBoundedContextNotExists_ThenThrowException() {
        // given
        final String cml = "src/test/cml/test.cml";
        final String bcName = "NotExistingContext";

        // when, then
        assertThatExceptionOfType(BoundedContextDoesNotExistException.class).isThrownBy(() ->
                new BoundedContextResolver().resolveBoundedContextFromModel(cml, bcName));
    }

}
