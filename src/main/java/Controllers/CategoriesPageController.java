package Controllers;

import Services.ItemService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoriesPageController {
    @FXML
    private Button pets;
    @FXML
    private Button toys;
    @FXML
    private Button accessories;
    @FXML
    private Button food;

    @FXML
   private void initialize(){
        try {
            ItemService.loadItemFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoShopPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ShopPage.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Shop Page");
        stage.setScene(new Scene(root, 1500,900));
        Stage stage1 = (Stage) food.getScene().getWindow();
        stage1.close();


        stage.show();
    }
}
