package pl.exsio.jin.file.locator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DefaultTranslationFileLocatorImpl implements TranslationFileLocator {

    @Override
    public InputStream locateFile(String filePath) throws IOException {
        return new FileInputStream(new File(filePath));
    }

}
