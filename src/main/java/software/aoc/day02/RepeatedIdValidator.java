package software.aoc.day02;

public class RepeatedIdValidator implements IdValidator {
    private static final RepeatedIdValidator INSTANCE = new RepeatedIdValidator();

    private RepeatedIdValidator() {}

    public static RepeatedIdValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isInvalid(long id) {
        String idStr = String.valueOf(id);
        int length = idStr.length();
        if (length % 2 != 0) {
            return false;
        }
        int mid = length / 2;
        String firstHalf = idStr.substring(0, mid);
        String secondHalf = idStr.substring(mid);
        return firstHalf.equals(secondHalf);
    }
}
