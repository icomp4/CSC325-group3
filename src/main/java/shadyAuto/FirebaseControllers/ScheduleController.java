package shadyAuto.FirebaseControllers;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleController {
    FirestoreDBConnection db;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    public ScheduleController(FirestoreDBConnection db){
        this.db = db;
    }

    /*
    * CreateSchedule creates a new schedule in the Firestore database
    * @param name: the name of the schedule
    * @param days: an array of strings representing the hours for each day of the week
    * @return boolean: true if the schedule was successfully created, false otherwise
     */
    public boolean CreateSchedule(String name, String[] days){
        try {
            Map<String, Object> schedule = new HashMap<>();
            schedule.put("name", name);
            schedule.put("monday", days[0]);
            schedule.put("tuesday", days[1]);
            schedule.put("wednesday", days[2]);
            schedule.put("thursday", days[3]);
            schedule.put("friday", days[4]);
            schedule.put("saturday", days[5]);
            schedule.put("sunday", days[6]);
            db.initialize().collection("schedules").document(name).set(schedule);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during CreateSchedule", e);
            return false;
        }
    }

    /*
    * DeleteSchedule deletes an existing schedule from the Firestore database
    * @param name: the name of the schedule
    * @return boolean: true if the schedule was successfully deleted, false otherwise
     */
    public boolean DeleteSchedule(String name){
        try {
            db.initialize().collection("schedules").document(name).delete();
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during DeleteSchedule", e);
            return false;
        }
    }

    /*
    * UpdateSchedule updates an existing schedule in the Firestore database
    * @param name: the name of the schedule
    * @param days: an array of strings representing the hours for each day of the week
    * @return boolean: true if the schedule was successfully updated, false otherwise
     */
    public boolean UpdateSchedule(String name, String[] days){
        try {
            Map<String, Object> schedule = new HashMap<>();
            schedule.put("name", name);
            schedule.put("monday", days[0]);
            schedule.put("tuesday", days[1]);
            schedule.put("wednesday", days[2]);
            schedule.put("thursday", days[3]);
            schedule.put("friday", days[4]);
            schedule.put("saturday", days[5]);
            schedule.put("sunday", days[6]);
            db.initialize().collection("schedules").document(name).set(schedule);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during UpdateSchedule", e);
            return false;
        }
    }

    /*
    * GetSchedule retrieves an existing schedule from the Firestore database
    * @param name: the name of the schedule
    * @return Map<String, String>: a map of the days of the week and their corresponding hours
     */
    public Map<String, String> GetSchedule(String name){
        try {
            Map<String, Object> schedule = db.initialize().collection("schedules").document(name).get().get().getData();
            Map<String, String> days = new HashMap<>();
            days.put("monday", (String) schedule.get("monday"));
            days.put("tuesday", (String) schedule.get("tuesday"));
            days.put("wednesday", (String) schedule.get("wednesday"));
            days.put("thursday", (String) schedule.get("thursday"));
            days.put("friday", (String) schedule.get("friday"));
            days.put("saturday", (String) schedule.get("saturday"));
            days.put("sunday", (String) schedule.get("sunday"));
            return days;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during GetSchedule", e);
            return null;
        }
    }

    /*
    * GetAllSchedules retrieves all schedules from the Firestore database
    * @return List<String>: a list of all schedule names, only returning name so GetSchedule can be called on each name later
    * @return null if an error occurs
     */
    public List<String> GetAllSchedules(){
        try {
            List<QueryDocumentSnapshot> documents = db.initialize().collection("schedules").get().get().getDocuments();
            List<String> scheduleNames = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                scheduleNames.add(document.getId());
            }
            return scheduleNames;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during GetAllSchedules", e);
            return null;
        }
    }
}
