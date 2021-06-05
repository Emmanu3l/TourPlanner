package businesslayer;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationManagerTest {

    @Test
    void getConfigProperty() throws FileNotFoundException {
        assertNotNull(ConfigurationManager.GetConfigProperty("PostgresSqlConnectionString"));
        assertNull(ConfigurationManager.GetConfigProperty("ThisConfigPropertyDoesNotExist"));
    }
}