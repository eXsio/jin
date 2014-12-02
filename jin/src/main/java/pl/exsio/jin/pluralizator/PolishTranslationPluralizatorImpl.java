package pl.exsio.jin.pluralizator;

public class PolishTranslationPluralizatorImpl extends AbstractTranslationPluralizatorImpl {

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
            case 3:
                if (count == 0) {
                    index = 2;
                } else if (count > 20 && (Math.abs(count) % 10) >= 2 && (Math.abs(count) % 10) <= 4) {
                    index = 1;
                } else if (count >= -1 && count <= 1) {
                    index = 0;
                } else if (count >= -4 && count <= 4) {
                    index = 1;
                } else {
                    index = 2;
                }
                break;
        }
        return index;
    }

    @Override
    protected int getMaxChoicesCount() {
        return 3;
    }

}
