package shadyAuto.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import shadyAuto.ShadyAuto;

public class HistoryController {

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
    private Button dashboardBtn;

    @FXML
    private Button scheduleBtn;

    @FXML
    private AnchorPane slider;

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

}
