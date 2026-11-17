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
            String[] employees = readEmployeesFromFile();
            for (String employee : employees) {
                System.out.println(employee);
            }
            System.out.println(Constants.DATA_LOADED);
        } else if (command.equals(Constants.CMD_RANDOM)) {
            System.out.println(Constants.LOADING_DATA);
            String rawContent = readRawContentFromFile();
            System.out.println(rawContent);
            String[] employees = readEmployeesFromFile();
            Random random = new Random();
            int randomIndex = random.nextInt(employees.length);
            System.out.println(employees[randomIndex]);
            System.out.println(Constants.DATA_LOADED);
        } else if (command.contains(Constants.CMD_ADD)) {
            System.out.println(Constants.LOADING_DATA);
            String newEmployee = command.substring(1).trim();
            appendEmployeeToFile(newEmployee);
            System.out.println(Constants.DATA_LOADED);
        } else if (command.contains(Constants.CMD_SEARCH)) {
            System.out.println(Constants.LOADING_DATA);
            String[] employees = readEmployeesFromFile();
            boolean found = false;
            String searchEmployee = command.substring(1).trim();
            for (int i = 0; i < employees.length && !found; i++) {
                if (employees[i].equals(searchEmployee)) {
                    System.out.println(Constants.EMPLOYEE_FOUND);
                    found = true;
                }
            }
            System.out.println(Constants.DATA_LOADED);
        } else if (command.contains(Constants.CMD_COUNT)) {
            System.out.println(Constants.LOADING_DATA);
            String rawContent = readRawContentFromFile();
            char[] characters = rawContent.toCharArray();
            boolean inWord = false;
            int wordCount = 0;
            for (char currentChar : characters) {
                if (currentChar == ' ') {
                    if (!inWord) {
                        wordCount++;
                        inWord = true;
                    } else {
                        inWord = false;
                    }
                }
            }
            System.out.println(wordCount + Constants.WORD_COUNT_SUFFIX + characters.length);
            System.out.println(Constants.DATA_LOADED);
        } else if (command.contains(Constants.CMD_UPDATE)) {
            System.out.println(Constants.LOADING_DATA);
            String[] employees = readEmployeesFromFile();
            String targetEmployee = command.substring(1).trim();
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(targetEmployee)) {
                    employees[i] = Constants.UPDATED_VALUE;
                }
            }
            writeEmployeesToFile(employees);
            System.out.println(Constants.DATA_UPDATED);
        } else if (command.contains(Constants.CMD_DELETE)) {
            System.out.println(Constants.LOADING_DATA);
            String[] employees = readEmployeesFromFile();
            String targetEmployee = command.substring(1).trim();
            List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
            employeeList.remove(targetEmployee);
            writeEmployeesToFile(employeeList.toArray(new String[0]));
            System.out.println(Constants.DATA_DELETED);
        }
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