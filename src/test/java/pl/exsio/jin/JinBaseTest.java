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
package pl.exsio.jin;

import com.google.common.base.Optional;
import org.mockito.Matchers;
import pl.exsio.jin.file.loader.TranslationFileLoader;
import pl.exsio.jin.file.locator.TranslationFileLocator;
import pl.exsio.jin.locale.provider.LocaleProvider;
import pl.exsio.jin.locale.provider.factory.LocaleProviderFactory;
import pl.exsio.jin.pluralizator.TranslationPluralizator;
import pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistry;
import pl.exsio.jin.translationprefix.manager.TranslationPrefixManager;
import pl.exsio.jin.translator.TranslatorImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author exsio
 */
public abstract class JinBaseTest {

    protected static final String TEST_TRANSLATION_PATH = "/jin/test-translations.yml";

    protected static final String TEST_TRANSLATION_PATH_STUB_EXISTING = "stub_existing";

    protected static final String TEST_TRANSLATION_PATH_STUB_NOT_EXISTING = "stub_not_existing";

    protected static final String TEST_PLURALIZED_MSG = "pluralized msg";

    protected static final Map TEST_TRANSLATION_MAP = new HashMap() {
        {
            put("test.string1.trans", "String 1 translation");
            put("test.string2.trans", "String 2 translation");
            put("test.pluralizator.pl.apple", "%count% jabłko|%count% jabłka|%count% jabłek");
            put("test.pluralizator.en.apple", "%count% apple|%count% apples");
        }
    };

    protected static String getTmpTranslationFilePath() {
        return System.getProperty("java.io.tmpdir") + File.separator + "tmp_trans.yml";
    }

    protected TranslatorImpl createTranslator() throws IOException {
        TranslatorImpl instance = new TranslatorImpl(getLoader(), getLocaleProviderFactory());
        instance.setPluralizators(getPluralizatorRegistry());
        instance.setPrefixManager(createPrefixManager());
        return instance;
    }

    protected TranslationPrefixManager createPrefixManager() {
        TranslationPrefixManager prefixMgr = mock(TranslationPrefixManager.class);
        when(prefixMgr.getPrefixForTranslatedClass()).thenReturn("");
        return prefixMgr;
    }

    protected TranslationPluralizatorRegistry getPluralizatorRegistry() {
        TranslationPluralizatorRegistry reg = mock(TranslationPluralizatorRegistry.class);
        TranslationPluralizator pluralizator = this.getPluralizator();
        when(reg.getPluralizator(getLanguage())).thenReturn(Optional.of(pluralizator));
        return reg;
    }

    protected TranslationPluralizator getPluralizator() {
        TranslationPluralizator pluralizator = mock(TranslationPluralizator.class);
        when(pluralizator.pluralize(Matchers.anyString(), Matchers.anyInt())).thenReturn(TEST_PLURALIZED_MSG);
        return pluralizator;
    }

    protected TranslationFileLoader getLoader() throws IOException {

        TranslationFileLoader loader = mock(TranslationFileLoader.class);
        when(loader.loadFile(TEST_TRANSLATION_PATH_STUB_EXISTING)).thenReturn(TEST_TRANSLATION_MAP);
        when(loader.loadFile(TEST_TRANSLATION_PATH_STUB_NOT_EXISTING)).thenThrow(new IOException());
        return loader;
    }

    protected TranslationFileLocator getLocator() throws IOException {
        TranslationFileLocator locator = mock(TranslationFileLocator.class);
        InputStream is = getClass().getResourceAsStream(TEST_TRANSLATION_PATH);
        when(locator.locateFile(TEST_TRANSLATION_PATH)).thenReturn(is);
        return locator;

    }

    protected String getLanguage() {
        return this.getLocaleProviderFactory().getLocaleProvider().getLocale().getLanguage();
    }

    protected LocaleProviderFactory getLocaleProviderFactory() {

        LocaleProvider provider = getLocaleProvider();

        LocaleProviderFactory providerProvider = mock(LocaleProviderFactory.class);
        when(providerProvider.getLocaleProvider()).thenReturn(provider);

        return providerProvider;
    }

    protected LocaleProvider getLocaleProvider() {
        LocaleProvider provider = mock(LocaleProvider.class);
        when(provider.getLocale()).thenReturn(Locale.US);
        return provider;
    }

}
