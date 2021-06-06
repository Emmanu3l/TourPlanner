package businesslayer;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationManagerTest {

    @Test
    void getConfigProperty() throws FileNotFoundException {
        assertNotNull(ConfigurationManager.GetConfigProperty("PostgresSqlConnectionString"));
        assertNotNull(ConfigurationManager.GetConfigProperty("DatabaseUser"));
        assertNotNull(ConfigurationManager.GetConfigProperty("DatabasePassword"));
        assertNotNull(ConfigurationManager.GetConfigProperty("MapQuestAPIKey"));
        assertNotNull(ConfigurationManager.GetConfigProperty("ImagePath"));
        assertNotNull(ConfigurationManager.GetConfigProperty("FilePath"));


        assertNull(ConfigurationManager.GetConfigProperty("ThisConfigPropertyDoesNotExist"));
    }
}