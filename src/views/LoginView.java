package views;

import services.AuthService;
import services.PemilikService;
import services.AsistenService;
import core.UserSession;
import app.App;

public class LoginView {

    private final AuthService authService = new AuthService();

    public void show() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Username: ");
        String user = App.sc.nextLine();

        System.out.print("Password: ");
        String pass = App.sc.nextLine();

        boolean success = authService.login(user, pass);

        if (!success) {
            System.out.println("Login gagal! Username atau password salah.");
            show();
            return;
        }

        System.out.println("\nLogin berhasil sebagai " + UserSession.getRole() + "!");

        switch (UserSession.getRole()) {
            case "asisten" -> new AsistenView().menuAsisten();
            case "pemilik" -> new PemilikView().menuPemilik();
            default -> System.out.println("Role tidak valid.");
        }
    }
}