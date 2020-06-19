package Controllers;

import Services.ItemService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;


public class AdministratorPageController {

    @FXML
    private TilePane tilepane;
     @FXML
     private Button additem;
    @FXML
    private ScrollPane scrollbar;

     @FXML
     private Button Back;

    @FXML
    private void initialize(){
        ItemService.injectmp(this);
        scrollbar.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollbar.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    @FXML
    void gotocategoriespage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CategoriesPage.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Administrator Page");
        stage.setScene(new Scene(root, 1500, 900));
        Stage stage1 = (Stage) Back.getScene().getWindow();
        stage1.close();
        stage.show();
    }

    @FXML
    void gotoadditempage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddItemPage.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Item Page");
        stage.setScene(new Scene(root, 1500, 900));
        Stage stage1 = (Stage) additem.getScene().getWindow();
        stage1.close();
        stage.show();
    }






    public TilePane getTilepane() {
        tilepane.getChildren().clear();
        return tilepane;
    }
}




