package top.hubby.principles.lsp;

import org.junit.Test;
import top.hubby.principles.lsp.before.Rectangle;
import top.hubby.principles.lsp.before.Square;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
public class RectangleTests {

    @Test
    public void testRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setLength(20);
        rectangle.setWidth(10);
        rectangle.resize(rectangle);
        rectangle.printLengthAndWidth(rectangle);

        Rectangle rectangle1 = new Square();
        rectangle1.setLength(10);
        rectangle1.resize(rectangle1);
        rectangle1.printLengthAndWidth(rectangle1);
    }
}
