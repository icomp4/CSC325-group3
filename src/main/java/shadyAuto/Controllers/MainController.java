package shadyAuto.Controllers;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import shadyAuto.FirebaseControllers.*;
import shadyAuto.LoginScreen;
import shadyAuto.Models.Customer;
import shadyAuto.Models.Invoice;
import shadyAuto.Models.Part;
import shadyAuto.Models.Vehicle;
import shadyAuto.ShadyAuto;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

public class MainController implements Initializable {


    @FXML
    private ImageView Exit;
    @FXML
    private TextField totalTxt;
    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private TableColumn<Customer, String> firstName;
    @FXML
    private TableColumn<Customer, String> lastName;
    @FXML
    private TableColumn<Customer, String> customerID;

    @FXML
    private TableColumn<?, ?> makeTxt;

    @FXML
    private TableColumn<?, ?> modelTxt;

    @FXML
    private Label nameTxt;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Double> partPrice;

    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Vehicle, String> yearTxt;
    @FXML
    private TableColumn<Vehicle, String> vehicleID;

    @FXML
    private Button scheduleBuilderBtn;
    @FXML
    private TableView<Vehicle> vehicleTable;

    @FXML
    private AnchorPane slider;
    private VehicleController vehicleController;
    private UserController userController;
    private CustomerController customerController;
    private InvoiceController invoiceController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partTable.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partPrice.setCellFactory(column -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.toString());
                }
            }
        });
        partTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            ObservableList<Part> selectedParts = partTable.getSelectionModel().getSelectedItems();
            double total = 0;
            for (Part part : selectedParts) {
                total += part.getPrice();
            }
            totalTxt.setText(String.format("$%.2f", total));
        });
        totalTxt.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        userController = new UserController(ShadyAuto.db);
        customerController = new CustomerController(ShadyAuto.db);

        // Example username retrieved from the login session
        String currentUsername = LoginScreen.currentUser;
        System.out.println(currentUsername);

        // Check if the user is a manager and modify the button's visibility
        boolean isManager = userController.getIsManagerStatus(currentUsername);
        scheduleBuilderBtn.setVisible(isManager);
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
        nameTxt.setText("Welcome Back " + LoginScreen.employeeName + "!");

        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        vehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        makeTxt.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelTxt.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearTxt.setCellValueFactory(new PropertyValueFactory<>("year"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        Part[] parts = new Part[6];
        parts[0] = new Part("1", "Brake Pads", 50.00);
        parts[1] = new Part("2", "Oil Filter", 10.00);
        parts[2] = new Part("3", "Air Filter", 15.00);
        parts[3] = new Part("4", "Spark Plugs", 20.00);
        parts[4] = new Part("5", "Battery", 100.00);
        parts[5] = new Part("6", "Tires", 200.00);
        for (Part part : parts) {
            partTable.getItems().add(part);
        }

        Customer[] customers = customerController.GetAllCustomers();
        for (Customer customer : customers) {
            customerTable.getItems().add(customer);
        }
        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
                updateVehicleTable(selectedCustomer);
            }
        });


    }
    private void updateVehicleTable(Customer customer) {
        vehicleController = new VehicleController(ShadyAuto.db);
        vehicleTable.getItems().clear();
        Vehicle[] vehicles = vehicleController.GetByOwnerID(customer.getCustomerID());
        vehicleTable.getItems().addAll(vehicles);
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
    void OpenEmployeesSchedule(ActionEvent event) {
        try {
            ShadyAuto.setRoot("employee-schedule");
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

    @FXML
    void CreateInvoice(ActionEvent event) {
        invoiceController = new InvoiceController(ShadyAuto.db);
        ObservableList<Part> selectedParts = partTable.getSelectionModel().getSelectedItems();
        String vehicleID = vehicleTable.getSelectionModel().getSelectedItem().getVehicleID();
        String customerID = customerTable.getSelectionModel().getSelectedItem().getCustomerID();
        String invoiceID = UUID.randomUUID().toString();
        String currentDate = new Date().toString();
        ArrayList<Part> parts = new ArrayList<>();
        for (Part part : selectedParts) {
            parts.add(new Part(part.getPartID(), part.getName(), part.getPrice()));
        }
        Invoice invoice = new Invoice(invoiceID, vehicleID, customerID, parts, currentDate);
        boolean created = invoiceController.CreateInvoice(invoice);
        if (created) {
            System.out.println("Invoice created successfully");
        } else {
            System.out.println("Invoice creation failed");
        }
    }

    @FXML
    void Logout(ActionEvent event) {
        try {
            ShadyAuto.setRoot("LoginScreen");
            LoginScreen.currentUser = "";
            LoginScreen.employeeName = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
