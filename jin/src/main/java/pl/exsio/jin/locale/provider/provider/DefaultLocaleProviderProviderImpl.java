package pl.exsio.jin.locale.provider.provider;

import pl.exsio.jin.locale.provider.LocaleProvider;

public class DefaultLocaleProviderProviderImpl implements LocaleProviderProvider {

    protected final LocaleProvider provider;

    public DefaultLocaleProviderProviderImpl(LocaleProvider provider) {
        this.provider = provider;
    }

    @Override
    public LocaleProvider getLocaleProvider() {
        return this.provider;
    }

}
