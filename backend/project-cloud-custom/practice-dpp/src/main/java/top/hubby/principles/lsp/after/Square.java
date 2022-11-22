package top.hubby.principles.lsp.after;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
public class Square implements Quadrilateral {

    private double side;

    @Override
    public double getLength() {
        return side;
    }

    @Override
    public double getWidth() {
        return side;
    }
}
