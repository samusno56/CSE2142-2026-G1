public class Constants {
    public static final String FILE_NAME = "employees.txt";
    public static final String DATE_FORMAT = "dd/MM/yyyy-hh:mm:ss a";

    public static final String COMMA_DELIMITER = ",";
    public static final String COMMA_SPACE = ", ";
    public static final String WHITESPACE_REGEX = "\\s+";

    public static final String CMD_LIST = "l";
    public static final String CMD_RANDOM = "s";
    public static final String CMD_ADD = "+";
    public static final String CMD_SEARCH = "?";
    public static final String CMD_COUNT = "c";
    public static final String CMD_UPDATE = "u";
    public static final String CMD_DELETE = "d";

    public static final String LOADING_DATA = "Loading data ...";
    public static final String DATA_LOADED = "Data Loaded.";
    public static final String DATA_UPDATED = "Data Updated.";
    public static final String DATA_DELETED = "Data Deleted.";
    public static final String EMPLOYEE_FOUND = "Employee found!";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found!";
    public static final String UPDATED_VALUE = "Updated";
    public static final String WORD_COUNT_SUFFIX = " word(s) found ";

    public static final String ERROR_ARG_COUNT =
            "Invalid number of arguments. Usage: java EmployeeManager <l | s | +name | ?name | c | uname | dname>";
    public static final String ERROR_INVALID_ARG =
            "Invalid argument. Usage: java EmployeeManager <l | s | +name | ?name | c | uname | dname>";
}