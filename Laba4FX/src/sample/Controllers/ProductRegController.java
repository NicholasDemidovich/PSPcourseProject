package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ClientData.DataSending;

public class ProductRegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField productDensity_field;

    @FXML
    private TextField productNumb_field;

    @FXML
    private Button regProduct_btn;

    @FXML
    private ComboBox<String> productType_Cb;

    @FXML
    private TextField productConditions_field;

    @FXML
    private TextField productCount_field;

    @FXML
    private Button back_btn;

    @FXML
    void initialize() {
        DataSending dataSending = new DataSending();
        ObservableList<String> langs = FXCollections.observableArrayList(dataSending.selectProductType());
        productType_Cb.setItems(langs);

        regProduct_btn.setOnAction(event -> {
            addProduct();
        });

        back_btn.setOnAction(event -> {
            back_btn.getScene().getWindow().hide();

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
            stage.show();
        });
    }

    public void addProduct(){
        String prNumb = productNumb_field.getText();
        String prDen = productDensity_field.getText();
        String prCond = productConditions_field.getText();
        String prCount = productCount_field.getText();
        String prType = productType_Cb.getValue();

        DataSending dataSending = new DataSending();
        int res = dataSending.registerProduct(prNumb, prDen, prCond, prCount, prType);
        if(res >0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Данные были успешно внесены!");

            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Данные были успешно внесены!");

            alert.showAndWait();
        }
    }
}

