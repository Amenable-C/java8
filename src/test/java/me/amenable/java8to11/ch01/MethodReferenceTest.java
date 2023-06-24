package me.amenable.java8to11.ch01;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MethodReferenceTest {

    @DisplayName("스테틱 메소드 참조")
    @Test
    void referenceWithStaticMethod() {
        // given
        UnaryOperator<String> hi = Greeting::hi;

        // when
        String greetingWithHi = hi.apply("amenable");

        // then
        assertThat(greetingWithHi).isEqualTo("hi amenable");
    }

    @DisplayName("특정 객체의 인스턴스 메서드 참조")
    @Test
    void referenceWithInstanceMethod() {
        // given
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;

        // when
        String greetingWithHello = hello.apply("amenable");

        // then
        assertThat(greetingWithHello).isEqualTo("hello amenable");
    }

    @DisplayName("생성자 참조")
    @Test
    void referenceWithConstructor() {
        // given
        Function<String, Greeting> greeting = Greeting::new;

        // when
        String name = greeting.apply("amenable").getName();

        // then
        assertThat(name).isEqualTo("amenable");
    }

    @DisplayName("임의 객체에 인스턴스 메소드 참조")
    @Test
    void arraySorting() {
        // given
        String[] names = {"jay", "amenable", "immortal", };

        // when
        Arrays.sort(names, String::compareToIgnoreCase);

        // then
        assertThat(names).containsExactly("amenable", "immortal", "jay");
    }

}
