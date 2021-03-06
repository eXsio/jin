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
package pl.exsio.jin.integration;

import org.junit.Before;
import org.junit.Test;
import pl.exsio.jin.ex.TranslationInitializationException;
import pl.exsio.jin.file.loader.TranslationFileLoader;
import pl.exsio.jin.file.loader.YamlTranslationFileLoaderImpl;
import pl.exsio.jin.file.locator.DefaultTranslationFileLocatorImpl;
import pl.exsio.jin.locale.provider.DefaultLocaleProviderImpl;
import pl.exsio.jin.locale.provider.factory.DefaultLocaleProviderFactoryImpl;
import pl.exsio.jin.pluralizator.EnglishTranslationPluralizatorImpl;
import pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistry;
import pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistryImpl;
import pl.exsio.jin.translationprefix.manager.TranslationPrefixManagerImpl;
import pl.exsio.jin.translator.Translator;
import pl.exsio.jin.translator.TranslatorImpl;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static pl.exsio.jin.translationcontext.TranslationContext.t;

/**
 * @author exsio
 */
public class JinIntegrationTest {

    private Translator t;

    private TranslatedClass subject;

    @Before
    public void beforeTest() throws TranslationInitializationException, URISyntaxException {
        this.t = this.getTranslator();
        this.subject = new TranslatedClass(t);
    }

    @Test
    public void testTranslatorResult() {
        assertEquals(subject.getTranslatorResult(), "Translator usage");
    }

    @Test
    public void testTranslationContextResult() {
        assertEquals(subject.getTranslationContextResult(), "Translation context usage");
    }

    @Test
    public void testPluralizeTranslatorResult() {
        assertEquals(subject.getPluralizeTranslatorResult(1), "1 apple");
        assertEquals(subject.getPluralizeTranslatorResult(3), "3 apples");
    }

    @Test
    public void testPluralizeTranslationContextResult() {
        assertEquals(subject.getPluralizeTranslationContextResult(1), "1 pear");
        assertEquals(subject.getPluralizeTranslationContextResult(3), "3 pears");
    }

    @Test
    public void testRawTextResult() {
        assertEquals(t("raw text for translation"), "translated raw text");
    }

    private Translator getTranslator() throws TranslationInitializationException, URISyntaxException {

        TranslationFileLoader loader = new YamlTranslationFileLoaderImpl(new DefaultTranslationFileLocatorImpl());
        TranslatorImpl translator = new TranslatorImpl(loader, new DefaultLocaleProviderFactoryImpl(
                new DefaultLocaleProviderImpl("en")
        ));
        TranslationPluralizatorRegistry pluralizators = new TranslationPluralizatorRegistryImpl();
        pluralizators.setPluralizators(new HashMap() {
            {
                put("en", new EnglishTranslationPluralizatorImpl());
            }
        });
        translator.setPluralizators(pluralizators);
        translator.setPrefixManager(new TranslationPrefixManagerImpl());
        URL url = this.getClass().getResource("/jin/integration/integration-test-translations.yml");
        translator.registerTranslationFile("en", new File(url.toURI()).getAbsolutePath());

        translator.init();
        return translator;
    }
}
