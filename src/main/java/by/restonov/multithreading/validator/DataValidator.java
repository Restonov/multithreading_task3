package by.restonov.multithreading.validator;

public class DataValidator {

    public static boolean validateTerminalData(String data) {
        boolean flag;
        try {
            Integer.parseInt(data);
            flag = true;
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }
}
