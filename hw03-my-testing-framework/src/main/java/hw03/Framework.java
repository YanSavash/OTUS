package hw03;

import myAnnotations.*;

public class Framework {

    public Framework() {
        System.out.println("Call of the constructor");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    static void beforeEach3() {
        System.out.println("BeforeEach3");
    }

    @BeforeEach
    static void beforeEach() {
        System.out.println("BeforeEach");
    }

    @BeforeEach
    static void beforeEach2() {
        System.out.println("BeforeEach2");
    }

    @Test
    static void testOne() {
        System.out.println("testOne");
    }

    @Test
    static void testTwo() {
        System.out.println("testTwo");
    }

    @AfterEach
    static void afterEach() {
        System.out.println("AfterEach");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll");
    }
}
