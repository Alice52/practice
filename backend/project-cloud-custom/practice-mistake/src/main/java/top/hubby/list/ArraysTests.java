package top.hubby.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author asd <br>
 * @create 2021-10-27 5:17 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class ArraysTests {

    @Test
    public void wrong1() {
        int[] arr = {1, 2, 3};
        List<int[]> list = Arrays.asList(arr);
        log.info("list:{} size:{} class:{}", list, list.size(), list.get(0).getClass());
    }

    @Test
    public void right1() {
        int[] arr1 = {1, 2, 3};
        List<Integer> list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        log.info("list:{} size:{} class:{}", list1, list1.size(), list1.get(0).getClass());

        Integer[] arr2 = {1, 2, 3};
        List<Integer> list2 = Arrays.asList(arr2);
        log.info("list:{} size:{} class:{}", list2, list2.size(), list2.get(0).getClass());
    }

    @Test
    public void wrong2() {
        String[] arr = {"1", "2", "3"};
        List<String> list = Arrays.asList(arr);
        arr[1] = "4";
        try {
            list.add("5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("arr:{} list:{}", Arrays.toString(arr), list);
    }

    @Test
    public void right2() {
        String[] arr = {"1", "2", "3"};
        List<String> list = new ArrayList(Arrays.asList(arr));
        arr[1] = "4";
        try {
            list.add("5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("arr:{} list:{}", Arrays.toString(arr), list);
    }
}
