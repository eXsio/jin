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
package pl.exsio.jin.translationprefix.manager;

import pl.exsio.jin.annotation.TranslationPrefix;

/**
 *
 * @author exsio
 */
@TranslationPrefix("TestClass")
public class TranslationPrefixManagerTestClass {

    private TranslationPrefixManagerImpl prefixManager = new TranslationPrefixManagerImpl();

    public String getPrefix() {
        return this.prefixManager.getPrefixForTranslatedClass();
    }

    public String getPrefixForInnerPublicClass() {
        return new InnerPublicClass().innerPrivateMethod();
    }

    public String getPrefixForInnerPublicClassWithPrefix() {
        return new InnerPublicClassWithPrefix().innerPrivateMethod();
    }

    public String getPrefixForInnerPrivateClass() {
        return new InnerPrivateClass().innerPrivateMethod();
    }

    public String getPrefixForOuterDefaultClass() {
        return new OuterDefaultClass().publicMethod();
    }

    public String getPrefixForOuterDefaultClassWithPrefix() {
        return new OuterDefaultClassWithPrefix().publicMethod();
    }

    public String getPrefixForAnonymousClass() {
        return new AnonymousClassInterface() {

            @Override
            public String anonymousPublicMethod() {
                return prefixManager.getPrefixForTranslatedClass();
            }
        }.anonymousPublicMethod();
    }

    public interface AnonymousClassInterface {

        public String anonymousPublicMethod();
    }

    public class InnerPublicClass {

        private String innerPrivateMethod() {
            return prefixManager.getPrefixForTranslatedClass();
        }
    }

    @TranslationPrefix("InnerPublicClassWithPrefix")
    public class InnerPublicClassWithPrefix {

        private String innerPrivateMethod() {
            return prefixManager.getPrefixForTranslatedClass();
        }
    }

    private class InnerPrivateClass {

        private String innerPrivateMethod() {
            return prefixManager.getPrefixForTranslatedClass();
        }
    }

}

class OuterDefaultClass {

    private TranslationPrefixManagerImpl prefixManager = new TranslationPrefixManagerImpl();

    public String publicMethod() {
        return prefixManager.getPrefixForTranslatedClass();
    }
}

@TranslationPrefix("OuterDefaultClassWithPrefix")
class OuterDefaultClassWithPrefix {

    private TranslationPrefixManagerImpl prefixManager = new TranslationPrefixManagerImpl();

    public String publicMethod() {
        return prefixManager.getPrefixForTranslatedClass();
    }
}
