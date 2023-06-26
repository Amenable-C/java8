package me.amenable.java8to11.ch07;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParallelSortingTest {

    @DisplayName("parallelSort를 사용하기")
    @Test
    void parallelSorting() {
        // given
        int[] numbers = new int[]{4, 5, 1, 3, 2};

        // when
        Arrays.parallelSort(numbers);

        // then
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5);
    }
}
