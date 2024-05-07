package shadyAuto.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import shadyAuto.FirebaseControllers.ScheduleController;
import shadyAuto.FirebaseControllers.UserController;
import shadyAuto.LoginScreen;
import shadyAuto.ScheduleBuilder.Schedule;
import shadyAuto.ShadyAuto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.*;

public class ScheduleBuilderController implements Initializable {
    ScheduleController scheduleController = new ScheduleController(ShadyAuto.db);
    private UserController userController;

    @FXML
    private Button scheduleBuilderBtn;

    @FXML
    private ImageView Exit;

    //********************************
    //TextFields Fx:id
    //********************************
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField mondayTextField;

    @FXML
    private TextField tuesdayTextField;

    @FXML
    private TextField wednesdayTextField;

    @FXML
    private TextField thursdayTextField;

    @FXML
    private TextField fridayTextField;

    @FXML
    private TextField saturdayTextField;

    @FXML
    private TextField sundayTextField;





    //********************************
    //Table View Fx:id
    //********************************
    @FXML
    private TableView<Schedule> tableView;

    @FXML
    private TableColumn<Schedule, String> tableColumnName;

    @FXML
    private TableColumn<Schedule, String> tableColumnMonday;

    @FXML
    private TableColumn<Schedule, String> tableColumnTuesday;

    @FXML
    private TableColumn<Schedule, String> tableColumnWednesday;

    @FXML
    private TableColumn<Schedule, String> tableColumnThursday;

    @FXML
    private TableColumn<Schedule, String> tableColumnFriday;

    @FXML
    private TableColumn<Schedule, String> tableColumnSaturday;

    @FXML
    private TableColumn<Schedule, String> tableColumnSunday;

    //Hold Schedule Data
    Collection<Schedule> scheduleCollection = new ArrayList<>();


    //Temporary Holds schedule data from json file which will be dumped into the scheduleCollection Interface
    Collection<Schedule> importJson = new ArrayList<>();



    //Alerts
    Alert showAlert = new Alert(Alert.AlertType.NONE);

