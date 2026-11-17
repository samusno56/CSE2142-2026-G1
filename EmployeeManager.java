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
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileLine = reader.readLine();
                String[] employees = fileLine.split(",");
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
                reader.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileLine = reader.readLine();
                System.out.println(fileLine);
                String[] employees = fileLine.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex].trim());
                reader.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (command.contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String newEmployee = command.substring(1);
                writer.write(", " + newEmployee);
                writer.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (command.contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileLine = reader.readLine();
                String[] employees = fileLine.split(",");
                boolean found = false;
                String searchEmployee = command.substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].trim().equals(searchEmployee)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
                reader.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (command.contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileLine = reader.readLine();
                char[] characters = fileLine.toCharArray();
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
                reader.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (command.contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileLine = reader.readLine();
                String[] employees = fileLine.split(",");
                String targetEmployee = command.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(targetEmployee)) {
                        employees[i] = "Updated";
                    }
                }
                reader.close();
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employees));
                writer.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Updated.");
        } else if (command.contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileLine = reader.readLine();
                String[] employees = fileLine.split(",");
                String targetEmployee = command.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(targetEmployee);
                reader.close();
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Deleted.");
        }
    }
}