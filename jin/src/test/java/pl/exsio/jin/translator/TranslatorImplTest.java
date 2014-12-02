package pl.exsio.jin.translator;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;
import pl.exsio.jin.JinBaseTest;
import pl.exsio.jin.ex.TranslationInitializationException;

/**
 *
 * @author exsio
 */
public class TranslatorImplTest extends JinBaseTest {

    /**
     * Test of init method, of class TranslatorImpl.
     */
    @Test
    public void testInit() throws IOException {
        try {
            TranslatorImpl instance = this.createTranslator();
            instance.registerTranslationFile(this.getLanguage(), TEST_TRANSLATION_PATH_STUB_EXISTING);
            instance.init();
        } catch (TranslationInitializationException ex) {
            fail("This shouldn't throw exception");
        }
        try {
            TranslatorImpl instance = this.createTranslator();
            instance.registerTranslationFile(this.getLanguage(), TEST_TRANSLATION_PATH_STUB_NOT_EXISTING);
            instance.init();
            fail("This should throw exception");
        } catch (TranslationInitializationException ex) {

        }
    }

    /**
     * Test of isInitialized method, of class TranslatorImpl.
     */
    @Test
    public void testIsInitialized() throws TranslationInitializationException, IOException {
        TranslatorImpl instance = this.createTranslator();
        assertFalse(instance.isInitialized());
        instance.init();
        assertTrue(instance.isInitialized());
    }

    /**
     * Test of registerTranslationFile method, of class TranslatorImpl.
     */
    @Test
    public void testRegisterTranslationFile() throws TranslationInitializationException, IOException {
        TranslatorImpl instance = this.createTranslator();
        boolean result = instance.registerTranslationFile(this.getLanguage(), TEST_TRANSLATION_PATH_STUB_EXISTING);
        assertTrue(result);
        instance.init();
        result = instance.registerTranslationFile(this.getLanguage(), TEST_TRANSLATION_PATH_STUB_EXISTING);
        assertFalse(result);
    }

    /**
     * Test of translate method, of class TranslatorImpl.
     */
    @Test
    public void testTranslate() throws IOException, TranslationInitializationException {
        System.out.println("translate");
        String subject = "test.string1.trans";
        TranslatorImpl instance = this.createTranslator();
        instance.registerTranslationFile(this.getLanguage(), TEST_TRANSLATION_PATH_STUB_EXISTING);
        instance.init();
        String expResult = "String 1 translation";
        String result = instance.translate(subject);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of pluralize method, of class TranslatorImpl.
     */
    @Test
    public void testPluralize() throws IOException, TranslationInitializationException {
        
        TranslatorImpl instance = this.createTranslator();
        instance.registerTranslationFile(this.getLanguage(), TEST_TRANSLATION_PATH_STUB_EXISTING);
        instance.init();
        assertEquals(instance.pluralize("", 0), TEST_PLURALIZED_MSG);

    }

}
