package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.ClientData.DataSending;

public class ProductDelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button delProduct_btn;

    @FXML
    private ComboBox<String> productNumb_Cb;

    @FXML
    private TextArea txtAr_field;

    @FXML
    private Button back_btn;

    @FXML
    void initialize() {
        DataSending dataSending = new DataSending();
        ObservableList<String> prNumb = FXCollections.observableArrayList(dataSending.selectProductNumber());
        productNumb_Cb.setItems(prNumb);

        productNumb_Cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    ArrayList<String> prList = new ArrayList<String>();
                    prList = dataSending.selectProductArr(productNumb_Cb.getValue());
                    txtAr_field.setText("ID: " + prList.get(0) + "\n" +
                                        "Номер товара: " + prList.get(1) + "\n" +
                                        "Тип товара: " + prList.get(2) + "\n" +
                                        "Плотность товара: " + prList.get(3) + "\n" +
                                        "Условия хранения товара: " + prList.get(4) + "\n" +
                                        "Количество товара: " + prList.get(5));
            }
        });

        delProduct_btn.setOnAction(event -> {
            deleteProduct(productNumb_Cb.getValue());
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

    public void deleteProduct(String productNumb){
        DataSending dataSending = new DataSending();
        int result = dataSending.deleteProductSend(productNumb);
        if(result > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Данные были успешно удалены!");

            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Данные не были удалены!");

            alert.showAndWait();
        }
        ObservableList<String> prNumb = FXCollections.observableArrayList(dataSending.selectProductNumber());
        productNumb_Cb.setItems(prNumb);
    }
}

