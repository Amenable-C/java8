package me.amenable.java8to11.ch03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StreamApiTest {

    @DisplayName("스프링 수업 중에서 title이 spring으로 시작하는 수업을 가져온다.")
    @Test
    void getClassesStartedWithSpring() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        OnlineClass springMvcClass = new OnlineClass(3, "spring mvc", false);
        OnlineClass springCoreClass = new OnlineClass(4, "spring core", false);
        OnlineClass restApiDevelopmentClass = new OnlineClass(5, "rest api development", false);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass, springMvcClass, springCoreClass, restApiDevelopmentClass);

        // when
        List<OnlineClass> springClassesStartedWithSpring = springClasses.stream()
            .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
            .collect(Collectors.toList());

        // then
        assertThat(springClassesStartedWithSpring)
            .containsExactlyInAnyOrder(springBootClass, springDataJpaClass, springMvcClass, springCoreClass);
    }

    @DisplayName("수업들 중에서 close되지 않은 수업을 가져온다.")
    @Test
    void getClassesWithoutClosed() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        OnlineClass springMvcClass = new OnlineClass(3, "spring mvc", false);
        OnlineClass springCoreClass = new OnlineClass(4, "spring core", false);
        OnlineClass restApiDevelopmentClass = new OnlineClass(5, "rest api development", false);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass, springMvcClass, springCoreClass, restApiDevelopmentClass);

        // when
        List<OnlineClass> classWithoutClosed = springClasses.stream()
            .filter(Predicate.not(OnlineClass::isClosed))
            .collect(Collectors.toList());

        // then
        assertThat(classWithoutClosed)
            .containsExactly(springMvcClass, springCoreClass, restApiDevelopmentClass);
    }

    @DisplayName("수업 이름만 모아서 스트림을 만든다.")
    @Test
    void getTitles() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        OnlineClass springMvcClass = new OnlineClass(3, "spring mvc", false);
        OnlineClass springCoreClass = new OnlineClass(4, "spring core", false);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass, springMvcClass, springCoreClass);

        // when
        Stream<String> classesTitle = springClasses.stream()
            .map(OnlineClass::getTitle);

        // then
        assertThat(classesTitle)
            .containsExactlyInAnyOrder(
                springBootClass.getTitle(),
                springDataJpaClass.getTitle(),
                springMvcClass.getTitle(),
                springCoreClass.getTitle()
            );
    }

    @DisplayName("두 수업 목록에 들어있는 모든 수업 아이디 출력")
    @Test
    void sumClasses() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass);

        OnlineClass javaTestClass = new OnlineClass(3, "The Java, Test", true);
        OnlineClass javaCodeManipulationClass = new OnlineClass(4, "The Java, Code manipulation", true);
        List<OnlineClass> javaClasses = Arrays.asList(javaTestClass, javaCodeManipulationClass);

        List<List<OnlineClass>> amenableEvents = Arrays.asList(springClasses, javaClasses);

        // when
        Stream<Integer> onlineClassIds = amenableEvents.stream()
            .flatMap(Collection::stream)
            .map(OnlineClass::getId);

        // then
        assertThat(onlineClassIds).containsExactlyInAnyOrder(
            springBootClass.getId(),
            springDataJpaClass.getId(),
            javaTestClass.getId(),
            javaCodeManipulationClass.getId()
        );
    }

    @DisplayName("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 5개 빼고 최대 5개 가지만 가져오기")
    @Test
    void getFiveRangeNumber() {
        // given // when
        Stream<Integer> numbers = Stream.iterate(10, i -> i + 1)
            .skip(5)
            .limit(5);

        // then
        assertThat(numbers).containsExactlyInAnyOrder(15, 16, 17, 18, 19);
    }

    @DisplayName("자바 수업 중에서 title에 Test가 들어있는 수업 있는지 확인한다.")
    @Test
    void hasTest() {
        // given
        OnlineClass javaTestClass = new OnlineClass(3, "The Java, Test", true);
        OnlineClass javaCodeManipulationClass = new OnlineClass(4, "The Java, Code manipulation", true);
        OnlineClass java8to11Class = new OnlineClass(8, "The Java, 8 to 11", true);
        List<OnlineClass> javaClasses = Arrays.asList(javaTestClass, javaCodeManipulationClass, java8to11Class);

        // when
        boolean result = javaClasses.stream()
            .anyMatch(oc -> oc.getTitle().contains("Test"));

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("스프링 수업 중에 title에 spring이 들어간 title만 모아서 List로 만들기")
    @Test
    void getClassesWithSpringTitle() {
        // given
        OnlineClass springBootClass = new OnlineClass(1, "spring boot", true);
        OnlineClass springDataJpaClass = new OnlineClass(2, "spring data jpa", true);
        OnlineClass springMvcClass = new OnlineClass(3, "spring mvc", false);
        OnlineClass springCoreClass = new OnlineClass(4, "spring core", false);
        OnlineClass restApiDevelopmentClass = new OnlineClass(5, "rest api development", false);
        List<OnlineClass> springClasses = Arrays.asList(springBootClass, springDataJpaClass, springMvcClass, springCoreClass, restApiDevelopmentClass);

        // when
        List<String> classTitlesWithSpring = springClasses.stream()
            .map(OnlineClass::getTitle)
            .filter(title -> title.contains("spring"))
            .collect(Collectors.toList());

        // then
        assertThat(classTitlesWithSpring)
            .containsExactlyInAnyOrder(
                springBootClass.getTitle(),
                springDataJpaClass.getTitle(),
                springMvcClass.getTitle(),
                springCoreClass.getTitle()
            );
    }
}
