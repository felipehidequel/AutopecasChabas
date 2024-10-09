package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import model.Funcionario;

public class LoginController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();

    try {
        if(FuncionarioController.realizarLogin(login,password)){
            System.out.println("Login bem sucedido");
        }else
        {
            System.out.println("Senha ou Login incorretos!");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        loginField.clear();
        passwordField.clear();
    }
}
