package core;

public final class UserSession {

    private static Integer userId;
    private static String nama;
    private static String role;

    
    private UserSession() {
    }

    public static void startSession(int idUser, String namaUser, String userRole) {
        userId = idUser;
        nama = namaUser;
        role = userRole;
    }

    public static void clear() {
        userId = null;
        nama = null;
        role = null;
    }

    public static boolean isLoggedIn() {
        return userId != null;
    }

    public static Integer getUserId() {
        return userId;
    }

    public static String getNama() {
        return nama;
    }

    public static String getRole() {
        return role;
    }
}

