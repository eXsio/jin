# Jin
[![Build Status](https://travis-ci.org/eXsio/jin.svg)](https://travis-ci.org/eXsio/jin)

Jin is a Java INternationalization library. It was created, because I couldn't find any i18n solution, that would be configurable enough and easy to use for my personal usage.

### Capabilities

Jin can (by default) load a number of YAML files, containing translations. Those files can be formatted in a simple key - value pairs or they can have nested structure (see tests for more details). 

After initializing the Translator object, Jin decides which language to use based on the Locale object returned by configured LocaleProvider.

Jin has also simple pluralization mechanizm. It can take something like this: "%count apple|%count% apples" and, based on an int passed to the pluralization method it will choose the correct option. For now, there are 2 languages supported by the pluralizator:
- Polish
- English

If You want to pluralize in other language, you have to implement your own TranslationPluralizer (best done by extending existing AbstractTranslationPluralizerImpl) and register it in TranslationPluralizerRegistry.

### Usage
There are 2 ways to use Jin:
   -  By calling Translator object directly:

   ```
    Translator::translate(String subject);
    
    Translator::pluralize(String options, int count);
    ```
    
   - Or by using static TranslationContext:
   
   ```
    import static pl.exsio.jin.translationcontext.TranslationContext.t;
    import static pl.exsio.jin.translationcontext.TranslationContext.pluralize;
    
    t(String subject);
    pluralize(String options, int count);
    ```
    
    You have to remember that as the TranslationContext uses static imports, it can use only one Translator (the one, that was initialized last). You can of course manually set the Translator object using static
    
    ```
    TranslationContext::setTranslator(Translator translator);
    ```
    
    ---
    
    There is also a feature called TranslationPrefix. It is obvious that You'll want to use the translation service multiple times in one class (eg. when creating a table, You have to translate all the column headers). It would be pointless to create a long translation string for each column (eg. "en.someclass.sometable.somecolumn"). Here comes the ``` @TranslationPrefix("some.prefix")  ``` annotation. You can annotate the class with it, and on every call to ```TranslationContext::t(String subject)``` or ```Translator::translate(String subject)``` the prefix will be appended to the beginning of the subject. 
    
    The full example of usage:
    
    ```
    
   package somepackage;

   import pl.exsio.jin.annotation.TranslationPrefix;
   import static pl.exsio.jin.translationcontext.TranslationContext.t;
   import pl.exsio.jin.translator.Translator;

   @TranslationPrefix("somepackage.someclass")
   public class SomeClass {
      
      private final Translator translator;
  
      public SomeClass(Translator translator) {
          this.translator = translator;
      }
      
      public void useTranslatorExample() {
          System.out.append(this.translator.translate("translator_usage"));
      }
      
      public void useTranslationContextExample() {
          System.out.println(t("translation_context_usage"));
      }
  }
        
   ```
    
### Installation / Initialization

Here is the full initialization example:

```
    TranslatorImpl translator = new TranslatorImpl();
    TranslationFileLoader loader = new YamlTranslationFileLoaderImpl();
    loader.setLocator(new DefaultTranslationFileLocatorImpl());
    translator.setLoader(loader);
    translator.setLocaleProviderProvider(
        new DefaultLocaleProviderProviderImpl(
            new DefaultLocaleProviderImpl("en")
        )
    );
    TranslationPluralizatorRegistry pluralizators = new TranslationPluralizatorRegistryImpl();
    pluralizators.setPuralizators(new HashMap() {
        {
            put("pl", new PolishTranslationPluralizatorImpl());
            put("en", new EnglishTranslationPluralizatorImpl());
        }
    });
    translator.setPluralizators(pluralizators);
    translator.registerTranslationFile("pl", "PATH_TO_POLISH_YAML_TRANSLATION_FILE");
    translator.registerTranslationFile("en", "PATH_TO_ENGLISH_YAML_TRANSLATION_FILE");
    try {
        translator.init();
    } catch (TranslationInitializationException ex) {
        //Handle the exception
    }
```

Optionally You can set the TranslationPrefixManager, if You want to use that feature (described in the Usage part).

```
    translator.setPrefixManager(new TranslationPrefixManagerImpl());
```

---

The main object is the Translator:
 
 ```
 pl.exsio.jin.translator.Translator
 ```
 
After instantiating it You have to inject an instance of:
 - 
    
    ```
     pl.exsio.jin.locale.provider.provider.LocaleProviderProvider
     ```
    It provides a 
    ```
    pl.exsio.jin.locale.provider.LocaleProvider
    ```
    
    instance. Based on used frameworks You can implement one, that will provide a Spring Bean or implement it in any other way. The LocaleProvider in turn can use a Request object (in Web Applications) or a persistent storage to determine which locale the user currently wants.
    
- 
    ```
    pl.exsio.jin.file.loader.TranslationFileLoader
    ```
    
    This object can load a Map<'String','String'> containing key-value pairs, that will be used by Translator.
    The default implementation is:
    
    ```
    pl.exsio.jin.file.loader.YamlTranslationFileLoaderImpl
    ```
    
    It uses SnakeYAML to accomplish the above requirement.
    The File Loader in turn requires an instance of
    
    ```
    pl.exsio.jin.file.locator.TranslationFileLocator
    ```
    
    Object of this class takes a String argument with a path to a translation file and returns an instance of
    
    ```
    java.io.InputStream
    ```
    
    which is then consumed and used by the Loader. The default implementation is:
    
    ```
    pl.exsio.jin.file.locator.DefaultTranslationFileLocator
    ```
    
    which simply uses 
    
    ```
    java.io.File
    ```
    
    to locate and load a file and returns a 
    
    ```
    java.io.FileInputStream
    ```
    
    Again You can implement your own logic here - for example using Spring Context to locate resources on the ClassPath.
- Another thing, you'll want to inject into Translator is an instance of

    ```
    pl.exsio.jin.pluralizator.registry.TranslationPluralizatorRegistry
    ```

    This is the object, that keeps all 

    ```
    pl.exsio.jin.pluralizator.TranslationPluralizator
    ```

    instances and can provide the Pluralizator for a specific language. By default Jin has 2 implementations of     Pluralizator:

    ```
    pl.exsio.jin.pluralizator.PolishTranslationPluralizatorImpl
    pl.exsio.jin.pluralizator.EnglishTranslationPluralizatorImpl
    ```

- In the end You'll want to register some YAML translation files :)

After the Translator object is set and ready to use, just call the 

```
init();
```

method and if all goes well, You'll have a ready and initialized Translator object. If, for some reason, initialization goes wrong (for example the file on the given path does not exist), you'll get a Checked Exception:

```
pl.exsio.jin.ex.TranslationInitializationException
```

After initializing the Translator sets TranslatorContext's Translator instance to itself.

### BUGS

If You find any bugs, feel free to submit PR or create an issue on GitHub: https://github.com/eXsio/jin
