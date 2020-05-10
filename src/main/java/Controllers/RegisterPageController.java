package Controllers;

import Exceptions.EmptyFieldException;
import Exceptions.UsernameAlreadyExistsException;
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

import java.io.IOException;

public class RegisterPageController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button register;

    @FXML
    private Text errortext;

    @FXML
    void onRegister(ActionEvent event) throws IOException {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(),nameField.getText(),emailField.getText(),phoneField.getText());
            Stage stage = (Stage) register.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml"));
            Stage stage1 = new Stage();
            stage1.setTitle("Login Page");
            stage1.setScene(new Scene(root, 1500, 900));

            stage1.show();
        } catch (Exception e) {
            errortext.setText(e.getMessage());
        }
    }


}
