package pl.exsio.jin.pluralizator;

import org.junit.Test;
import static org.junit.Assert.*;
import pl.exsio.jin.JinBaseTest;

/**
 *
 * @author exsio
 */
public class PolishTranslationPluralizatorImplTest extends JinBaseTest {

    @Test
    public void testPluralize3Options() {
        String options = "%count% jabłko|%count% jabłka|%count% jabłek";
        PolishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 jabłko");
        assertEquals(instance.pluralize(options, -1), "-1 jabłko");
        assertEquals(instance.pluralize(options, 2), "2 jabłka");
        assertEquals(instance.pluralize(options, -3), "-3 jabłka");
        assertEquals(instance.pluralize(options, 5), "5 jabłek");
        assertEquals(instance.pluralize(options, -10), "-10 jabłek");
        assertEquals(instance.pluralize(options, 0), "0 jabłek");
        assertEquals(instance.pluralize(options, 22), "22 jabłka");
        assertEquals(instance.pluralize(options, 54), "54 jabłka");
        assertEquals(instance.pluralize(options, -156), "-156 jabłek");
    }

    private PolishTranslationPluralizatorImpl createPluralizator() {
        PolishTranslationPluralizatorImpl instance = new PolishTranslationPluralizatorImpl();
        return instance;
    }

    @Test
    public void testPluralize2Options() {
        String options = "%count% jabłko|%count% jabłka";
        PolishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 jabłko");
        assertEquals(instance.pluralize(options, -1), "-1 jabłko");
        assertEquals(instance.pluralize(options, 2), "2 jabłka");
        assertEquals(instance.pluralize(options, -3), "-3 jabłka");
        assertEquals(instance.pluralize(options, 5), "5 jabłka");
        assertEquals(instance.pluralize(options, -10), "-10 jabłka");
        assertEquals(instance.pluralize(options, 0), "0 jabłka");
    }

    @Test
    public void testPluralize1Option() {
        String options = "%count% jabłko";
        PolishTranslationPluralizatorImpl instance = createPluralizator();
        assertEquals(instance.pluralize(options, 1), "1 jabłko");
        assertEquals(instance.pluralize(options, -1), "-1 jabłko");
        assertEquals(instance.pluralize(options, 2), "2 jabłko");
        assertEquals(instance.pluralize(options, -3), "-3 jabłko");
        assertEquals(instance.pluralize(options, 5), "5 jabłko");
        assertEquals(instance.pluralize(options, -10), "-10 jabłko");
        assertEquals(instance.pluralize(options, 0), "0 jabłko");
    }

}
