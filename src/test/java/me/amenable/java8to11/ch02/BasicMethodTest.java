package me.amenable.java8to11.ch02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BasicMethodTest {

    @DisplayName("Iterable의 기본 메서드 - forEach()")
    @Test
    void addName() {
        // given
        String name1 = "amenable";
        String name2 = "immortal";
        String name3 = "jay";
        List<String> names = List.of(name1, name2, name3);

        String totalName = name1 + name2 + name3;

        StringBuilder stringBuilder = new StringBuilder();

        // when
        names.forEach(stringBuilder::append);

        // then
        assertThat(stringBuilder.toString()).isEqualTo(totalName);
    }

    @DisplayName("Iterable의 기본 메서드 - spliterator")
    @Test
    void countName() {
        // given
        List<String> names = List.of("amenable", "immortal", "jay");

        Spliterator<String> spliterator = names.spliterator();
        int size = 0;

        // when
        while(spliterator.tryAdvance((name) -> {})) {
            size++;
        }

        // then
        assertThat(size).isEqualTo(names.size());
    }

    @DisplayName("Iterable의 기본 메서드 - trySplit")
    @Test
    void splitNames() {
        // given
        List<String> threeName = List.of("amenable", "immortal", "jay");
        List<String> fourName = List.of("amenable", "immortal", "jay", "choi");

        int halfSizeForThree = 0, halfSizeForFour = 0;
        Spliterator<String> threeHalfSpliterator = threeName.spliterator().trySplit();
        Spliterator<String> fourHalfSpliterator = fourName.spliterator().trySplit();

        // when
        while(threeHalfSpliterator.tryAdvance((name) -> {})) {
            halfSizeForThree++;
        }

        while(fourHalfSpliterator.tryAdvance((name) -> {})) {
            halfSizeForFour++;
        }

        // then
        assertThat(halfSizeForThree).isEqualTo(1);
        assertThat(halfSizeForFour).isEqualTo(2);
    }

    @DisplayName("Collection의 기본 메서드 - stream()")
    @Test
    void getFilteredNames() {
        // given
        List<String> names = List.of("amenable", "immortal", "jay", "choi", "aTest");

        // when
        List<String> filteredNames = names.stream()
                                        .map(String::toUpperCase)
                                        .filter(s -> s.startsWith("A"))
                                        .collect(Collectors.toList());

        // then
        assertThat(filteredNames).containsExactlyInAnyOrder("AMENABLE", "ATEST");
    }

    @DisplayName("Collection의 기본 메서드 - removeIf")
    @Test
    void removeNameStartedWithA() {
        // given
        List<String> names = new ArrayList<>(Arrays.asList("amenable", "immortal", "choi", "aTest"));

        // when
        names.removeIf(s -> s.startsWith("a"));

        // then
        assertThat(names).containsExactlyInAnyOrder("immortal", "choi");
    }

    @DisplayName("Comparator의 기본 메소드 및 스테틱 메서드 - reversed()")
    @Test
    void reverseNames1() {
        // given
        List<String> names = Arrays.asList("amenable", "immortal", "jay", "choi");
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;

        // when
        names.sort(compareToIgnoreCase.reversed());

        // then
        assertThat(names).containsExactly("jay", "immortal", "choi", "amenable");
    }

    @DisplayName("Comparator의 기본 메소드 및 스테틱 메서드 - reversedOrder()")
    @Test
    void reverseNames2() {
        // given
        List<String> names = Arrays.asList("amenable", "immortal", "jay", "choi");

        // when
        names.sort(Comparator.reverseOrder());

        // then
        assertThat(names).containsExactly("jay", "immortal", "choi", "amenable");
    }

}
