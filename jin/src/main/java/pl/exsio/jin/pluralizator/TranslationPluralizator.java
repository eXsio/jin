package pl.exsio.jin.pluralizator;

import pl.exsio.jin.translator.Translator;

/**
 *
 * @author exsio
 */
public interface TranslationPluralizator {

    public static final String COUNT_PLACEHOLDER = "%count%";
    
    String pluralize(String options, int count);
}
