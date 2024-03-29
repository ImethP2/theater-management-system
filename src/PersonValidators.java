/**
 * This class contains methods that validate the data of a Person object.
 * @author imeth pathirana
 * @version 2023.03.18
 */
public class PersonValidators {
    public static boolean EmailChecker(String email) {
        /**
         * This method checks if the email is valid.
         * @param email The email to be checked.
         * @return true if the email is valid, false otherwise.
         */
        String regex = "^[\\w-\\.-_]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }
    public static boolean NameChecker(String name) {
        /**
         * This method checks if the name is valid.
         * @param name The name to be checked.
         * @return true if the name is valid, false otherwise.
         */
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
