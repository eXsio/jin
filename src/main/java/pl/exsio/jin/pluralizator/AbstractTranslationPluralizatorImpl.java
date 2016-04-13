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
package pl.exsio.jin.pluralizator;

public abstract class AbstractTranslationPluralizatorImpl implements TranslationPluralizator {

    String[] splitChoices(String choices) {
        return choices.split("\\|");
    }

    @Override
    public String pluralize(String options, int count) {
        String[] choices = splitChoices(options);
        int index = getChoicesIndex(choices, count);
        if (choices.length > 0 && choices.length <= getMaxChoicesCount() && index >= 0) {
            if (index < choices.length) {
                return applyCount(choices[index], count);
            } else {
                return "invalid choice index: " + index + ", options size: " + choices.length;
            }
        } else {
            return "invalid options: " + options;
        }
    }

    protected abstract int getChoicesIndex(String[] choices, int count);

    protected abstract int getMaxChoicesCount();

    String applyCount(String choice, int count) {
        return choice.replace(COUNT_PLACEHOLDER, Integer.toString(count));
    }

}
