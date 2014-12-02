
package pl.exsio.jin.file.locator;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author exsio
 */
public interface TranslationFileLocator {
    
    InputStream locateFile(String filePath) throws IOException;
}
