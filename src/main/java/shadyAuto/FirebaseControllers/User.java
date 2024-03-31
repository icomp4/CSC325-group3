package shadyAuto.FirebaseControllers;

import com.google.firebase.auth.UserRecord;
import shadyAuto.ShadyAuto;

public class User {
    public static boolean SignUp(String email, String username, String password) {
        UserRecord.CreateRequest user = new UserRecord.CreateRequest().
                setEmail(email).
                setPassword(password).
                setDisplayName(username).
                setDisabled(false);
        UserRecord userRecord;

        try {
            userRecord = ShadyAuto.fauth.createUser(user);
            System.out.println("Successfully created new user: " + userRecord.getUid());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}