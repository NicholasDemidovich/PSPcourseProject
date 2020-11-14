package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ClientData.DataSending;
import sample.animations.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    void initialize() {

        loginBtn.setOnAction(event -> {
            String loginTxt = login_field.getText().trim();
            String passwordTxt = password_field.getText().trim();

            if(!loginTxt.equals("") && !passwordTxt.equals(""))
                authUser(loginTxt, passwordTxt);
            else System.out.println("Заполните все поля!");


        });



        signUpBtn.setOnAction(event -> {
            signUpBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/signUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

    }

    private void authUser(String loginTxt, String passwordTxt) {
        DataSending dataSending = new DataSending();
        String result = dataSending.loginFormSending(loginTxt,passwordTxt);
        System.out.println(result);
        if(result.equals("true")) {


            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Авторизация выолнена успешно!");

            alert.showAndWait();
            loginBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/app.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Parent root = loader.getRoot();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        else {
            Shake loginAnim = new Shake(login_field);
            Shake passAnim = new Shake(password_field);
            loginAnim.playAnimation();
            passAnim.playAnimation();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Логин или пароль был введен не верно!");

            alert.showAndWait();
        }
    }
}

