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
import com.google.api.core.ApiFuture;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.logging.Logger;
import java.util.logging.Level;

public class User {
    static FirestoreDBConnection db;
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    public User(FirestoreDBConnection db) {
        User.db = db;
    }

    public boolean SignUp(String email, String username, String password) {
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
            db.initialize().collection("usernameMappings").document(username).set(usernameMapping);
            LOGGER.info("Successfully created new user: " + userRecord.getUid());
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during SignUp", e);
            return false;
        }
    }

    public boolean Login(String username, String password) {
        Dotenv dotenv = Dotenv.load();
        String API_KEY = dotenv.get("API_KEY");
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
        String email = getEmailByUsername(username);
        if (email == null) {
            LOGGER.warning("Email not found for username: " + username);
            return false;
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
                System.out.println("Login successful for user: " + username);
                return true;
            } else {
                LOGGER.warning("Login failed for username: " + username);
                return false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during Login", e);
            return false;
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
}