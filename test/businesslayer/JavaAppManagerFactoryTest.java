package businesslayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaAppManagerFactoryTest {

    @Test
    void getManager() {
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        assertNotNull(manager);
    }
}