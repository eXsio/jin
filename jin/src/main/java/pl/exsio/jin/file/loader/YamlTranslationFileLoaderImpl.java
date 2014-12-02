package pl.exsio.jin.file.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import pl.exsio.jin.file.locator.TranslationFileLocator;

public class YamlTranslationFileLoaderImpl implements TranslationFileLoader {

    protected TranslationFileLocator locator;

    @Override
    public Map<String, String> loadFile(String filePath) throws IOException {
        InputStream input = this.locator.locateFile(filePath);
        Map<String, Object> loadedMap = (Map<String, Object>) new Yaml().load(input);
        return this.parseMap(loadedMap, null);
    }

    protected Map<String, String> parseMap(Map<String, Object> map, String parentKey) {
        Map<String, String> parsedMap = new HashMap<>();
        for (String key : map.keySet()) {
            String mapKey = parentKey != null ? parentKey + "." + key : key;
            Object value = map.get(key);
            if (value instanceof String) {
                parsedMap.put(mapKey, (String) value);
            } else if (value instanceof Map) {
                parsedMap.putAll(this.parseMap((Map<String, Object>) value, mapKey));
            }
        }

        return parsedMap;
    }

    public void setLocator(TranslationFileLocator locator) {
        this.locator = locator;
    }

}
