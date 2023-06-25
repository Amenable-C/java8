package me.amenable.java8to11.ch03;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StreamTest {

    @DisplayName("중계 오퍼레이션은 근본적으로 Lazy하다.")
    @Test
    void lazy() {
        // given
        List<String> names = Arrays.asList("amenable", "immortal", "jay", "choi");
        StringBuilder stringBuilder = new StringBuilder();

        // when // then
        Stream<String> stringStream = names.stream().map((s) -> {
            stringBuilder.append("lazy");
            return s.toUpperCase();
            });
        assertThat(stringBuilder.length()).isZero();

        List<String> collect = stringStream.collect(Collectors.toList());
        assertThat(stringBuilder.length()).isNotZero();
        assertThat(collect).containsExactlyInAnyOrder("AMENABLE", "IMMORTAL", "JAY", "CHOI");
    }

    @DisplayName("stream 병렬 처리")
    @Test
    void parallelStream() {
        // given
        List<String> names = Arrays.asList("amenable", "immortal", "jay", "choi");

        // when
        List<String> collectByOriginStream = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> collectByParallelStream = names.parallelStream().map(String::toUpperCase).collect(Collectors.toList());

        // then
        assertThat(collectByOriginStream).isEqualTo(collectByParallelStream);
    }
}
