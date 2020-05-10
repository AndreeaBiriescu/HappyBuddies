package Controllers;

import Services.ItemService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddItemPageController {

    @FXML
    private TextField imageField;

    @FXML
    private TextField priceField;

    @FXML
    private Button addField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField quantityField;

    @FXML
    void addFunction(ActionEvent event) {
        try {
            ItemService.addItem(nameField.getText(), Integer.parseInt(priceField.getText()), Integer.parseInt(quantityField.getText()), descriptionField.getText(), imageField.getText());
            Stage stage = (Stage) addField.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CategoriesPage.fxml"));
            Stage stage1 = new Stage();
            stage1.setTitle("Categpry Page");
            stage1.setScene(new Scene(root, 1500, 900));

            stage1.show();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

}
