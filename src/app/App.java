package app;

import views.LoginView;
import java.util.Scanner;

public class App {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new LoginView().show();
    }
}
