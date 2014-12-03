/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
