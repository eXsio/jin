package pl.exsio.jin.locale.provider;

import java.io.Serializable;
import java.util.Locale;

/**
 *
 * @author exsio
 */
public interface LocaleProvider extends Serializable {
    
    Locale getLocale();
}
