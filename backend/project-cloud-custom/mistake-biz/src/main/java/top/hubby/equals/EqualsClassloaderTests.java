package top.hubby.equals;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author asd <br>
 * @create 2021-10-26 5:34 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class EqualsClassloaderTests {
    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader =
                new ClassLoader() {
                    @Override
                    public Class<?> loadClass(String name) throws ClassNotFoundException {
                        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                        InputStream inputStream = getClass().getResourceAsStream(fileName);
                        if (inputStream == null) {
                            return super.loadClass(name);
                        }
                        try {
                            byte[] b = new byte[inputStream.available()];
                            inputStream.read(b);
                            return defineClass(name, b, 0, b.length);
                        } catch (IOException e) {
                            throw new ClassNotFoundException();
                        }
                    }
                };

        Object point1 = classLoader.loadClass(Point.class.getName()).newInstance();
        Point point2 = new Point();
        Object point3 =
                ClassLoader.getSystemClassLoader().loadClass(Point.class.getName()).newInstance();

        System.out.println(point1 instanceof Point); // false
        System.out.println(point1.getClass() == Point.class); // false
        System.out.println(point1.equals(point2)); // false

        System.out.println(point3 instanceof Point); // true
        System.out.println(point3.getClass() == Point.class); // true
        System.out.println(point3.equals(point2)); // true
    }
}
