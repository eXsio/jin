package pl.exsio.jin.file.loader;

import java.io.IOException;
import java.util.Map;
import pl.exsio.jin.file.locator.TranslationFileLocator;

/**
 *
 * @author exsio
 */
public interface TranslationFileLoader {

    Map<String, String> loadFile(String filePath) throws IOException;

    void setLocator(TranslationFileLocator locator);
}
