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
