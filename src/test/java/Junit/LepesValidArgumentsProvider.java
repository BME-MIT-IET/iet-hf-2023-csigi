package Junit;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class LepesValidArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
            ExtensionContext context) throws Exception {

        return Stream.of(
                Arguments.of(0, 3),
                Arguments.of(4, 5),
                Arguments.of(3, 9)
        );
    }
}
