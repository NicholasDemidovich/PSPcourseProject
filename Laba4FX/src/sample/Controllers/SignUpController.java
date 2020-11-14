package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ClientData.DataSending;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpLogin_field;

    @FXML
    private PasswordField signUpPassword_field;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField signUpName_field;

    @FXML
    private TextField signUpSurname_field;

    @FXML
    private TextField signUpEmail_field;

    @FXML
    private TextField signUpCountry_field;

    @FXML
    private Button back_btn;


    @FXML
    void initialize() {

        signUpBtn.setOnAction(event ->{
            DataSending dataSending = new DataSending();
            dataSending.signUpFormSending(signUpName_field.getText(), signUpSurname_field.getText(),
                    signUpLogin_field.getText(), signUpPassword_field.getText(),
                    signUpEmail_field.getText(), signUpCountry_field.getText());
        });

        back_btn.setOnAction(event -> {
            back_btn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/sample.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
