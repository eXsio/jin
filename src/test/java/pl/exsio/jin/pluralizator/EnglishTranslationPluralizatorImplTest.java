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
package pl.exsio.jin.pluralizator;

import org.junit.Test;
import static org.junit.Assert.*;
import pl.exsio.jin.JinBaseTest;

/**
 *
 * @author exsio
 */
public class EnglishTranslationPluralizatorImplTest extends JinBaseTest {
    
    @Test
    public void testPluralize2Options() {
        String options = "%count% apple|%count% apples";
        EnglishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 apple");
        assertEquals(instance.pluralize(options, -1), "-1 apple");
        assertEquals(instance.pluralize(options, 2), "2 apples");
        assertEquals(instance.pluralize(options, -3), "-3 apples");
        assertEquals(instance.pluralize(options, 5), "5 apples");
        assertEquals(instance.pluralize(options, -10), "-10 apples");
        assertEquals(instance.pluralize(options, 0), "0 apples");
    }
    
    private EnglishTranslationPluralizatorImpl createPluralizator() {
        EnglishTranslationPluralizatorImpl instance = new EnglishTranslationPluralizatorImpl();
        return instance;
    }
    
    @Test
    public void testPluralize1Option() {
        String options = "%count% apple";
        EnglishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 apple");
        assertEquals(instance.pluralize(options, -1), "-1 apple");
        assertEquals(instance.pluralize(options, 2), "2 apple");
        assertEquals(instance.pluralize(options, -3), "-3 apple");
        assertEquals(instance.pluralize(options, 5), "5 apple");
        assertEquals(instance.pluralize(options, -10), "-10 apple");
        assertEquals(instance.pluralize(options, 0), "0 apple");
    }
    
}
