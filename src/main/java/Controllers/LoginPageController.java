package Controllers;

import Exceptions.LoginFail;
import Model.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.jws.soap.SOAPBinding;
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
    private static User user;
    @FXML
    void goToCategoryPage(ActionEvent event) throws IOException {
        try {
            UserService.checkEmptyField(usernameField.getText(),passwordField.getText());
            UserService.checkLoginCredentials(usernameField.getText(),passwordField.getText());
            user=UserService.activeUser(usernameField.getText(),passwordField.getText());
            CategoriesPageController.setUsername(usernameField.getText());

            Stage stage = (Stage) login.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CategoriesPage.fxml"));
            Stage stage1=new Stage();
            stage1.setTitle("Category Page");
            stage1.setScene(new Scene(root, 1500,900));


            stage.close();
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
        stage1.setScene(new Scene(root, 1500,900));

        stage1.show();
    }

    public static User getUser() {
        return user;
    }
}