package partTwo.test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import partTwo.main.TaskTwo;

import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

public class TaskTwoTest {
    @ParameterizedTest
    @MethodSource("arrayForFirstTestParametersProvider")
    public void testReturnArrayFromLastFour(int[] expected, int[] actual){
        assertArrayEquals(expected, TaskTwo.checkArrayWithFour(actual));
    }

    private static Stream<Arguments> arrayForFirstTestParametersProvider(){
        return Stream.of(
                Arguments.arguments(new int[]{5, 6}, new int[]{1, 2, 3, 4, 5, 6}),
                Arguments.arguments(new int[]{5}, new int[]{8, 5, 4, 4, 5, 4, 5}),
                Arguments.arguments(new int[]{2, 3, 2, 5, 6}, new int[]{4, 2, 3, 2, 5, 6}),
                Arguments.arguments(new int[]{}, new int[]{4, 2, 3, 2, 5, 4})
        );
    }

    @ParameterizedTest
    @MethodSource("arrayForSecondTestParametersProvider")
    public void testReturnTrueArrayWithOneOrFour(boolean expected, int[] actual){
        Assertions.assertEquals(expected, TaskTwo.checkArrayOneAndFour(actual));
    }

    private static Stream<Arguments> arrayForSecondTestParametersProvider(){
        return Stream.of(
                Arguments.arguments(true, new int[]{1, 2, 3}),
                Arguments.arguments(false, new int[]{5, 2, 3}),
                Arguments.arguments(false, new int[]{7, 2, 3, 10}),
                Arguments.arguments(true, new int[]{6, 4, 8})

        );
    }
}
