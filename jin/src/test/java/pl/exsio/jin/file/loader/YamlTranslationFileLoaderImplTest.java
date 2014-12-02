package pl.exsio.jin.file.loader;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.exsio.jin.JinBaseTest;

/**
 *
 * @author exsio
 */
public class YamlTranslationFileLoaderImplTest extends JinBaseTest {

    /**
     * Test of loadFile method, of class
     * ApplicationContextYamlTranslationFileLoaderImpl.
     */
    @Test
    public void testLoadFile() throws Exception {
        YamlTranslationFileLoaderImpl instance = new YamlTranslationFileLoaderImpl();
        instance.setLocator(this.getLocator());
        Map<String, String> result = instance.loadFile(TEST_TRANSLATION_PATH);
        assertTrue(result.size() == 5);
        assertEquals(result.get("test.string1.trans"), "String 1 translation");
        assertEquals(result.get("test.string2.trans"), "String 2 translation");
        assertEquals(result.get("test.compound.str2"), "Compound String 2 translation");
        assertEquals(result.get("test.compound2.str1"), "Compound2 String 1 translation");
        assertEquals(result.get("test.compound3.deeper.str1"), "Compound 3 Deeper String 1 translation");

    }

}
