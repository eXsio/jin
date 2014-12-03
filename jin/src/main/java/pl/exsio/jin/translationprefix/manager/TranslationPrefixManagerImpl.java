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
package pl.exsio.jin.translationprefix.manager;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import pl.exsio.jin.annotation.TranslationPrefix;
import pl.exsio.jin.file.loader.TranslationFileLoader;
import pl.exsio.jin.file.locator.TranslationFileLocator;
import pl.exsio.jin.locale.provider.LocaleProvider;
import pl.exsio.jin.locale.provider.provider.LocaleProviderProvider;
import pl.exsio.jin.pluralizator.TranslationPluralizator;
import pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistry;
import pl.exsio.jin.translationcontext.TranslationContext;
import pl.exsio.jin.translator.Translator;

public class TranslationPrefixManagerImpl implements TranslationPrefixManager {

    protected final Map<Class, String> prefixMap;

    protected final List<Class> internalClasses;

    public TranslationPrefixManagerImpl() {
        this.prefixMap = new HashMap();
        this.internalClasses = new LinkedList() {
            {
                add(TranslationPrefixManager.class);
                add(Translator.class);
                add(TranslationContext.class);
                add(TranslationPluralizator.class);
                add(TranslationPluralizatorRegistry.class);
                add(TranslationFileLoader.class);
                add(TranslationFileLocator.class);
                add(LocaleProvider.class);
                add(LocaleProviderProvider.class);
            }
        };
    }

    @Override
    public String getPrefixForTranslatedClass() {
        Class translatedClass = this.getCallingClass();
        if (translatedClass != null) {
            if (!this.prefixMap.containsKey(translatedClass)) {
                this.updateMap(translatedClass);
            }
            return this.prefixMap.get(translatedClass);
        } else {
            return "";
        }
    }

    protected void updateMap(Class translatedClass) {
        Annotation annotation = this.getAnnotationForClass(translatedClass);
        String prefix;
        if (annotation != null) {
            TranslationPrefix prefixObj = (TranslationPrefix) annotation;
            prefix = prefixObj.value() + ".";
        } else {
            prefix = "";
        }
        this.prefixMap.put(translatedClass, prefix);
    }

    protected Annotation getAnnotationForClass(Class translatedClass) {
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

    protected Class getCallingClass() {
        StackTraceElement[] stack = new Exception().getStackTrace();
        for (int i = 0; i < stack.length; i++) {
            try {
                String className = stack[i].getClassName();
                Class classObj = Class.forName(className);
                if (!this.isInternalClass(classObj)) {
                    return classObj;
                }
            } catch (ClassNotFoundException ex) {
                continue;
            }
        }

        return null;
    }

    protected boolean isInternalClass(Class classObj) {
        for (Class internalClass : this.internalClasses) {
            if (internalClass.isAssignableFrom(classObj) || internalClass.equals(classObj)) {
                return true;
            }
        }

        return false;
    }

}
