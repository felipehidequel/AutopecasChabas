package view;

public class Logg {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void warning(String message) {
        System.out.println(ANSI_RED + "[SYSTEM]" +  ANSI_RESET + message);
    }

    public static void info(String message) {
        System.out.println(ANSI_GREEN + "[SYSTEM]" + ANSI_RESET + message);
    }

    public static void main(String[] args) {
        warning("Teste");
        info("Teste");
    }
}
