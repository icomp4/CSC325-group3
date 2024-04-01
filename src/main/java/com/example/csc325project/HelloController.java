package com.example.csc325project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HelloController {
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

    public void resetScheduleHandler(){
        ObservableList<Schedule> list = tableView.getItems();

        list.clear();
        scheduleCollection.clear();
        clearTextFields();
    }


    public void deleteScheduleHandler(){
        ObservableList<Schedule> list = tableView.getItems();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();



        String jsonString = gson.toJson(new ArrayList<>());
        try {
            PrintStream ps = new PrintStream("schedule.json");
            ps.println(jsonString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        list.clear();
        scheduleCollection.clear();


        showAlert.setAlertType(Alert.AlertType.INFORMATION);
        showAlert.setHeaderText("Schedule Deleted");
        showAlert.setContentText("Continue.");
        showAlert.show();
    }

    public void saveScheduleHandler(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonString = gson.toJson(scheduleCollection);

        try {
            PrintStream ps = new PrintStream("schedule.json");
            ps.println(jsonString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        showAlert.setAlertType(Alert.AlertType.INFORMATION);
        showAlert.setHeaderText("Schedule Saved");
        showAlert.setContentText("You can Exit now");
        showAlert.show();

    }






    public void displayScheduleHandler(){
        ObservableList<Schedule> list = tableView.getItems();
        list.clear();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();



        System.out.println("Current count in collection: " + scheduleCollection.size());

        //Opening and reading schedule.json file
        try{
            FileReader fr = new FileReader("schedule.json");
            importJson = gson.fromJson(fr, new TypeToken<ArrayList<Schedule>>(){}.getType());
        }
        catch (Exception e){
            System.out.println("Error Occurred");
        }




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

}