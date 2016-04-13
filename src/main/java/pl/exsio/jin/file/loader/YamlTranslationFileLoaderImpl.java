/* 
 * The MIT License
 *
 * Copyright 2015 exsio.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
        Map<Object, Object> loadedMap = (Map<Object, Object>) new Yaml().load(input);
        return this.parseMap(loadedMap, null);
    }

    protected Map<String, String> parseMap(Map<Object, Object> map, String parentKey) {
        Map<String, String> parsedMap = new HashMap<>();
        for (Object key : map.keySet()) {
            if (key instanceof String) {
                String mapKey = parentKey != null ? parentKey + "." + key.toString() : key.toString();
                Object value = map.get(key);
                if (value instanceof String) {
                    parsedMap.put(mapKey, (String) value);
                } else if (value instanceof Map) {
                    parsedMap.putAll(this.parseMap((Map<Object, Object>) value, mapKey));
                }
            }
        }

        return parsedMap;
    }

    @Override
    public void setLocator(TranslationFileLocator locator) {
        this.locator = locator;
    }

}