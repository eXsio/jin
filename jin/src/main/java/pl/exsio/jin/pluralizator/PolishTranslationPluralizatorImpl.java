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
package pl.exsio.jin.pluralizator;

public class PolishTranslationPluralizatorImpl extends AbstractTranslationPluralizatorImpl {

    @Override
    protected int getChoicesIndex(String[] choices, int count) {
        int index = -1;
        switch (choices.length) {
            case 1:
                index = 0;
                break;
            case 2:
                if (count == 0) {
                    index = 1;
                } else {
                    index = (count >= -1 && count <= 1) ? 0 : 1;
                }
                break;
            case 3:
                if (count == 0) {
                    index = 2;
                } else if (count > 20 && (Math.abs(count) % 10) >= 2 && (Math.abs(count) % 10) <= 4) {
                    index = 1;
                } else if (count >= -1 && count <= 1) {
                    index = 0;
                } else if (count >= -4 && count <= 4) {
                    index = 1;
                } else {
                    index = 2;
                }
                break;
        }
        return index;
    }

    @Override
    protected int getMaxChoicesCount() {
        return 3;
    }

}
