//package io.github.alice52.test.junit;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Before;
//import org.junit.*;
//import org.junit.jupiter.api.Test;
//import org.junit.runners.MethodSorters;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * @author T04856 <br>
// * @create 2023-03-27 2:24 PM <br>
// * @project project-cloud-custom <br>
// */
//@Slf4j
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class OrderAnnotationUnit4Test {
//    private static StringBuilder output = new StringBuilder("");
//
//    @BeforeClass
//    public static void beforeClass() {
//        log.info("first: beforeClass");
//    }
//
//    @Before
//    public void before() {
//        log.info("before");
//    }
//
//
//    @Test
//    public void test_1_app_b() {
//        output.append("b");
//        log.info("b");
//    }
//
//    @Test
//    public void test_2_app_a() {
//        output.append("a");
//        log.info("a");
//    }
//
//    @After
//    public void after() {
//        log.info("after");
//    }
//
//    @AfterClass
//    public static void afterClass() {
//        log.info("first: afterClass");
//        assertEquals(output.toString(), "ba");
//    }
//}
