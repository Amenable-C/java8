package me.amenable.java8to11.ch06;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadTest {

    @DisplayName("Callable 리턴 값 받아오기")
    @Test
    void getReturnValue() throws ExecutionException, InterruptedException {
        // given
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> "hello";

        Future<String> helloFuture = executorService.submit(hello);

        // when
        String str = helloFuture.get();

        // then
        Assertions.assertThat(str).isEqualTo("hello");
    }

    @DisplayName("Callable 리턴 값들 받아오기")
    @Test
    void getReturnValues() throws InterruptedException {
        // given
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(3000L);
            return "hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(2000L);
            return "java";
        };

        Callable<String> choi = () -> {
            Thread.sleep(1000L);
            return "choi";
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, choi));

        // when
        List<String> collect = futures.stream()
            .map(r -> {
                try {
                    return r.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            })
            .collect(Collectors.toList());

        // then
        Assertions.assertThat(collect)
            .containsExactlyInAnyOrder("hello", "java", "choi");
    }

    @DisplayName("Callable을 이용하여 가장 빨리 가져올 수 있는 값을 가져오기")
    @Test
    void getReturnFastestValue() throws ExecutionException, InterruptedException {
        // given
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> hello = () -> {
            Thread.sleep(3000L);
            return "hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(2000L);
            return "java";
        };

        Callable<String> choi = () -> {
            Thread.sleep(1000L);
            return "choi";
        };

        // when
        String result = executorService.invokeAny(Arrays.asList(hello, java, choi));

        // then
        Assertions.assertThat(result).isEqualTo("choi");
    }
}
