package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class DashBoardCenterFormController {

    @FXML
    private AnchorPane centerNode;

    @FXML
    private BarChart<?, ?> chartPayment;

    @FXML
    private BarChart<?, ?> chartStudent;

    @FXML
    private Label lblPayments;

    @FXML
    private Label lblProgramsCount;

    @FXML
    private Label lblStudentCount;

}
