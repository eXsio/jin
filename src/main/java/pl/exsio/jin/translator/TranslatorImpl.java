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
package pl.exsio.jin.translator;

import com.google.common.base.Optional;
import pl.exsio.jin.ex.TranslationInitializationException;
import pl.exsio.jin.file.loader.TranslationFileLoader;
import pl.exsio.jin.locale.provider.factory.LocaleProviderFactory;
import pl.exsio.jin.pluralizator.TranslationPluralizator;
import pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistry;
import pl.exsio.jin.translationcontext.TranslationContext;
import pl.exsio.jin.translationprefix.manager.TranslationPrefixManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author exsio
 */
public class TranslatorImpl implements Translator {

    private final TranslationFileLoader loader;

    private final LocaleProviderFactory localeProviderFactory;

    private Map<String, String> registeredFiles;

    private final Map<String, Map<String, String>> translations;

    private boolean initialized = false;

    private Optional<TranslationPluralizatorRegistry> pluralizators;

    private Optional<TranslationPrefixManager> prefixManager;

    public TranslatorImpl(TranslationFileLoader loader, LocaleProviderFactory localeProviderFactory) {
        this.loader = loader;
        this.localeProviderFactory = localeProviderFactory;
        this.registeredFiles = new LinkedHashMap<>();
        this.translations = new HashMap();
    }

    @Override
    public void init() throws TranslationInitializationException {
        if (!initialized) {
            for (String filePath : registeredFiles.keySet()) {
                String lang = registeredFiles.get(filePath);
                this.loadFile(lang, filePath);
            }
            this.initialized = true;
            TranslationContext.setTranslator(this);
        }
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    private void loadFile(String lang, String filePath) throws TranslationInitializationException {
        Map<String, String> translationMap = getTranslations(lang);
        try {
            translationMap.putAll(loader.loadFile(filePath));
        } catch (IOException ex) {
            throw new TranslationInitializationException("An error occured during initialization of translations", ex);
        }
    }

    public void setRegisterdFiles(Map<String, String> registeredFiles) {
        this.registeredFiles = registeredFiles;
    }

    @Override
    public Map<String, String> getTranslations(String lang) {
        if (!translations.containsKey(lang)) {
            translations.put(lang, new LinkedHashMap<String, String>());
        }
        return translations.get(lang);
    }

    @Override
    public boolean registerTranslationFile(String lang, String filePath) {
        if (!initialized) {
            registeredFiles.put(filePath, lang);
            return true;
        }
        return false;
    }

    @Override
    public String translate(String subject) {
        subject = formatSubject(subject);
        Map<String, String> translationsMap = getTranslations(getCurrentLocale().getLanguage());
        if (translationsMap instanceof Map && translationsMap.containsKey(subject)) {
            Object translation = translationsMap.get(subject);
            return translation instanceof String ? ((String) translation) : subject;
        } else {
            return subject;
        }
    }

    @Override
    public String pluralize(String options, int count) {
        if (pluralizators.isPresent()) {
            Optional<TranslationPluralizator> pluralizator = pluralizators.get().getPluralizator(getCurrentLocale().getLanguage());
            if (pluralizator.isPresent()) {
                return pluralizator.get().pluralize(translate(options), count);
            }
        }
        return options;
    }

    private String formatSubject(String subject) {
        if (prefixManager.isPresent()) {
            return prefixManager.get().getPrefixForTranslatedClass() + subject;
        } else {
            return subject;
        }
    }

    private Locale getCurrentLocale() {
        return localeProviderFactory.getLocaleProvider().getLocale();
    }

    public void setPluralizators(TranslationPluralizatorRegistry pluralizators) {
        this.pluralizators = Optional.of(pluralizators);
    }

    public void setPrefixManager(TranslationPrefixManager prefixManager) {
        this.prefixManager = Optional.of(prefixManager);
    }

}
