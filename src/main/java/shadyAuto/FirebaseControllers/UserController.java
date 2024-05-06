package shadyAuto.FirebaseControllers;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.auth.UserRecord;
import shadyAuto.ShadyAuto;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserController {
    static FirestoreDBConnection db;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    public UserController(FirestoreDBConnection db) {
        UserController.db = db;
    }

    public static boolean SignUp(String fname, String lname, String email, String username, String password, boolean isManager) {
        UserRecord.CreateRequest user = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password)
                .setDisplayName(username)
                .setDisabled(false);
        UserRecord userRecord;

        try {
            userRecord = ShadyAuto.fauth.createUser(user);

            Map<String, Object> usernameMapping = new HashMap<>();
            usernameMapping.put("email", email);
            usernameMapping.put("first name", fname);
            usernameMapping.put("last name", lname);
            usernameMapping.put("isManager", isManager);

            db.initialize().collection("usernameMappings").document(username).set(usernameMapping);

            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during SignUp", e);
            return false;
        }
    }


    public String Login(String username, String password) {
        Dotenv dotenv = Dotenv.load();
        String API_KEY = dotenv.get("API_KEY");
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
        String email = getEmailByUsername(username);
        if (email == null) {
            LOGGER.warning("Email not found for username: " + username);
            return "";
        }
        Map<Object, Object> data = new HashMap<>();
        data.put("email", email);
        data.put("password", password);
        data.put("returnSecureToken", true);

        // Convert Map into JSON string
        String requestBody = new org.json.JSONObject(data).toString();

        // Create and send the HTTP request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return getNameByUsername(username);
            } else {
                LOGGER.warning("Login failed for username: " + username);
                return "";
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during Login", e);
            return "g";
        }
    }

    public String getEmailByUsername(String username) {
        try {
            DocumentSnapshot document = db.initialize().collection("usernameMappings").document(username).get().get();
            if (document.exists()) {
                return document.getString("email");
            } else {
                LOGGER.warning("Username not found: " + username);
                return null;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting email by username", e);
            return null;
        }
    }
    private String getNameByUsername(String username) {
        try {
            DocumentSnapshot document = db.initialize().collection("usernameMappings").document(username).get().get();
            if (document.exists()) {
                return document.getString("first name") + " " + document.getString("last name");
            } else {
                LOGGER.warning("Username not found: " + username);
                return null;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting name by username", e);
            return null;
        }
    }
    public boolean getIsManagerStatus(String username) {
        try {
            DocumentSnapshot document = db.initialize()
                    .collection("usernameMappings")
                    .document(username)
                    .get()
                    .get();

            if (document.exists()) {
                return document.getBoolean("isManager");
            } else {
                LOGGER.warning("Username not found: " + username);
                return false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting manager status by username", e);
            return false;
        }
    }
}