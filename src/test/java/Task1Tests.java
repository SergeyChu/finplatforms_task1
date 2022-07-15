import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.schu.test.Task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


class Task1Tests {

    private final Path currentPath = Paths.get("src").resolve("test")
            .resolve("resources").resolve("task1").toAbsolutePath();

    @Test
    void NoPathSpecifiedTest() {

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                Task1::main);
        String exMessage = exception.getMessage();

        Assertions.assertTrue(exMessage.contains(Task1.HELP_MESSAGE));
    }

    @Test
    void NotExistingPathSpecifiedTest() {

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> Task1.main("not exist"));
        String exMessage = exception.getMessage();

        Assertions.assertTrue(exMessage.contains(Task1.INCORRECT_PATH_MESSAGE));
    }

    @Test
    void HappyPathTest() throws IOException {

        Task1.main(currentPath.toAbsolutePath().toString());
        List<String> result = new ArrayList<>();
        Files.lines(currentPath.resolve(Task1.OUTPUT_FILE_NAME)).forEachOrdered(result::add);
        Assertions.assertEquals(15, result.size());
        Assertions.assertEquals("afilecontent1", result.get(0));
        Assertions.assertEquals("afilecontent2", result.get(1));
        Assertions.assertEquals("afilecontent3", result.get(2));
        Assertions.assertEquals("afile1content1", result.get(3));
        Assertions.assertEquals("afile1content2", result.get(4));
        Assertions.assertEquals("afile1content3", result.get(5));
        Assertions.assertEquals("bfilecontent1", result.get(6));
        Assertions.assertEquals("bfilecontent2", result.get(7));
        Assertions.assertEquals("bfilecontent3", result.get(8));
        Assertions.assertEquals("cfilecontent1", result.get(9));
        Assertions.assertEquals("cfilecontent2", result.get(10));
        Assertions.assertEquals("dfilecontent1", result.get(11));
        Assertions.assertEquals("dfilecontent2", result.get(12));
        Assertions.assertEquals("dfilecontent3", result.get(13));
        Assertions.assertEquals("efilecontent1", result.get(14));
    }
    
}
