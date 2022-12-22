package top.hubby.dp.adapter.practice;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author zack <br>
 * @create 2022-12-22 23:31 <br>
 * @project practice-optimize <br>
 */
public class Version {

    // JDK1.0 中包含一个遍历集合容器的类 Enumeration 升级为  Iterator 的兼容
    public static Enumeration emumeration(final Collection c) {

        return new Enumeration() {
            Iterator i = c.iterator();

            @Override
            public boolean hasMoreElements() {
                return i.hasNext();
            }

            @Override
            public Object nextElement() {
                return i.next();
            }
        };
    }
}
