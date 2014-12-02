package pl.exsio.jin.pluralizator;

public class EnglishTranslationPluralizatorImpl extends AbstractTranslationPluralizatorImpl {

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
        }
        return index;
    }

    @Override
    protected int getMaxChoicesCount() {
        return 2;
    }

}
