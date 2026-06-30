package software.aoc.day02;

public class MultipleRepeatedIdValidator implements IdValidator {
    private static final MultipleRepeatedIdValidator INSTANCE = new MultipleRepeatedIdValidator();

    private MultipleRepeatedIdValidator() {}

    public static MultipleRepeatedIdValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isInvalid(long id) {
        String idStr = String.valueOf(id);
        int length = idStr.length();

        for (int patternLen = 1; patternLen <= length / 2; patternLen++) {
            if (length % patternLen != 0) {
                continue;
            }

            String pattern = idStr.substring(0, patternLen);
            if (checkRepetition(idStr, pattern, patternLen)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkRepetition(String original, String pattern, int patternLen) {
        int repetitions = original.length() / patternLen;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < repetitions; i++) {
            builder.append(pattern);
        }
        return builder.toString().equals(original);
    }
}
