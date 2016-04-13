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
package pl.exsio.jin.translationprefix.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import pl.exsio.jin.file.loader.TranslationFileLoader;
import pl.exsio.jin.file.locator.TranslationFileLocator;
import pl.exsio.jin.locale.provider.LocaleProvider;
import pl.exsio.jin.locale.provider.factory.LocaleProviderFactory;
import pl.exsio.jin.pluralizator.TranslationPluralizator;
import pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistry;
import pl.exsio.jin.translationcontext.TranslationContext;
import pl.exsio.jin.translationprefix.retriever.TranslationPrefixRetriever;
import pl.exsio.jin.translator.Translator;

public class TranslationPrefixManagerImpl implements TranslationPrefixManager {

    private final Map<Class, String> prefixMap;

    private final List<Class> internalClasses;

    public TranslationPrefixManagerImpl() {
        prefixMap = new HashMap();
        internalClasses = new LinkedList() {
            {
                add(TranslationPrefixManager.class);
                add(Translator.class);
                add(TranslationContext.class);
                add(TranslationPrefixRetriever.class);
                add(TranslationPluralizator.class);
                add(TranslationPluralizatorRegistry.class);
                add(TranslationFileLoader.class);
                add(TranslationFileLocator.class);
                add(LocaleProvider.class);
                add(LocaleProviderFactory.class);
            }
        };
    }

    @Override
    public String getPrefixForTranslatedClass() {
        Class translatedClass = getCallingClass();
        if (translatedClass != null) {
            if (!prefixMap.containsKey(translatedClass)) {
                updateMap(translatedClass);
            }
            return prefixMap.get(translatedClass);
        } else {
            return "";
        }
    }

    private void updateMap(Class translatedClass) {
        prefixMap.put(translatedClass, TranslationPrefixRetriever.getTranslationPrefix(translatedClass));
    }

    private Class getCallingClass() {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            try {
                Class candidateClass = Class.forName(element.getClassName());
                if (!isInternalClass(candidateClass)) {
                    return candidateClass;
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace(System.err);
            }
        }

        return null;
    }

    private boolean isInternalClass(Class candidateClass) {
        for (Class internalClass : internalClasses) {
            if (internalClass.isAssignableFrom(candidateClass) || internalClass.equals(candidateClass)) {
                return true;
            }
        }

        return false;
    }

}
