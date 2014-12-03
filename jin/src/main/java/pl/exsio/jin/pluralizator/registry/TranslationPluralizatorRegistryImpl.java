/* 
 * The MIT License
 *
 * Copyright 2014 exsio.
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
