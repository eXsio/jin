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
