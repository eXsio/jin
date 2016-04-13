/* 
 * The MIT License
 *
 * Copyright 2014 exsio.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