    @FXML
    private Label Menu;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label MenuClose;
    @FXML
    private Label title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userController = new UserController(ShadyAuto.db);
        String currentUsername = LoginScreen.currentUser;
        boolean isManager = userController.getIsManagerStatus(currentUsername);
        scheduleBuilderBtn.setVisible(isManager);
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        slider.setTranslateX(0); //-176 to show again
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });

        tableColumnName.setCellValueFactory(new PropertyValueFactory<Schedule,String>("Name"));
        tableColumnMonday.setCellValueFactory(new PropertyValueFactory<Schedule,String>("Monday"));
        tableColumnTuesday.setCellValueFactory(new PropertyValueFactory<Schedule,String>("Tuesday"));
        tableColumnWednesday.setCellValueFactory(new PropertyValueFactory<Schedule,String>("Wednesday"));
        tableColumnThursday.setCellValueFactory(new PropertyValueFactory<Schedule,String>("Thursday"));
        tableColumnFriday.setCellValueFactory(new PropertyValueFactory<Schedule,String>("Friday"));
        tableColumnSaturday.setCellValueFactory(new PropertyValueFactory<Schedule,String>("Saturday"));
        tableColumnSunday.setCellValueFactory(new PropertyValueFactory<Schedule,String>("Sunday"));
    }







    //*****************************************************************************************************************
    //                                      Method to add information to schedule
    //*****************************************************************************************************************
    public void addToScheduleButton(){
        ObservableList<Schedule> list = tableView.getItems();

        String getNameTextField, getMondayTextField, getTuesdayTextField, getWednesdayTextField, getThursdayTextField,
                getFridayTextField, getSaturdayTextField, getSundayTextField;

        getNameTextField = nameTextField.getText();
        getMondayTextField = mondayTextField.getText();
        getTuesdayTextField = tuesdayTextField.getText();
        getWednesdayTextField = wednesdayTextField.getText();
        getThursdayTextField = thursdayTextField.getText();
        getFridayTextField = fridayTextField.getText();
        getSaturdayTextField = saturdayTextField.getText();
        getSundayTextField = sundayTextField.getText();

        Schedule schedule = new Schedule(getNameTextField, getMondayTextField, getTuesdayTextField, getWednesdayTextField,
                getThursdayTextField, getFridayTextField, getSaturdayTextField, getSundayTextField);


        //Adding schedule data to table view
        list.add(schedule);

        //Adding schedule data to firebase
        createSchedule(schedule);

        clearTextFields();
    }








    //*****************************************************************************************************************
    //                                          Method to clear table view
    //*****************************************************************************************************************
    public void clearScheduleTableHandler(){
        ObservableList<Schedule> list = tableView.getItems();

        list.clear();
        scheduleCollection.clear();
        clearTextFields();
    }








    //*****************************************************************************************************************
    //                                          Method to delete schedule
    //*****************************************************************************************************************
    public void deleteScheduleHandler(){
        ObservableList<Schedule> list = tableView.getItems();


        //********************************
        //Creating Gson builder
        //********************************



        //**************************************************************************************************************
        //Clearing the TableView and all information in the firebase
        //**************************************************************************************************************
        list.clear();
        deleteSchedule();

        //****************************************************
        //Gives alert window that shows the deletion occurred.
        //****************************************************
        showAlert.setAlertType(Alert.AlertType.INFORMATION);
        showAlert.setHeaderText("Schedule Deleted");
        showAlert.setContentText("Continue");
        showAlert.show();
    }

















    //*****************************************************************************************************************
    //                                          Method to display schedule
    //*****************************************************************************************************************
    public void displayScheduleHandler(){
        getAll();
    }












    //*****************************************************************************************************************
    //                                          Method to remove row from table view
    //*****************************************************************************************************************
    public void removeSelectedRow(){
        Schedule selectedItem = tableView.getSelectionModel().getSelectedItem();

        //If user chooses a row, it will get deleted. Otherwise, an alert window appears to select a row to delete
        if(selectedItem != null){
            tableView.getItems().remove(selectedItem);
            scheduleCollection.remove(selectedItem);
            System.out.println("Removed Row: " + selectedItem.toString());

            deleteSelectedSchedule(selectedItem);
        }
        else{

            //*********************************************************
            //Alert window that tells user to select a row to delete.
            //*********************************************************
            showAlert.setAlertType(Alert.AlertType.INFORMATION);
            showAlert.setHeaderText("Select a Row to Remove");
            showAlert.setContentText("Continue");
            showAlert.show();
        }

    }








    //*****************************************************************************************************************
    //                         Method to clear TextFields once information is displayed on schedule
    //*****************************************************************************************************************
    public void clearTextFields(){
        nameTextField.clear();
        mondayTextField.clear();
        tuesdayTextField.clear();
        wednesdayTextField.clear();
        thursdayTextField.clear();
        fridayTextField.clear();
        saturdayTextField.clear();
        sundayTextField.clear();
    }








    //*****************************************************************************************************************
    //                                      Switching and Navigating Between Windows
    //*****************************************************************************************************************
    public void switchToEmployeeSchedule(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("employee-schedule.fxml"));
            Parent root = loader.load();

            Stage stage = ShadyAuto.getPrimaryStage();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }





    /**
     * New FireBase Methods added
     */

    @FXML
    public void createSchedule(Schedule schedule) {
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
    void deleteSelectedSchedule(Schedule selectedItem) {
//        String name = "TestSchedule";
        String name = selectedItem.getName();
        boolean deleted = scheduleController.DeleteSelectedSchedule(name);
        if(deleted){
            System.out.println("Successfully deleted schedule: " + name);
        } else {
            System.out.println("Error during DeleteSchedule");
        }
    }

    @FXML
    void deleteSchedule() {
//        String name = "TestSchedule";
        Collection<Schedule> schedules = scheduleController.GetAllSchedules();
        for(Schedule schedule: schedules){
            String name = schedule.getName();
            boolean deleted = scheduleController.DeleteSchedule(name);
            if(deleted){
                System.out.println("Successfully deleted schedule: " + name);
            } else {
                System.out.println("Error during DeleteSchedule");
            }
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
        ObservableList<Schedule> list = tableView.getItems();
        list.clear();
        Collection<Schedule> schedules = scheduleController.GetAllSchedules();
        if (schedules != null) {
            System.out.println("Successfully retrieved all schedules");
            for (Schedule schedule : schedules) {
//                System.out.println("Schedule loaded: " + schedule);
                list.add(schedule);
            }
        } else {
            System.out.println("Error during GetAllSchedules");
        }
    }



    @FXML
    void updateSchedule(ActionEvent event) {
        String name = "TestSchedule";
        String[] days = {"", "", "", "", "", "", ""};

//        String name = selectedItem.getName();
//        String[] days = {selectedItem.getMonday(), selectedItem.getTuesday(), selectedItem.getWednesday(),
//        selectedItem.getThursday(), selectedItem.getFriday(), selectedItem.getSaturday(), selectedItem.getSunday()};

        boolean updated = scheduleController.UpdateSchedule(name, days);
        if(updated){
            System.out.println("Successfully updated schedule: " + name);
        } else {
            System.out.println("Error during UpdateSchedule");
        }
    }

    @FXML
    void switchToMainScreen(ActionEvent event){
        try {
            ShadyAuto.setRoot("MainScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    @FXML
    void OpenAddScreen(ActionEvent event) {
        try {
            ShadyAuto.setRoot("AddScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OpenDashboard(ActionEvent event) {
        try {
            ShadyAuto.setRoot("MainScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OpenSchedule(ActionEvent event) {
        try {
            ShadyAuto.setRoot("employee-schedule");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OpenScheduleBuilder(ActionEvent event) {
        try {
            ShadyAuto.setRoot("schedule-builder");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OpenHistory(ActionEvent event) {
        try {
            ShadyAuto.setRoot("HistoryScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
