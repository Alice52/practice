package top.hubby.principles.lsp.before;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
public class Square extends Rectangle {
    /**
     * 会引起 resize 方法的报错: 因为重写了父类的方法, 违反了里氏替换原则
     *
     * @param width
     */
    @Override
    public void setWidth(double width) {
        super.setLength(width);
        super.setWidth(width);
    }

    @Override
    public void setLength(double length) {
        super.setLength(length);
        super.setWidth(length);
    }
}
