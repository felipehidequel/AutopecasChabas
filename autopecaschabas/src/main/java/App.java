import model.entity.Funcionario;
import utils.Logg;
import view.TelaFuncionario;
import view.TelaGerente;
import view.TelaLogin;

public class App {
    public static void main(String[] args) {
        Funcionario user;
        user = TelaLogin.login();
        if (user != null) {
            Logg.info("Login bem sucedido!");
            if (user.getGerente()) {
                TelaGerente.menuGerente(); // substituir por menu
            } else {
                TelaFuncionario.menuFuncionario(user);
            }

        }
    }
}
