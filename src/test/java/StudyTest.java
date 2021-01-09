import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    void studyClassEqualityTest() throws IOException {
        HashMap<Study, String> hashMap = new HashMap<>();
        hashMap.put(new Study(1, 1), "test1");

        String result = hashMap.get(new Study(1, 1));
        assertEquals("test1", result);

        result = hashMap.get(new Study(1, 2));
        assertEquals(null, result);

    }

    @Test
    void studyRepositoryTest() {

    }

}