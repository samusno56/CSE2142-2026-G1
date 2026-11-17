import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Manages employee records stored in a text file via command-line arguments.
 * Supports listing, random selection, adding, searching, counting words,
 * updating, and deleting employees.
 */
public class EmployeeManager {

    public static void main(String[] args) {
        // Validate that exactly one command-line argument is supplied
        if (args == null || args.length != 1) {
            System.out.println(Constants.ERROR_ARG_COUNT);
            return;
        }

        String command = args[0];

        if (command.equals(Constants.CMD_LIST)) {
            // List all employees from the file
            System.out.println(Constants.LOADING_DATA);
            for (String employee : readEmployeesFromFile()) {
                System.out.println(employee);
            }
            System.out.println(Constants.DATA_LOADED);

        } else if (command.equals(Constants.CMD_RANDOM)) {
            // Print the raw employee list and pick one employee randomly
            System.out.println(Constants.LOADING_DATA);
            System.out.println(readRawContentFromFile());
            String[] employees = readEmployeesFromFile();
            if (employees.length > 0) {
                System.out.println(employees[new Random().nextInt(employees.length)]);
            }
            System.out.println(Constants.DATA_LOADED);

        } else if (command.startsWith(Constants.CMD_ADD) && command.length() > 1) {
            // Append a new employee to the file
            System.out.println(Constants.LOADING_DATA);
            appendEmployeeToFile(command.substring(1).trim());
            System.out.println(Constants.DATA_LOADED);

        } else if (command.startsWith(Constants.CMD_SEARCH) && command.length() > 1) {
            // Search for an employee by exact name
            System.out.println(Constants.LOADING_DATA);
            searchEmployee(command.substring(1).trim());
            System.out.println(Constants.DATA_LOADED);

        } else if (command.equals(Constants.CMD_COUNT)) {
            // Count total words and characters in the data file
            System.out.println(Constants.LOADING_DATA);
            String content = readRawContentFromFile();
            int wordCount = content.trim().isEmpty()
                    ? 0
                    : content.trim().split(Constants.WHITESPACE_REGEX).length;
            System.out.println(wordCount + Constants.WORD_COUNT_SUFFIX + content.length());
            System.out.println(Constants.DATA_LOADED);

        } else if (command.startsWith(Constants.CMD_UPDATE) && command.length() > 1) {
            // Replace matching employee name with "Updated"
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
            // Remove matching employee from the file
            System.out.println(Constants.LOADING_DATA);
            List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeesFromFile()));
            employeeList.remove(command.substring(1).trim());
            writeEmployeesToFile(employeeList.toArray(new String[0]));
            System.out.println(Constants.DATA_DELETED);

        } else {
            // Handle any unrecognized command-line argument
            System.out.println(Constants.ERROR_INVALID_ARG);
        }
    }

    /**
     * Reads the raw first line from the employees data file.
     *
     * @return The raw string line from the file, or an empty string if missing.
     */
    private static String readRawContentFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.FILE_NAME)))) {
            String line = reader.readLine();
            return (line != null) ? line : "";
        } catch (IOException exception) {
            System.out.println("Error reading file: " + exception.getMessage());
            return "";
        }
    }

    /**
     * Reads employee names from the data file and returns them as a trimmed array.
     *
     * @return An array of employee names.
     */
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

    /**
     * Appends a single new employee name to the end of the data file.
     *
     * @param newEmployee The employee name to append.
     */
    private static void appendEmployeeToFile(String newEmployee) {
        String existingContent = readRawContentFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME, true))) {
            if (existingContent.trim().isEmpty()) {
                writer.write(newEmployee);
            } else {
                writer.write(Constants.COMMA_SPACE + newEmployee);
            }
        } catch (IOException exception) {
            System.out.println("Error appending to file: " + exception.getMessage());
        }
    }

    /**
     * Overwrites the data file with the updated array of employee names.
     *
     * @param employees The array of employee names to save.
     */
    private static void writeEmployeesToFile(String[] employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME))) {
            writer.write(String.join(Constants.COMMA_SPACE, employees));
        } catch (IOException exception) {
            System.out.println("Error writing to file: " + exception.getMessage());
        }
    }

    /**
     * Searches for an employee by exact name and prints whether they were found.
     *
     * @param targetEmployee The name of the employee to search for.
     */
    private static void searchEmployee(String targetEmployee) {
        for (String employee : readEmployeesFromFile()) {
            if (employee.equals(targetEmployee)) {
                System.out.println(Constants.EMPLOYEE_FOUND);
                return;
            }
        }
        System.out.println(Constants.EMPLOYEE_NOT_FOUND);
    }
}