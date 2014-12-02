package pl.exsio.jin.translationcontext;

import pl.exsio.jin.translator.Translator;

/**
 *
 * @author exsio
 */
public class TranslationContext {

    private static Translator translator;

    public static void setTranslator(Translator translator) {
        TranslationContext.translator = translator;
    }

    public static String t(String subject) {
        if (translator != null) {
            return translator.translate(subject);
        } else {
            return subject;
        }

    }

    public static String[] t(String[] subjects) {
        if (translator != null) {
            String[] translated = new String[subjects.length];
            for (int i = 0; i < subjects.length; i++) {
                translated[i] = translator.translate(subjects[i]);
            }
            return translated;
        } else {
            return subjects;
        }
    }

    public static String pluralize(String options, int count) {
        if (translator != null) {
            return translator.pluralize(options, count);
        } else {
            return options;
        }
    }

}
