package codechallengue1;

public class ValidUtils {
    public static boolean isFloat(String string) {

        try {
            Float.parseFloat(string);
            return true;
        } catch (NumberFormatException ignored) {
        }

        return false;
    }
}
