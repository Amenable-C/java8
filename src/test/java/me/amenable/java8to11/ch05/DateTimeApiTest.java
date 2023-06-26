package me.amenable.java8to11.ch05;

import static org.assertj.core.api.Assertions.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTimeApiTest {

    @DisplayName("시간 간격 구하기- ver.Human")
    @Test
    void getHumanTime() {
        // given
        LocalDate day1 = LocalDate.of(2023, Month.JUNE, 15);
        LocalDate day2 = LocalDate.of(2023, Month.JUNE, 20);


        // when
        Period period = Period.between(day1, day2);
        Period until = day1.until(day2);

        // then
        assertThat(period.getDays()).isEqualTo(5);
        assertThat(until.getDays()).isEqualTo(5);
    }

    @DisplayName("시간 간격 구하기- ver.Machine")
    @Test
    void getMachineTime() {
        // given
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);

        // when
        Duration between = Duration.between(now, plus);

        // then
        assertThat(between.getSeconds()).isEqualTo(10);
    }

    @DisplayName("Formatter 사용하기")
    @Test
    void formatter() {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2023, Month.JUNE, 26, 0, 0, 0);

        // when
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String day = localDateTime.format(MMddyyyy);

        // then
        assertThat(day).isEqualTo("06/26/2023");
    }

    @DisplayName("parse 사용해보기")
    @Test
    void test() {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2023, Month.JUNE, 26, 0, 0, 0);
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        // when
        LocalDate parse = LocalDate.parse("06/26/2023", MMddyyyy);

        // then
        assertThat(parse).isEqualTo(localDateTime.toLocalDate());
    }
}
