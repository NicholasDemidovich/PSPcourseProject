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

public class ProductEditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button editlProduct_btn;

    @FXML
    private ComboBox<String> productNumb_Cb;

    @FXML
    private TextField productDensity_field;

    @FXML
    private TextField productNumb_field;

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
        ObservableList<String> prNumb = FXCollections.observableArrayList(dataSending.selectProductNumber());
        productNumb_Cb.setItems(prNumb);

        editlProduct_btn.setOnAction(event -> {
            editProduct(productType_Cb.getValue(),productDensity_field.getText(),
                    productConditions_field.getText(),productCount_field.getText(),
                    productNumb_Cb.getValue(),productNumb_field.getText());
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
    public void editProduct(String product_type , String product_density,
                            String product_conditions, String product_count, String productNumb, String newProductNumb){
        DataSending dataSending = new DataSending();
        int result = dataSending.editProductSend(product_type,product_density,
                product_conditions,product_count,productNumb,newProductNumb);
        if(result > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Данные были успешно изменены!");

            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Данные не были изменены!");

            alert.showAndWait();
        }
        ObservableList<String> prNumb = FXCollections.observableArrayList(dataSending.selectProductNumber());
        productNumb_Cb.setItems(prNumb);
    }
}
