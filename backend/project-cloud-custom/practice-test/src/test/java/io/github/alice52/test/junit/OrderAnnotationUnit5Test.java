//package io.github.alice52.test.junit;
//
//import org.junit.jupiter.api.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
///**
// * @author T04856 <br>
// * @create 2023-03-28 4:16 PM <br>
// * @project project-cloud-custom <br>
// */
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class OrderAnnotationUnit5Test {
//    private static StringBuilder output = new StringBuilder("");
//
//    @Test
//    @Order(1)
//    void firstTest() {
//        output.append("a");
//    }
//
//    @Test
//    @Order(2)
//    void secondTest() {
//        output.append("b");
//    }
//
//    @Test
//    @Order(3)
//    void thirdTest() {
//        output.append("c");
//    }
//
//    @AfterAll
//    public static void assertOutput() {
//        assertEquals("abc", output.toString());
//    }
//}
