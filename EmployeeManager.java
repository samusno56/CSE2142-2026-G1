import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid number of arguments. Please provide exactly one argument.");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployeesFromFile();
            for (String employee : employees) {
                System.out.println(employee);
            }
            System.out.println("Data Loaded.");
        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            String rawContent = readRawContentFromFile();
            System.out.println(rawContent);
            String[] employees = readEmployeesFromFile();
            Random random = new Random();
            int randomIndex = random.nextInt(employees.length);
            System.out.println(employees[randomIndex]);
            System.out.println("Data Loaded.");
        } else if (command.contains("+")) {
            System.out.println("Loading data ...");
            String newEmployee = command.substring(1).trim();
            appendEmployeeToFile(newEmployee);
            System.out.println("Data Loaded.");
        } else if (command.contains("?")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployeesFromFile();
            boolean found = false;
            String searchEmployee = command.substring(1).trim();
            for (int i = 0; i < employees.length && !found; i++) {
                if (employees[i].equals(searchEmployee)) {
                    System.out.println("Employee found!");
                    found = true;
                }
            }
            System.out.println("Data Loaded.");
        } else if (command.contains("c")) {
            System.out.println("Loading data ...");
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
            System.out.println(wordCount + " word(s) found " + characters.length);
            System.out.println("Data Loaded.");
        } else if (command.contains("u")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployeesFromFile();
            String targetEmployee = command.substring(1).trim();
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(targetEmployee)) {
                    employees[i] = "Updated";
                }
            }
            writeEmployeesToFile(employees);
            System.out.println("Data Updated.");
        } else if (command.contains("d")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployeesFromFile();
            String targetEmployee = command.substring(1).trim();
            List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
            employeeList.remove(targetEmployee);
            writeEmployeesToFile(employeeList.toArray(new String[0]));
            System.out.println("Data Deleted.");
        }
    }

    private static String readRawContentFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("employees.txt")))) {
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
        String[] employees = rawContent.split(",");
        for (int i = 0; i < employees.length; i++) {
            employees[i] = employees[i].trim();
        }
        return employees;
    }

    private static void appendEmployeeToFile(String newEmployee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true))) {
            writer.write(", " + newEmployee);
        } catch (IOException exception) {
        }
    }

    private static void writeEmployeesToFile(String[] employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            writer.write(String.join(", ", employees));
        } catch (IOException exception) {
        }
    }
}