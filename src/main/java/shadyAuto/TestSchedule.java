package shadyAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shadyAuto.FirebaseControllers.ScheduleController;

import java.util.List;
import java.util.Map;

public class TestSchedule {
    ScheduleController scheduleController = new ScheduleController(ShadyAuto.db);

    @FXML
    private Label loginLabel;

    @FXML
    void createSchedule(ActionEvent event) {
        String name = "TestSchedule";
        String[] days = {"2-9", "", "1-8", "2-9", "2-9", "", ""};
        boolean created = scheduleController.CreateSchedule(name, days);
        if(created){
            System.out.println("Successfully created new schedule: " + name);
        } else {
            System.out.println("Error during CreateSchedule");
        }
    }

    @FXML
    void deleteSchedule(ActionEvent event) {
        String name = "TestSchedule";
        boolean deleted = scheduleController.DeleteSchedule(name);
        if(deleted){
            System.out.println("Successfully deleted schedule: " + name);
        } else {
            System.out.println("Error during DeleteSchedule");
        }
    }

    @FXML
    void getSchedule(ActionEvent event) {
        String name = "TestSchedule";
        Map<String, String> schedule = scheduleController.GetSchedule(name);
        if(schedule != null){
            System.out.println("Successfully retrieved schedule: " + name);
            for (Map.Entry<String, String> entry : schedule.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Error during GetSchedule");
        }
    }
    @FXML
    void getAll(ActionEvent event) {
        List<String> schedules = scheduleController.GetAllSchedules();
        if(schedules != null){
            System.out.println("Successfully retrieved all schedules");
            for (String schedule : schedules) {
                System.out.println(scheduleController.GetSchedule(schedule));
            }
        } else {
            System.out.println("Error during GetAllSchedules");
        }
    }

    @FXML
    void updateSchedule(ActionEvent event) {
        String name = "TestSchedule";
        String[] days = {"", "", "", "", "", "", ""};
        boolean updated = scheduleController.UpdateSchedule(name, days);
        if(updated){
            System.out.println("Successfully updated schedule: " + name);
        } else {
            System.out.println("Error during UpdateSchedule");
        }
    }

}
