package Junit;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class LepesInvalidArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
            ExtensionContext context) throws Exception {

        return Stream.of(
                Arguments.of(-1, 3),
                Arguments.of(-1000, 5),
                Arguments.of(10, 9),
                Arguments.of(5, 5)
        );
    }
}
