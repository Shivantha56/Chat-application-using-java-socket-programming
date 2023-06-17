package org.chatApp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatDashBoardFormController implements Initializable {


    public TextField txtInputMemberName;
    public VBox vBox;
    public AnchorPane addNewClientsPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void closeBtnOnAction(MouseEvent mouseEvent) {
        addNewClientsPane.setVisible(false);
    }

    public void addNewClientOnAction(MouseEvent mouseEvent) {
        addNewClientsPane.setVisible(true);
    }

    public void adminHboxMouseOnAction(MouseEvent mouseEvent) {

    }

    public void managerHboxMouseOnAction(MouseEvent mouseEvent) {

    }

    public void enterMemberBtnOnAction(ActionEvent actionEvent) throws IOException {


        URL card = getClass().getResource("/view/MemberCard.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(card);
        HBox hBox = fxmlLoader.load();
        MemberCardController controller = fxmlLoader.getController();

        controller.memberNameLbl.setText(txtInputMemberName.getText());
        controller.lblaaaaaaaaa.setText("No new MSG");
        if (controller.memberNameLbl.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"please check th name").show();
        }else {
            vBox.getChildren().add(hBox);
        }
    }
}
