package com.example.csc325project;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Collection;

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
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> tableColumnName;

    @FXML
    private TableColumn<Person, String> tableColumnMonday;

    @FXML
    private TableColumn<Person, String> tableColumnTuesday;

    @FXML
    private TableColumn<Person, String> tableColumnWednesday;

    @FXML
    private TableColumn<Person, String> tableColumnThursday;

    @FXML
    private TableColumn<Person, String> tableColumnFriday;

    @FXML
    private TableColumn<Person, String> tableColumnSaturday;

    @FXML
    private TableColumn<Person, String> tableColumnSunday;

    Collection<Person> collection = new ArrayList<>();


    @FXML
    private Label title;


    public void initialize(){
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Person,String>("Name"));
        tableColumnMonday.setCellValueFactory(new PropertyValueFactory<Person,String>("Monday"));
        tableColumnTuesday.setCellValueFactory(new PropertyValueFactory<Person,String>("Tuesday"));
        tableColumnWednesday.setCellValueFactory(new PropertyValueFactory<Person,String>("Wednesday"));
        tableColumnThursday.setCellValueFactory(new PropertyValueFactory<Person,String>("Thursday"));
        tableColumnFriday.setCellValueFactory(new PropertyValueFactory<Person,String>("Friday"));
        tableColumnSaturday.setCellValueFactory(new PropertyValueFactory<Person,String>("Saturday"));
        tableColumnSunday.setCellValueFactory(new PropertyValueFactory<Person,String>("Sunday"));

    }

    public void addToScheduleButton(){
        ObservableList<Person> list = tableView.getItems();

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

        Person person = new Person(getNameTextField, getMondayTextField, getTuesdayTextField, getWednesdayTextField,
                getThursdayTextField, getFridayTextField, getSaturdayTextField, getSundayTextField);

        list.add(person);

        clearTextFields();




    }

    public void resetScheduleHandler(){
        ObservableList<Person> list = tableView.getItems();

        list.clear();
        clearTextFields();
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