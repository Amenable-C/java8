package me.amenable.java8to11.ch04;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OptionalApiTest {

    @DisplayName("Optional에 값이 있는 경우")
    @Test
    void isPresent() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        OnlineClass restApiDevelopmentClass = new OnlineClass(3, "rest api development", false);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass, restApiDevelopmentClass);

        // when
        Optional<OnlineClass> springClass = springClasses.stream()
            .filter(oc -> oc.getTitle().startsWith("spring"))
            .findFirst();

        // then
        assertThat(springClass).isPresent();
    }

    @DisplayName("Optional에 값이 없는 경우")
    @Test
    void isEmpty() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        OnlineClass restApiDevelopmentClass = new OnlineClass(3, "rest api development", false);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass, restApiDevelopmentClass);

        // when
        Optional<OnlineClass> jpaClass = springClasses.stream()
            .filter(oc -> oc.getTitle().startsWith("jpa"))
            .findFirst();

        // then
        assertThat(jpaClass).isEmpty();
    }

    @DisplayName("JPA 수업이 없는 경우 JPA 수업을 만든다.")
    @Test
    void createJpaClass() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        OnlineClass restApiDevelopmentClass = new OnlineClass(3, "rest api development", false);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass, restApiDevelopmentClass);

        // when
        Optional<OnlineClass> jpaClass = springClasses.stream()
            .filter(oc -> oc.getTitle().startsWith("jpa"))
            .findFirst();

        // then
        OnlineClass newJpaClass = jpaClass.orElse(new OnlineClass(4, "jpa class", false));

        assertThat(newJpaClass.getTitle()).isEqualTo("jpa class");
    }

    @DisplayName("JPA 수업이 없는 경우 예외를 던진다.")
    @Test
    void throwException() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        OnlineClass restApiDevelopmentClass = new OnlineClass(3, "rest api development", false);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass, restApiDevelopmentClass);

        // when
        Optional<OnlineClass> jpaClass = springClasses.stream()
            .filter(oc -> oc.getTitle().startsWith("jpa"))
            .findFirst();

        // then
        assertThatThrownBy(() -> jpaClass.orElseThrow(IllegalArgumentException::new))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수업의 id 값이 10보다 크지 않아서 optional은 empty이다.")
    @Test
    void filterAndEmpty() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        OnlineClass restApiDevelopmentClass = new OnlineClass(3, "rest api development", false);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass, restApiDevelopmentClass);

        Stream<OnlineClass> springClass = springClasses.stream()
            .filter(oc -> oc.getTitle().startsWith("spring"));

        // when
        Stream<OnlineClass> optional = springClass.filter(oc -> oc.getId() > 10);

        // then
        assertThat(optional).isEmpty();
    }
}
