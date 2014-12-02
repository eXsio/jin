package pl.exsio.jin.pluralizator;

public abstract class AbstractTranslationPluralizatorImpl implements TranslationPluralizator {

    String[] splitChoices(String choices) {
        return choices.split("\\|");
    }

    @Override
    public String pluralize(String options, int count) {
        String[] choices = this.splitChoices(options);
        int index = this.getChoicesIndex(choices, count);
        if (choices.length > 0 && choices.length <= this.getMaxChoicesCount() && index >= 0) {
            if (index < choices.length) {
                return this.applyCount(choices[index], count);
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
