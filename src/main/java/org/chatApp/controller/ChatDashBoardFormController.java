package org.chatApp.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatDashBoardFormController extends Thread implements Initializable {


    public TextField txtInputMemberName;
    public VBox vBox;
    public AnchorPane addNewClientsPane;
    public HBox adminHBox;
    public HBox managerHBox;
    public TextField adminMessageTxt;
    public Button adminSendBtn;
    public TextField managerMessageTxt;
    public Button managerSendBtn;
    public HBox hboxsetMsg;
    public Label lblReceivedMsg;


    boolean b = false;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    Socket socket;
    ArrayList<String> sent = new ArrayList<>();

    public ChatDashBoardFormController() throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        b = true;


    }

    public void closeBtnOnAction(MouseEvent mouseEvent) {
        addNewClientsPane.setVisible(false);
    }

    public void addNewClientOnAction(MouseEvent mouseEvent) {
        addNewClientsPane.setVisible(true);
    }

    public void adminHboxMouseOnAction(MouseEvent mouseEvent) throws IOException {

        managerSendBtn.setVisible(false);
        managerMessageTxt.setVisible(false);
        adminMessageTxt.setVisible(true);
        adminSendBtn.setVisible(true);


        if (b) {

            try {
                socket = new Socket("localhost", 3000);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                b = false;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            this.start();
        }
    }

    public void managerHboxMouseOnAction(MouseEvent mouseEvent) throws IOException {
        managerSendBtn.setVisible(true);
        managerMessageTxt.setVisible(true);
        adminMessageTxt.setVisible(false);
        adminSendBtn.setVisible(false);


        if (b) {

            try {
                socket = new Socket("localhost", 3000);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void enterMemberBtnOnAction(ActionEvent actionEvent) throws IOException {


        URL card = getClass().getResource("/view/MemberCard.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(card);
        HBox hBox = fxmlLoader.load();
        MemberCardController controller = fxmlLoader.getController();

        controller.memberNameLbl.setText(txtInputMemberName.getText());
        controller.lblaaaaaaaaa.setText("No new MSG");
        if (controller.memberNameLbl.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "please check the name").show();
        } else {

            hBox.setId(txtInputMemberName.getText());
            controller.memberCardHBox.setId(txtInputMemberName.getText());
            vBox.getChildren().add(hBox);

        }


    }

    public void adminSendBtnOnAction(ActionEvent actionEvent) throws IOException {


        bufferedWriter.write("Admin :" + adminMessageTxt.getText());
        bufferedWriter.newLine();
        bufferedWriter.flush();


    }

    public void managetSendBtnOnAction(ActionEvent actionEvent) throws IOException {
        bufferedWriter.write("Manager :" + managerMessageTxt.getText());
        bufferedWriter.newLine();
        bufferedWriter.flush();

    }

    @Override
    public void run() {


        try {
            while (true) {

                String s = bufferedReader.readLine();
                System.out.println(s);

                Platform.runLater(() -> {
                    lblReceivedMsg.setText(s);
                });

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

