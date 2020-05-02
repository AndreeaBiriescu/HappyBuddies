package Controllers;

import Exceptions.LoginFail;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    private Text error;
    @FXML
    void goToCategoryPage(ActionEvent event) throws IOException {
        try {
            UserService.checkLoginCredentials(usernameField.getText(),passwordField.getText());
            UserService.checkEmptyField(usernameField.getText(),passwordField.getText());
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CategoryPage.fxml"));
            Stage stage1=new Stage();
            stage1.setTitle("Category Page");


            stage1.show();
        } catch (Exception e) {
            error.setText(e.getMessage());
        }

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