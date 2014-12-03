package pl.exsio.jin.translator;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import pl.exsio.jin.ex.TranslationInitializationException;
import pl.exsio.jin.file.loader.TranslationFileLoader;
import pl.exsio.jin.locale.provider.provider.LocaleProviderProvider;
import pl.exsio.jin.pluralizator.TranslationPluralizator;
import pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistry;
import pl.exsio.jin.translationcontext.TranslationContext;
import pl.exsio.jin.translationprefix.manager.TranslationPrefixManager;

/**
 *
 * @author exsio
 */
public class TranslatorImpl implements Translator {

    protected TranslationFileLoader loader;

    protected LocaleProviderProvider localeProviderProvider;

    protected Map<String, String> registeredFiles;

    protected final Map<String, Map<String, String>> translations;

    protected boolean initialized = false;

    protected Locale currentLocale;

    protected TranslationPluralizatorRegistry pluralizators;

    protected TranslationPrefixManager prefixManager;

    public TranslatorImpl() {
        this.registeredFiles = new LinkedHashMap<>();
        this.translations = new HashMap();
    }

    @Override
    public void init() throws TranslationInitializationException {
        if (!this.initialized) {
            for (String filePath : this.registeredFiles.keySet()) {
                String lang = this.registeredFiles.get(filePath);
                this.loadFile(lang, filePath);
            }
            this.initialized = true;
            TranslationContext.setTranslator(this);
        }
    }

    @Override
    public boolean isInitialized() {
        return this.initialized;
    }

    protected void loadFile(String lang, String filePath) throws TranslationInitializationException {
        Map<String, String> translationMap = this.getTranslationMap(lang);
        try {
            translationMap.putAll(this.loader.loadFile(filePath));
        } catch (IOException ex) {
            throw new TranslationInitializationException("An error occured during initialization of translations", ex);
        }
    }

    public void setRegisterdFiles(Map<String, String> registeredFiles) {
        this.registeredFiles = registeredFiles;
    }

    protected Map<String, String> getTranslationMap(String lang) {
        if (!this.translations.containsKey(lang)) {
            this.translations.put(lang, new LinkedHashMap<String, String>());
        }
        return this.translations.get(lang);
    }

    @Override
    public boolean registerTranslationFile(String lang, String filePath) {
        if (!this.initialized) {
            this.registeredFiles.put(filePath, lang);
            return true;
        }
        return false;
    }

    @Override
    public String translate(String subject) {
        subject = this.formatSubject(subject);
        Map<String, String> translationsMap = this.getTranslationMap(this.getCurrentLocale().getLanguage());
        if (translationsMap instanceof Map && translationsMap.containsKey(subject)) {
            Object translation = translationsMap.get(subject);
            return translation instanceof String ? ((String) translation) : subject;
        } else {
            return subject;
        }
    }

    @Override
    public String pluralize(String options, int count) {
        if (this.pluralizators != null) {
            TranslationPluralizator pluralizator = this.pluralizators.getPluralizator(this.getCurrentLocale().getLanguage());
            if (pluralizator != null) {
                return pluralizator.pluralize(this.translate(options), count);
            }
        }
        return options;
    }

    protected String formatSubject(String subject) {
        if (this.prefixManager != null) {
            return this.prefixManager.getPrefixForTranslatedClass() + subject;
        } else {
            return subject;
        }
    }

    protected Locale getCurrentLocale() {
        return this.localeProviderProvider.getLocaleProvider().getLocale();
    }

    public void setLoader(TranslationFileLoader loader) {
        this.loader = loader;
    }

    public void setLocaleProviderProvider(LocaleProviderProvider localeProviderProvider) {
        this.localeProviderProvider = localeProviderProvider;
    }

    public void setPluralizators(TranslationPluralizatorRegistry pluralizators) {
        this.pluralizators = pluralizators;
    }

    public void setPrefixManager(TranslationPrefixManager prefixManager) {
        this.prefixManager = prefixManager;
    }

}
