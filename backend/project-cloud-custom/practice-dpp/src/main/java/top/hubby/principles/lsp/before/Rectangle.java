package top.hubby.principles.lsp.before;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
@Slf4j
@Data
public class Rectangle {
    private double length;
    private double width;

    public static void resize(Rectangle rectangle) {
        while (rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }
    }
    // 打印长方形的长和宽
    public static void printLengthAndWidth(Rectangle rectangle) {
        log.info("{}", rectangle.getLength());
        log.info("{}", rectangle.getWidth());
    }
}
