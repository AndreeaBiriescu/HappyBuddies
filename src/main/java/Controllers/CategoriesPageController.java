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
    private Button pet;
    @FXML
    private Button toy;
    @FXML
    private Button accessory;
    @FXML
    private Button food;
    private static String username;

    public static void setUsername(String username) {
        CategoriesPageController.username = username;
    }

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

        if(username.equals("admin")) {
            Parent rootad = FXMLLoader.load(getClass().getClassLoader().getResource("AdministratorPage.fxml"));
            Stage stagead = new Stage();
            stagead.setTitle("Administrator Page");
            stagead.setScene(new Scene(rootad, 1500, 900));
            Stage stage1ad = (Stage) food.getScene().getWindow();
            stage1ad.close();
        }
        else {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ShopPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Shop Page");
            stage.setScene(new Scene(root, 1500, 900));
            Stage stage1 = (Stage) food.getScene().getWindow();
            stage1.close();


            if (((Button) event.getSource()).getId().equals("toy"))
                ItemService.addItems("toy");
            else if (((Button) event.getSource()).getId().equals("food"))
                ItemService.addItems("food");
            else if (((Button) event.getSource()).getId().equals("pet"))
                ItemService.addItems("pet");
            else if (((Button) event.getSource()).getId().equals("accessory"))
                ItemService.addItems("accessory");


            stage.show();
        }
    }
}
