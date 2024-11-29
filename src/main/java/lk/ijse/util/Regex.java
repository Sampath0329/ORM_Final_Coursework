package lk.ijse.util;

import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    // Validates the input text against the regex pattern for the specified field type
    public static boolean isTextFieldValid(TextFeildRegex fieldType, String text) {
        String patternString = "";

        switch (fieldType) {
            case NAME:
                patternString = "^[A-Za-z\\s]{3,50}$"; // Names with 3-50 alphabetic characters or spaces
                break;
            case CONTACT:
                patternString = "^07[0-9]{8}$"; // Sri Lankan mobile numbers starting with 07
                break;
            case PRICE:
                patternString = "^\\d+(\\.\\d{1,2})?$"; // Decimal numbers with up to 2 decimal places
                break;
            case EMAIL:
                patternString = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Valid email pattern
                break;
            default:
                return false;
        }

        if (text == null || text.trim().isEmpty()) {
            return false; // Invalid if text is null or empty
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    // Sets the text color of the TextField based on validation
    public static boolean setTextColor(TextFeildRegex fieldType, TextField textField) {
        boolean isValid = isTextFieldValid(fieldType, textField.getText());
        if (isValid) {
            textField.setStyle("-fx-text-fill: green;"); // Green for valid input
        } else {
            textField.setStyle("-fx-text-fill: red;"); // Red for invalid input
        }
        return isValid;
    }
}
