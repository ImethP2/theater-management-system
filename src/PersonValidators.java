public class PersonValidators {
    public static boolean EmailChecker(String email) {
        String regex = "^[\\w-\\.-_]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }
    public static boolean NameChecker(String name) {
        // Check if the name is null or empty
        if (name == null || name.isEmpty()) {
            return false;
        }
        // Check if the name contains any numbers or special characters
        if (!name.matches("^[a-zA-Z\\s]*$")) {
            return false;
        }
        // Check if the name is within the length range
        if (name.length() < 2 || name.length() > 50) {
            return false;
        }
        return true;
    }
}
