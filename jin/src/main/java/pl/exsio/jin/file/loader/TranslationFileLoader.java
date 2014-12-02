package pl.exsio.jin.file.loader;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author exsio
 */
public interface TranslationFileLoader {

    Map<String, String> loadFile(String filePath) throws IOException;
}
