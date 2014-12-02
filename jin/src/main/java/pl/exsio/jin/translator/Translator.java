package pl.exsio.jin.translator;

import pl.exsio.jin.ex.TranslationInitializationException;

/**
 *
 * @author exsio
 */
public interface Translator {

    public void init() throws TranslationInitializationException;
    
    public boolean isInitialized();

    public boolean registerTranslationFile(String lang, String filePath);

    public String translate(String subject);
    
    public String pluralize(String options, int count);
}
