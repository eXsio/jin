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
        assertTrue(result.size() == 7);
        assertEquals(result.get("test.string1.trans"), "String 1 translation");
        assertEquals(result.get("test.string2.trans"), "String 2 translation");
        assertEquals(result.get("test.compound.str2"), "Compound String 2 translation");
        assertEquals(result.get("test.compound2.str1"), "Compound2 String 1 translation");
        assertEquals(result.get("test.compound3.deeper.str1"), "Compound 3 Deeper String 1 translation");
        assertEquals(result.get("test.mixed.compound.str1"), "Mixed Compound String 1 translation");

    }

}
