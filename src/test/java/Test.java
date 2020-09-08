import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.GenericContainer;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Test {
    @org.junit.Test
    public void mountTest() throws IOException, InterruptedException {
        GenericContainer c = new GenericContainer("alpine")
            .withClasspathResourceMapping("/foo.txt", "/foo.txt", BindMode.READ_WRITE)
            .withCommand("sleep", "10");

        c.start();
        Container.ExecResult cat = c.execInContainer("cat", "/foo.txt");
        String stdout = cat.getStdout();

        assertEquals("works", stdout);
    }
}
