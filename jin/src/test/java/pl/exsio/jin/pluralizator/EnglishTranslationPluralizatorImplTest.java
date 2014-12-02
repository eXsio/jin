
package pl.exsio.jin.pluralizator;

import org.junit.Test;
import static org.junit.Assert.*;
import pl.exsio.jin.JinBaseTest;

/**
 *
 * @author exsio
 */
public class EnglishTranslationPluralizatorImplTest extends JinBaseTest {
    
    @Test
    public void testPluralize2Options() {
        String options = "%count% apple|%count% apples";
        EnglishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 apple");
        assertEquals(instance.pluralize(options, -1), "-1 apple");
        assertEquals(instance.pluralize(options, 2), "2 apples");
        assertEquals(instance.pluralize(options, -3), "-3 apples");
        assertEquals(instance.pluralize(options, 5), "5 apples");
        assertEquals(instance.pluralize(options, -10), "-10 apples");
        assertEquals(instance.pluralize(options, 0), "0 apples");
    }
    
    private EnglishTranslationPluralizatorImpl createPluralizator() {
        EnglishTranslationPluralizatorImpl instance = new EnglishTranslationPluralizatorImpl();
        return instance;
    }
    
    @Test
    public void testPluralize1Option() {
        String options = "%count% apple";
        EnglishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 apple");
        assertEquals(instance.pluralize(options, -1), "-1 apple");
        assertEquals(instance.pluralize(options, 2), "2 apple");
        assertEquals(instance.pluralize(options, -3), "-3 apple");
        assertEquals(instance.pluralize(options, 5), "5 apple");
        assertEquals(instance.pluralize(options, -10), "-10 apple");
        assertEquals(instance.pluralize(options, 0), "0 apple");
    }
    
}
