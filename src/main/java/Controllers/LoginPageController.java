package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    void goToCategoryPage(ActionEvent event) throws IOException {
        //verifici credentials
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CategoryPage.fxml"));
        Stage stage1=new Stage();
        stage1.setTitle("Category Page");


        stage1.show();
    }

    @FXML
    void goToRegisterPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RegisterPage.fxml"));
        Stage stage1=new Stage();
        stage1.setTitle("Register Page");


        stage1.show();
    }

}