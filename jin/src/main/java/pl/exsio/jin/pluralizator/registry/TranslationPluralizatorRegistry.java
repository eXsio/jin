package pl.exsio.jin.pluralizator.registry;

import java.util.Map;
import pl.exsio.jin.pluralizator.TranslationPluralizator;

/**
 *
 * @author exsio
 */
public interface TranslationPluralizatorRegistry {

    Map<String, TranslationPluralizator> getPluralizators();

    void setPuralizators(Map<String, TranslationPluralizator> pluralizators);

    void registerPluralizator(String lang, TranslationPluralizator pluralizator);

    TranslationPluralizator getPluralizator(String lang);
}
