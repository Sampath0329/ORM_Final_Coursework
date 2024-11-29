package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CourseBo;
import lk.ijse.bo.custom.StudentBo;

import java.io.IOException;

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

    StudentBo studentBo = (StudentBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.STUDENT);
    CourseBo courseBo = (CourseBo) BOFactory.getInstance().getBo(BOFactory.BoTypes.COURSE);

    public void initialize()  {
        loadStudentCount();
        loadProgramCount();
    }

    private void loadProgramCount() {
        try {
            lblProgramsCount.setText(
                    String.valueOf(courseBo.getCourseCount())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStudentCount() {
        try {
            lblStudentCount.setText(
                    String.valueOf(studentBo.getStudentCount())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
