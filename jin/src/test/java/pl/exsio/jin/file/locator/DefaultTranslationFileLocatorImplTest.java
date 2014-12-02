package pl.exsio.jin.file.locator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.exsio.jin.JinBaseTest;

/**
 *
 * @author exsio
 */
public class DefaultTranslationFileLocatorImplTest extends JinBaseTest {

    public DefaultTranslationFileLocatorImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        InputStream is = DefaultTranslationFileLocatorImplTest.class.getResourceAsStream(TEST_TRANSLATION_PATH);
        File targetFile = new File(getTmpTranslationFilePath());
        FileUtils.copyInputStreamToFile(is, targetFile);
    }

    @AfterClass
    public static void tearDownClass() {
        File targetFile = new File(getTmpTranslationFilePath());
        targetFile.delete();
    }

    /**
     * Test of locateFile method, of class DefaultTranslationFileLocatorImpl.
     */
    @Test
    public void testLocateFile() throws Exception {

        try {
            DefaultTranslationFileLocatorImpl instance = new DefaultTranslationFileLocatorImpl();
            InputStream result = instance.locateFile(getTmpTranslationFilePath());
            assertNotNull(result);
        } catch (IOException ex) {
            fail("Unable to load file: " + ex.getMessage());
        }

    }

}
