package pl.exsio.jin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.mockito.Matchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import pl.exsio.jin.file.loader.TranslationFileLoader;
import pl.exsio.jin.file.locator.TranslationFileLocator;
import pl.exsio.jin.locale.provider.LocaleProvider;
import pl.exsio.jin.locale.provider.provider.LocaleProviderProvider;
import pl.exsio.jin.pluralizator.TranslationPluralizator;
import pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistry;
import pl.exsio.jin.translator.TranslatorImpl;

/**
 *
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
        TranslatorImpl instance = new TranslatorImpl();
        instance.setLoader(this.getLoader());
        instance.setLocaleProviderProvider(this.getLocaleProviderProvider());
        instance.setPluralizators(this.getPluralizatorRegistry());
        return instance;
    }

    protected TranslationPluralizatorRegistry getPluralizatorRegistry() {
        TranslationPluralizatorRegistry reg = mock(TranslationPluralizatorRegistry.class);
        TranslationPluralizator pluralizator = this.getPluralizator();
        when(reg.getPluralizator(this.getLanguage())).thenReturn(pluralizator);
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
        InputStream is = this.getClass().getResourceAsStream(TEST_TRANSLATION_PATH);
        when(locator.locateFile(TEST_TRANSLATION_PATH)).thenReturn(is);
        return locator;

    }

    protected String getLanguage() {
        return this.getLocaleProviderProvider().getLocaleProvider().getLocale().getLanguage();
    }

    protected LocaleProviderProvider getLocaleProviderProvider() {

        LocaleProvider provider = this.getLocaleProvider();

        LocaleProviderProvider providerProvider = mock(LocaleProviderProvider.class);
        when(providerProvider.getLocaleProvider()).thenReturn(provider);

        return providerProvider;
    }

    protected LocaleProvider getLocaleProvider() {
        LocaleProvider provider = mock(LocaleProvider.class);
        when(provider.getLocale()).thenReturn(Locale.US);
        return provider;
    }

}
