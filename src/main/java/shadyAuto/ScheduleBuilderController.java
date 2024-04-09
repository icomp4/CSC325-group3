package shadyAuto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import shadyAuto.ScheduleBuilder.Schedule;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

public class ScheduleBuilderController {
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
    private Label title;


    public void initialize(){
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

        list.add(schedule);
        scheduleCollection.add(schedule);

        clearTextFields();
    }



    //*****************************************************************************************************************
    //                                          Method to clear table view
    //*****************************************************************************************************************
    public void resetScheduleHandler(){
        ObservableList<Schedule> list = tableView.getItems();

        list.clear();
        scheduleCollection.clear();
        clearTextFields();
    }



    //********************************
    //Method to Delete Schedule
    //********************************


    //*****************************************************************************************************************
    //                                          Method to delete schedule
    //*****************************************************************************************************************
    public void deleteScheduleHandler(){
        ObservableList<Schedule> list = tableView.getItems();


        //********************************
        //Creating Gson builder
        //********************************
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonString = gson.toJson(new ArrayList<>());


        //****************************************************
        //Opening schedule.json file and deleting all contents
        //****************************************************
        try {
            PrintStream ps = new PrintStream("schedule.json");
            ps.println(jsonString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        //**************************************************************************************************************
        //Clearing the TableView and all information in the scheduleCollection which helps to store data into json file
        //**************************************************************************************************************
        list.clear();
        scheduleCollection.clear();


        //****************************************************
        //Gives alert window that shows the deletion occurred.
        //****************************************************
        showAlert.setAlertType(Alert.AlertType.INFORMATION);
        showAlert.setHeaderText("Schedule Deleted");
        showAlert.setContentText("Click Ok");
        showAlert.show();
    }







    //*****************************************************************************************************************
    //                                          Method to save schedule
    //*****************************************************************************************************************
    public void saveScheduleHandler(){

        //********************************
        //Creating Gson builder
        //********************************
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonString = gson.toJson(scheduleCollection);




        //****************************************************************
        //Opening schedule.json file and saving it to the file
        //****************************************************************
        try {
            PrintStream ps = new PrintStream("schedule.json");
            ps.println(jsonString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



        //*********************************************************
        //Alert window that tells user that the schedule was saved
        //*********************************************************
        showAlert.setAlertType(Alert.AlertType.INFORMATION);
        showAlert.setHeaderText("Schedule Saved");
        showAlert.setContentText("Click Ok.");
        showAlert.show();

    }





    //*****************************************************************************************************************
    //                                          Method to save schedule
    //*****************************************************************************************************************
    public void displayScheduleHandler(){
        ObservableList<Schedule> list = tableView.getItems();
        list.clear();

        //********************************
        //Creating Gson builder
        //********************************
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();



        System.out.println("Current count in collection: " + scheduleCollection.size());


        //********************************************************************************
        //Opening schedule.json file and importing it to a Collection interface "importJson"
        //********************************************************************************
        try{
            FileReader fr = new FileReader("schedule.json");
            importJson = gson.fromJson(fr, new TypeToken<ArrayList<Schedule>>(){}.getType());
        }
        catch (Exception e){
            System.out.println("Error Occurred");
        }



        //*****************************************
        //Checking to see if schedule is empty or not
        //*****************************************

        //if schedule.json file is null, schedule needs to be made.
        if(importJson.size() == 0){
            showAlert.setAlertType(Alert.AlertType.INFORMATION);
            showAlert.setHeaderText("Schedule is Empty");
            showAlert.setContentText("Create your schedule.");
            showAlert.show();
        }
        else{
            try {
                //If schedule is not empty, the schedule made beforehand will be displayed.
                scheduleCollection.clear();
                scheduleCollection.addAll(importJson);

                list.addAll(scheduleCollection);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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


    public void switchToScheduleBuilder(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("schedule-builder.fxml"));
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


}
