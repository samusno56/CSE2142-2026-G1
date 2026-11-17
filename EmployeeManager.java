import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(Constants.ERROR_ARG_COUNT);
            return;
        }

        String command = args[0];

        if (command.equals(Constants.CMD_LIST)) {
            System.out.println(Constants.LOADING_DATA);
            for (String employee : readEmployeesFromFile()) {
                System.out.println(employee);
            }
            System.out.println(Constants.DATA_LOADED);
        } else if (command.equals(Constants.CMD_RANDOM)) {
            System.out.println(Constants.LOADING_DATA);
            System.out.println(readRawContentFromFile());
            String[] employees = readEmployeesFromFile();
            if (employees.length > 0) {
                System.out.println(employees[new Random().nextInt(employees.length)]);
            }
            System.out.println(Constants.DATA_LOADED);
        } else if (command.startsWith(Constants.CMD_ADD) && command.length() > 1) {
            System.out.println(Constants.LOADING_DATA);
            appendEmployeeToFile(command.substring(1).trim());
            System.out.println(Constants.DATA_LOADED);
        } else if (command.startsWith(Constants.CMD_SEARCH) && command.length() > 1) {
            System.out.println(Constants.LOADING_DATA);
            searchEmployee(command.substring(1).trim());
            System.out.println(Constants.DATA_LOADED);
        } else if (command.equals(Constants.CMD_COUNT)) {
            System.out.println(Constants.LOADING_DATA);
            String content = readRawContentFromFile();
            int wordCount = content.trim().isEmpty()
                    ? 0
                    : content.trim().split(Constants.WHITESPACE_REGEX).length;
            System.out.println(wordCount + Constants.WORD_COUNT_SUFFIX + content.length());
            System.out.println(Constants.DATA_LOADED);
        } else if (command.startsWith(Constants.CMD_UPDATE) && command.length() > 1) {
            System.out.println(Constants.LOADING_DATA);
            String[] employees = readEmployeesFromFile();
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(command.substring(1).trim())) {
                    employees[i] = Constants.UPDATED_VALUE;
                }
            }
            writeEmployeesToFile(employees);
            System.out.println(Constants.DATA_UPDATED);
        } else if (command.startsWith(Constants.CMD_DELETE) && command.length() > 1) {
            System.out.println(Constants.LOADING_DATA);
            List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeesFromFile()));
            employeeList.remove(command.substring(1).trim());
            writeEmployeesToFile(employeeList.toArray(new String[0]));
            System.out.println(Constants.DATA_DELETED);
        } else {
            System.out.println(Constants.ERROR_INVALID_ARG);
        }
    }

    private static void searchEmployee(String targetEmployee) {
        for (String employee : readEmployeesFromFile()) {
            if (employee.equals(targetEmployee)) {
                System.out.println(Constants.EMPLOYEE_FOUND);
                return;
            }
        }
        System.out.println(Constants.EMPLOYEE_NOT_FOUND);
    }

    private static String readRawContentFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.FILE_NAME)))) {
            String line = reader.readLine();
            return line != null ? line : "";
        } catch (IOException exception) {
            return "";
        }
    }

    private static String[] readEmployeesFromFile() {
        String rawContent = readRawContentFromFile();
        if (rawContent.trim().isEmpty()) {
            return new String[0];
        }
        String[] employees = rawContent.split(Constants.COMMA_DELIMITER);
        for (int i = 0; i < employees.length; i++) {
            employees[i] = employees[i].trim();
        }
        return employees;
    }

    private static void appendEmployeeToFile(String newEmployee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME, true))) {
            writer.write(Constants.COMMA_SPACE + newEmployee);
        } catch (IOException exception) {
        }
    }

    private static void writeEmployeesToFile(String[] employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME))) {
            writer.write(String.join(Constants.COMMA_SPACE, employees));
        } catch (IOException exception) {
        }
    }
}