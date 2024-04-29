package shadyAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shadyAuto.Controllers.ScheduleBuilderController;
import shadyAuto.FirebaseControllers.ScheduleController;
import shadyAuto.ScheduleBuilder.Schedule;

import java.util.Collection;
import java.util.Map;

/**
 * This class is obsolete and will be deleted soon
 */

public class TestSchedule extends ScheduleBuilderController {
    ScheduleController scheduleController = new ScheduleController(ShadyAuto.db);


    @FXML
    private Label loginLabel;



    @FXML
    public void createSchedule(Schedule schedule) {
        //default
//        String name = "TestSchedule";
//        String[] days = {"2-9", "", "1-8", "2-9", "2-9", "", ""};

        //New version
        String name = schedule.getName();
        String[] days = {schedule.getMonday(), schedule.getTuesday(), schedule.getWednesday(),
        schedule.getThursday(), schedule.getFriday(), schedule.getSaturday(), schedule.getSunday()};


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
    public void getAll() {
        Collection<Schedule> schedules = scheduleController.GetAllSchedules();
        if (schedules != null) {
            System.out.println("Successfully retrieved all schedules");
            //displayScheduleMethod(schedules);  // Assuming displayScheduleMethod updates the tableView
//            addSchedulesToTable(schedules);
            for (Schedule schedule : schedules) {
                System.out.println("Schedule loaded: " + schedule);
//                list.add(schedule);
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
