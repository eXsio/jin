/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.exsio.jin.translationprefix.manager;

import org.junit.Test;
import static org.junit.Assert.*;
import pl.exsio.jin.JinBaseTest;
import pl.exsio.jin.annotation.TranslationPrefix;

/**
 *
 * @author exsio
 */
@TranslationPrefix("pl.exsio.jin.translationprefix.manager")
public class TranslationPrefixManagerImplTest extends JinBaseTest {

    /**
     * Test of getPrefixForTranslatedClass method, of class
     * TranslationPrefixManagerImpl.
     */
    @Test
    public void testGetPrefixForTranslatedClass() {

        TranslationPrefixManagerImpl instance = new TranslationPrefixManagerImpl();
        String expResult = "pl.exsio.jin.translationprefix.manager.";
        String result = instance.getPrefixForTranslatedClass();
        assertEquals(expResult, result);
        TranslationPrefixManagerTestClass test = new TranslationPrefixManagerTestClass();
        assertEquals(test.getPrefix(), "TestClass.");
        assertEquals(test.getPrefixForInnerPrivateClass(), "TestClass.");
        assertEquals(test.getPrefixForInnerPublicClass(), "TestClass.");
        assertEquals(test.getPrefixForAnonymousClass(), "TestClass.");
        assertEquals(test.getPrefixForInnerPublicClassWithPrefix(), "InnerPublicClassWithPrefix.");
        assertEquals(test.getPrefixForOuterDefaultClass(), "");
        assertEquals(test.getPrefixForOuterDefaultClassWithPrefix(), "OuterDefaultClassWithPrefix.");

    }

}
