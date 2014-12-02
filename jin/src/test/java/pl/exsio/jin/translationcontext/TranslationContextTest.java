package pl.exsio.jin.translationcontext;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.exsio.jin.JinBaseTest;
import pl.exsio.jin.ex.TranslationInitializationException;
import pl.exsio.jin.translator.Translator;

/**
 *
 * @author exsio
 */
public class TranslationContextTest extends JinBaseTest {

    @Before
    public void setUp() throws IOException, TranslationInitializationException {
        Translator t = this.createTranslator();
        t.registerTranslationFile(this.getLanguage(), TEST_TRANSLATION_PATH_STUB_EXISTING);
        t.init();
        TranslationContext.setTranslator(t);
    }

    /**
     * Test of t method, of class TranslationContext.
     */
    @Test
    public void testT_String() {

        String subject = "test.string1.trans";
        String expResult = "String 1 translation";
        String result = TranslationContext.t(subject);
        assertEquals(expResult, result);

    }

    /**
     * Test of t method, of class TranslationContext.
     */
    @Test
    public void testT_StringArr() {

        String[] subjects = new String[]{"test.string1.trans", "test.string2.trans"};
        String[] expResult = new String[]{"String 1 translation", "String 2 translation"};
        String[] result = TranslationContext.t(subjects);
        assertArrayEquals(expResult, result);
    }

}
