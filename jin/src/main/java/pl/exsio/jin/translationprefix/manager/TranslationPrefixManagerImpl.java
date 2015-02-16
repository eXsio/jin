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
import pl.exsio.jin.locale.provider.provider.LocaleProviderProvider;
import pl.exsio.jin.pluralizator.TranslationPluralizator;
import pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistry;
import pl.exsio.jin.translationcontext.TranslationContext;
import pl.exsio.jin.translationprefix.retriever.TranslationPrefixRetriever;
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
                add(TranslationPrefixRetriever.class);
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
        this.prefixMap.put(translatedClass, TranslationPrefixRetriever.getTranslationPrefix(translatedClass));
    }

    protected Class getCallingClass() {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            try {
                Class candidateClass = Class.forName(element.getClassName());
                if (!this.isInternalClass(candidateClass)) {
                    return candidateClass;
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace(System.err);
            }
        }

        return null;
    }

    protected boolean isInternalClass(Class candidateClass) {
        for (Class internalClass : this.internalClasses) {
            if (internalClass.isAssignableFrom(candidateClass) || internalClass.equals(candidateClass)) {
                return true;
            }
        }

        return false;
    }

}
