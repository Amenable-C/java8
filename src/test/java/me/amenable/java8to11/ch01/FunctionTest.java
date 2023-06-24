package me.amenable.java8to11.ch01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FunctionTest {

    @DisplayName("Function 함수 인터페이스 테스트 - apply method")
    @Test
    void plus() {
        // given
        java.util.function.Function<Integer, Integer> plus10 = (number) -> number + 10;

        // when
        Integer result = plus10.apply(20);

        // then
        assertThat(result).isEqualTo(30);
    }

    @DisplayName("Function 함수 인터페이스 테스트 - apply method")
    @Test
    void multiply() {
        // given
        java.util.function.Function<Integer, Integer> multiply2 = (number) -> number * 2;

        // when
        Integer result = multiply2.apply(5);

        // then
        assertThat(result).isEqualTo(10);
    }

    @DisplayName("Function 함수 인터페이스 테스트 - compose method")
    @Test
    void multiplyAndPlus() {
        // given
        java.util.function.Function<Integer, Integer> plus10 = (number) -> number + 10;
        java.util.function.Function<Integer, Integer> multiply2 = (number) -> number * 2;
        java.util.function.Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);

        // when
        Integer result = multiply2AndPlus10.apply(2);

        // then
        assertThat(result).isEqualTo(14);
    }

    @DisplayName("Function 함수 인터페이스 테스트 - andThen method")
    @Test
    void plusAndThenMultiply() {
        // given
        java.util.function.Function<Integer, Integer> plus10 = (number) -> number + 10;
        java.util.function.Function<Integer, Integer> multiply2 = (number) -> number * 2;
        java.util.function.Function<Integer, Integer> plus10AndThenMultiply2 = plus10.andThen(multiply2);

        // when
        Integer result = plus10AndThenMultiply2.apply(2);

        // then
        assertThat(result).isEqualTo(24);
    }
    
    @DisplayName("BiFunction 함수 인터페이스")
    @Test
    void plusIntegerAndFloat() {
        // given
        BiFunction<Integer, Float, String> plusIntegerAndFloat = (numberInteger, numberFloat) -> {
            float ans = numberInteger + numberFloat;
            return String.format("%d + %.1f = %.1f", numberInteger, numberFloat, ans);
        };
        
        // when
        String result = plusIntegerAndFloat.apply(1, 1.2f);

        // then
        assertThat(result).isEqualTo("1 + 1.2 = 2.2");
    }

    @DisplayName("Supplier 함수 인터페이스")
    @Test
    void get10() {
        // given
        Supplier<Integer> get10 = () -> 10;

        // when
        Integer result = get10.get();

        // then
        assertThat(result).isEqualTo(10);
    }

    @DisplayName("Predicate 함수 인터페이스")
    @Test
    void isEven() {
        // given
        Predicate<Integer> isEven = (number) -> number % 2 == 0;

        // when // then
        assertThat(isEven.test(2)).isTrue();
        assertThat(isEven.test(3)).isFalse();
    }

    @DisplayName("UnaryOperator 함수 인터페이스")
    @Test
    void plus1() {
        // given
        UnaryOperator<Integer> plus1 = (number) -> number + 1;

        // when
        Integer result = plus1.apply(5);

        // then
        assertThat(result).isEqualTo(6);
    }

    @DisplayName("BinaryOperator 함수 인터페이스")
    @Test
    void plusAAndB() {
        // given
        BinaryOperator<Integer> plusAAndB = (numberA, numberB) -> numberA + numberB;

        // when
        Integer result = plusAAndB.apply(1, 2);

        // then
        assertThat(result).isEqualTo(3);
    }

}
