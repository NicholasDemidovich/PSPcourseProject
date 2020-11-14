package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.ClientData.DataSending;

public class ProductShowSortController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ModelTable> table_view;

    @FXML
    private TableColumn<ModelTable, String> tblV_id;

    @FXML
    private TableColumn<ModelTable, String> tblV_numb;

    @FXML
    private TableColumn<ModelTable, String> tblV_type;

    @FXML
    private TableColumn<ModelTable, String> tblV_den;

    @FXML
    private TableColumn<ModelTable, String> tblV_cond;

    @FXML
    private TableColumn<ModelTable, String> tblV_count;

    @FXML
    private Button sortByNumb_btn;

    @FXML
    private Button sortByDen_btn;

    @FXML
    private Button sortByCount_btn;

    @FXML
    private Button back_btn;

    @FXML
    void initialize() {
       fillDefault();

        sortByNumb_btn.setOnAction(event -> {
            fillByNumb();
        });

        sortByDen_btn.setOnAction(event -> {
            fillByDen();
        });

        sortByCount_btn.setOnAction(event -> {
            fillByCount();
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

    public void fillDefault(){
        DataSending dataSending = new DataSending();
        ArrayList<String> list = new ArrayList<String>();
        list = dataSending.selectProductFullArr();
        ObservableList<ModelTable> prList = FXCollections.observableArrayList();

        int counter =0;

        while(counter < list.size()) {
            prList.add(new ModelTable(list.get(counter),list.get(counter+1),list.get(counter+2),
                    list.get(counter+3),list.get(counter+4),list.get(counter+5)));
            counter+=6;
        }

        tblV_id.setCellValueFactory(new PropertyValueFactory<>("tblV_id"));
        tblV_numb.setCellValueFactory(new PropertyValueFactory<>("tblV_numb"));
        tblV_type.setCellValueFactory(new PropertyValueFactory<>("tblV_type"));
        tblV_den.setCellValueFactory(new PropertyValueFactory<>("tblV_den"));
        tblV_cond.setCellValueFactory(new PropertyValueFactory<>("tblV_cond"));
        tblV_count.setCellValueFactory(new PropertyValueFactory<>("tblV_count"));

        table_view.setItems(prList);
    }

    public void fillByNumb(){
        DataSending dataSending = new DataSending();
        ArrayList<String> list = new ArrayList<String>();
        list = dataSending.selectProductFullArrByNumb();
        ObservableList<ModelTable> prList = FXCollections.observableArrayList();

        int counter =0;

        while(counter < list.size()) {
            prList.add(new ModelTable(list.get(counter),list.get(counter+1),list.get(counter+2),
                    list.get(counter+3),list.get(counter+4),list.get(counter+5)));
            counter+=6;
        }

        tblV_id.setCellValueFactory(new PropertyValueFactory<>("tblV_id"));
        tblV_numb.setCellValueFactory(new PropertyValueFactory<>("tblV_numb"));
        tblV_type.setCellValueFactory(new PropertyValueFactory<>("tblV_type"));
        tblV_den.setCellValueFactory(new PropertyValueFactory<>("tblV_den"));
        tblV_cond.setCellValueFactory(new PropertyValueFactory<>("tblV_cond"));
        tblV_count.setCellValueFactory(new PropertyValueFactory<>("tblV_count"));

        table_view.setItems(prList);
    }



    public void fillByDen(){
        DataSending dataSending = new DataSending();
        ArrayList<String> list = new ArrayList<String>();
        list = dataSending.selectProductFullArrByDen();
        ObservableList<ModelTable> prList = FXCollections.observableArrayList();

        int counter =0;

        while(counter < list.size()) {
            prList.add(new ModelTable(list.get(counter),list.get(counter+1),list.get(counter+2),
                    list.get(counter+3),list.get(counter+4),list.get(counter+5)));
            counter+=6;
        }

        tblV_id.setCellValueFactory(new PropertyValueFactory<>("tblV_id"));
        tblV_numb.setCellValueFactory(new PropertyValueFactory<>("tblV_numb"));
        tblV_type.setCellValueFactory(new PropertyValueFactory<>("tblV_type"));
        tblV_den.setCellValueFactory(new PropertyValueFactory<>("tblV_den"));
        tblV_cond.setCellValueFactory(new PropertyValueFactory<>("tblV_cond"));
        tblV_count.setCellValueFactory(new PropertyValueFactory<>("tblV_count"));

        table_view.setItems(prList);
    }

    public void fillByCount(){
        DataSending dataSending = new DataSending();
        ArrayList<String> list = new ArrayList<String>();
        list = dataSending.selectProductFullArrByCount();
        ObservableList<ModelTable> prList = FXCollections.observableArrayList();

        int counter =0;

        while(counter < list.size()) {
            prList.add(new ModelTable(list.get(counter),list.get(counter+1),list.get(counter+2),
                    list.get(counter+3),list.get(counter+4),list.get(counter+5)));
            counter+=6;
        }

        tblV_id.setCellValueFactory(new PropertyValueFactory<>("tblV_id"));
        tblV_numb.setCellValueFactory(new PropertyValueFactory<>("tblV_numb"));
        tblV_type.setCellValueFactory(new PropertyValueFactory<>("tblV_type"));
        tblV_den.setCellValueFactory(new PropertyValueFactory<>("tblV_den"));
        tblV_cond.setCellValueFactory(new PropertyValueFactory<>("tblV_cond"));
        tblV_count.setCellValueFactory(new PropertyValueFactory<>("tblV_count"));

        table_view.setItems(prList);
    }

    public class ModelTable{
        private String tblV_id;
        private String tblV_numb;
        private String tblV_type;
        private String tblV_den;
        private String tblV_cond;
        private String tblV_count;
        ModelTable(String tblV_id,String tblV_numb, String tblV_type,String tblV_den,
                   String tblV_cond, String tblV_count){
            this.tblV_id = tblV_id;
            this.tblV_numb = tblV_numb;
            this.tblV_type = tblV_type;
            this.tblV_den = tblV_den;
            this.tblV_cond = tblV_cond;
            this.tblV_count = tblV_count;
        }

        public String getTblV_id(){
            return tblV_id;
        }
        public String getTblV_numb(){
            return tblV_numb;
        }
        public String getTblV_type(){
            return tblV_type;
        }
        public String getTblV_den(){
            return tblV_den;
        }
        public String getTblV_cond(){
            return tblV_cond;
        }
        public String getTblV_count(){
            return tblV_count;
        }
    }
}
