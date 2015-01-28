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
package pl.exsio.jin.integration;

import pl.exsio.jin.annotation.TranslationPrefix;
import static pl.exsio.jin.translationcontext.TranslationContext.pluralize;
import static pl.exsio.jin.translationcontext.TranslationContext.t;
import pl.exsio.jin.translator.Translator;

/**
 *
 * @author exsio
 */
@TranslationPrefix("jin.integration")
public class TranslatedClass {

    private final Translator translator;

    public TranslatedClass(Translator translator) {
        this.translator = translator;
    }

    public String getTranslatorResult() {
        return this.translator.translate("translator_usage");
    }

    public String getTranslationContextResult() {
        return t("translation_context_usage");
    }

    public String getPluralizeTranslatorResult(int count) {
        return this.translator.pluralize("pluralize_translator_usage", count);
    }

    public String getPluralizeTranslationContextResult(int count) {
        return pluralize("pluralize_translation_context_usage", count);
    }
}
