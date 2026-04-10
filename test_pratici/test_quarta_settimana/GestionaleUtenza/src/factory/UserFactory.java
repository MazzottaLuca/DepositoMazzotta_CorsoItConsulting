package factory;

import model.User;

/**
 * Factory per la creazione centralizzata di oggetti User.
 * Implementa il Factory Pattern.
 */
public class UserFactory {

    /**
     * Crea un nuovo oggetto User
     */
    public static User createUser(int id, String username, String password, String role){
        return new User(id, username, password, role);
    }
}