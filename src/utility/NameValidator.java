package utility;

import java.util.regex.*;

public class NameValidator {
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i)) && name.charAt(i) != ' ') {
                return false;
            }
        }

        return true;
    }

    public static String formatName(String name) {
        String res = "";
        String[] arr = name.trim().replaceAll("\\s+", " ").split(" ");
        for (String s : arr) {
            char c = Character.toUpperCase(s.charAt(0));
            s = c + s.substring(1);
            res = res.concat(s + " ");
        }
        return res.trim();
    }

    public static boolean validatePassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
