package app.ui;

public class Menu {
    public void show() {
        System.out.println("=== MENU ===");
        System.out.println("1. Status");
        System.out.println("2. Narzędzia");
        System.out.println("3. Autor");
        System.out.println("0. Wyjście");
    }

    public void showInvalidOption() {
        System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
    }

    public void showExitMessage() {
        System.out.println("Zamykanie aplikacji...");
    }
}