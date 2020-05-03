package Controllers;

import Services.ItemService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;

public class ShopPageController {
    @FXML
    private TilePane tilePane;
    @FXML
    private Button back;

    @FXML
    private void initialize(){
        ItemService.injectmp(this);
    }

    public TilePane getTilePane() {
        tilePane.getChildren().clear();
        return tilePane;
    }

        @FXML
        void gotocategories(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CategoriesPage.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Shop Page");
        stage.setScene(new Scene(root, 1500, 900));
        Stage stage1 = (Stage) back.getScene().getWindow();
        stage1.close();
        stage.show();
    }




}
