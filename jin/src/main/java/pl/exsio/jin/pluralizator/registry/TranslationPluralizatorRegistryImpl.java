package pl.exsio.jin.pluralizator.registry;

import java.util.HashMap;
import java.util.Map;
import pl.exsio.jin.pluralizator.TranslationPluralizator;

public class TranslationPluralizatorRegistryImpl implements TranslationPluralizatorRegistry {

    protected Map<String, TranslationPluralizator> pluralizators;

    public TranslationPluralizatorRegistryImpl() {
        this.pluralizators = new HashMap();
    }

    @Override
    public Map<String, TranslationPluralizator> getPluralizators() {
        return this.pluralizators;
    }

    @Override
    public void setPuralizators(Map<String, TranslationPluralizator> pluralizators) {
        if (pluralizators != null) {
            this.pluralizators = pluralizators;
        }
    }

    @Override
    public void registerPluralizator(String lang, TranslationPluralizator pluralizator) {
        if (this.pluralizators != null) {
            this.pluralizators.put(lang, pluralizator);
        }
    }

    @Override
    public TranslationPluralizator getPluralizator(String lang) {
        if (this.pluralizators.containsKey(lang)) {
            return this.pluralizators.get(lang);
        } else {
            return null;
        }
    }

}
