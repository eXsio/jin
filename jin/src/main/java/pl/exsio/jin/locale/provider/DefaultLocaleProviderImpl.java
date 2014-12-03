package pl.exsio.jin.locale.provider;

import java.util.Locale;

/**
 *
 * @author exsio
 */
public class DefaultLocaleProviderImpl implements LocaleProvider {

    protected final String language;

    public DefaultLocaleProviderImpl(String language) {
        this.language = language;
    }

    @Override
    public Locale getLocale() {
        return new Locale(this.language);
    }

}
