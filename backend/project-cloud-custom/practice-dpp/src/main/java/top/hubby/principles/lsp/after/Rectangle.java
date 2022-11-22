package top.hubby.principles.lsp.after;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
@Slf4j
@Data
public class Rectangle implements Quadrilateral {

    private double length;
    private double width;

    public static void resize(top.hubby.principles.lsp.before.Rectangle rectangle) {
        while (rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public double getWidth() {
        return width;
    }
}
