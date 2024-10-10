import model.entity.Funcionario;
import utils.Logg;
import view.TelaLogin;

public class App {
    public static void main(String[] args) {
        Funcionario user;
        user = TelaLogin.login();
        if (user != null) {
            Logg.info("Login bem sucedido!");
            if (user.getGerente()) {
                System.out.println("Menu para o gerente"); // substituir por menu
            } else {
                System.out.println("Menu para o funcionario comum"); // substituir por menu
            }

        }
    }
}
