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

import pl.exsio.jin.translator.Translator;

/**
 *
 * @author exsio
 */
public class TranslationContext {

    private static Translator translator;

    public static void setTranslator(Translator translator) {
        TranslationContext.translator = translator;
    }

    public static String t(String subject) {
        if (translator != null) {
            return translator.translate(subject);
        } else {
            return subject;
        }

    }

    public static String[] t(String[] subjects) {
        if (translator != null) {
            String[] translated = new String[subjects.length];
            for (int i = 0; i < subjects.length; i++) {
                translated[i] = translator.translate(subjects[i]);
            }
            return translated;
        } else {
            return subjects;
        }
    }

    public static String pluralize(String options, int count) {
        if (translator != null) {
            return translator.pluralize(options, count);
        } else {
            return options;
        }
    }

}
