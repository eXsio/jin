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
public class PolishTranslationPluralizatorImplTest extends JinBaseTest {

    @Test
    public void testPluralize3Options() {
        String options = "%count% jabłko|%count% jabłka|%count% jabłek";
        PolishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 jabłko");
        assertEquals(instance.pluralize(options, -1), "-1 jabłko");
        assertEquals(instance.pluralize(options, 2), "2 jabłka");
        assertEquals(instance.pluralize(options, -3), "-3 jabłka");
        assertEquals(instance.pluralize(options, 5), "5 jabłek");
        assertEquals(instance.pluralize(options, -10), "-10 jabłek");
        assertEquals(instance.pluralize(options, 0), "0 jabłek");
        assertEquals(instance.pluralize(options, 22), "22 jabłka");
        assertEquals(instance.pluralize(options, 54), "54 jabłka");
        assertEquals(instance.pluralize(options, -156), "-156 jabłek");
    }

    private PolishTranslationPluralizatorImpl createPluralizator() {
        PolishTranslationPluralizatorImpl instance = new PolishTranslationPluralizatorImpl();
        return instance;
    }

    @Test
    public void testPluralize2Options() {
        String options = "%count% jabłko|%count% jabłka";
        PolishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 jabłko");
        assertEquals(instance.pluralize(options, -1), "-1 jabłko");
        assertEquals(instance.pluralize(options, 2), "2 jabłka");
        assertEquals(instance.pluralize(options, -3), "-3 jabłka");
        assertEquals(instance.pluralize(options, 5), "5 jabłka");
        assertEquals(instance.pluralize(options, -10), "-10 jabłka");
        assertEquals(instance.pluralize(options, 0), "0 jabłka");
    }

    @Test
    public void testPluralize1Option() {
        String options = "%count% jabłko";
        PolishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 jabłko");
        assertEquals(instance.pluralize(options, -1), "-1 jabłko");
        assertEquals(instance.pluralize(options, 2), "2 jabłko");
        assertEquals(instance.pluralize(options, -3), "-3 jabłko");
        assertEquals(instance.pluralize(options, 5), "5 jabłko");
        assertEquals(instance.pluralize(options, -10), "-10 jabłko");
        assertEquals(instance.pluralize(options, 0), "0 jabłko");
    }

}
