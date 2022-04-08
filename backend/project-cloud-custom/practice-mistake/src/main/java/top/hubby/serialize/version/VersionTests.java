package top.hubby.serialize.version;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-10-29 1:30 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class VersionTests {
    private String file;

    @Before
    public void init() throws IOException {
        File f = new File("user.obj");
        if (!f.exists()) {
            f.createNewFile();
        }
        file = f.getAbsolutePath();
    }

    @Test
    public void write() throws IOException {
        User userSource = new User();
        userSource.setName("zhuye");
        writeObject(userSource);
        log.info("{}", userSource);
    }

    @Test
    public void read() throws IOException, ClassNotFoundException {
        User user = readObject();
        log.info("{}", user);
    }

    private void writeObject(User user) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(user);
        }
    }

    private User readObject() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (User) ois.readObject();
        }
    }
}
