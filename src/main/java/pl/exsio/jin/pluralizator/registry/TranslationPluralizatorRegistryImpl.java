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
package pl.exsio.jin.pluralizator.registry;

import com.google.common.base.Optional;
import pl.exsio.jin.pluralizator.TranslationPluralizator;

import java.util.HashMap;
import java.util.Map;

public class TranslationPluralizatorRegistryImpl implements TranslationPluralizatorRegistry {

    private Map<String, TranslationPluralizator> pluralizators;

    public TranslationPluralizatorRegistryImpl() {
        pluralizators = new HashMap();
    }

    @Override
    public Map<String, TranslationPluralizator> getPluralizators() {
        return pluralizators;
    }

    @Override
    public void setPluralizators(Map<String, TranslationPluralizator> pluralizators) {
        if (pluralizators != null) {
            this.pluralizators = pluralizators;
        }
    }

    @Override
    public void registerPluralizator(String lang, TranslationPluralizator pluralizator) {
        if (pluralizators != null) {
            pluralizators.put(lang, pluralizator);
        }
    }

    @Override
    public Optional<TranslationPluralizator> getPluralizator(String lang) {
        return Optional.fromNullable(pluralizators.get(lang));
    }

}
