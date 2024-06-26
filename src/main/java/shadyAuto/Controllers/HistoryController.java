package shadyAuto.Controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import shadyAuto.FirebaseControllers.InvoiceController;
import shadyAuto.FirebaseControllers.UserController;
import shadyAuto.LoginScreen;
import shadyAuto.Models.Invoice;
import shadyAuto.ShadyAuto;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    InvoiceController invoiceController = new InvoiceController(ShadyAuto.db);
    private UserController userController;

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private Button accountBtn;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> customerNameColumn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> invoiceIDColumn;

    @FXML
    private TableView<Invoice> invoiceTable;

    @FXML
    private TableColumn<?, ?> partsColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private Button scheduleBtn;

    @FXML
    private AnchorPane slider;

    @FXML
    private TableColumn<?, ?> vehicleColumn;

    @FXML
    private Button scheduleBuilderBtn;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userController = new UserController(ShadyAuto.db);
        String currentUsername = LoginScreen.currentUser;
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

        // Define what value to display in each cell
        invoiceIDColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceID"));
        vehicleColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleDetails"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        partsColumn.setCellValueFactory(new PropertyValueFactory<>("partsNames"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Get the data
        Invoice[] invoices = invoiceController.GetAllInvoices();

        // Add the data to the table
        for (Invoice invoice : invoices) {
            invoiceTable.getItems().add(invoice);
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
