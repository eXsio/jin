/* 
 * The MIT License
 *
 * Copyright 2015 exsio.
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
package pl.exsio.jin.translationprefix.retriever;

import java.lang.annotation.Annotation;
import pl.exsio.jin.annotation.TranslationPrefix;

/**
 *
 * @author exsio
 */
public class TranslationPrefixRetriever {

    private final static String EMPTY_PREFIX = "";

    private final static String TRANSLATION_STRING_CONNECTOR = ".";

    public static String getTranslationPrefix(Class translatedClass) {
        Annotation annotation = getAnnotationForClass(translatedClass);
        String prefix;
        if (annotation != null) {
            TranslationPrefix prefixObj = (TranslationPrefix) annotation;
            prefix = getPrefixString(prefixObj);
        } else {
            prefix = EMPTY_PREFIX;
        }
        return prefix;
    }

    private static String getPrefixString(TranslationPrefix prefixObj) {
        return new StringBuilder(prefixObj.value()).append(TRANSLATION_STRING_CONNECTOR).toString();
    }

    private static Annotation getAnnotationForClass(Class translatedClass) {
        do {
            Annotation annotation = translatedClass.getAnnotation(TranslationPrefix.class);
            if (annotation != null) {
                return annotation;
            } else {
                translatedClass = translatedClass.getEnclosingClass();
            }
        } while (translatedClass != null);

        return null;
    }
}
