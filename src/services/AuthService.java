package services;

import core.UserSession;
import models.User;
import repositories.UserRepository;

public class AuthService {

    private final UserRepository userRepo = new UserRepository();

    public boolean login(String username, String password) {
        User user = userRepo.findByUsernameAndPassword(username, password);

        if (user == null) {
            return false;
        }

        // Ambil role dari repo
        String role = userRepo.findUserRole(user.getIdUser());

        // Simpan user ke session
        UserSession.startSession(user.getIdUser(), user.getNama(), role);

        return true;
    }

    public void logout() {
        UserSession.clear();
    }
}
