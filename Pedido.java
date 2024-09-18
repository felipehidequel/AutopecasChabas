import java.util.Calendar;

public class Pedido {
    private int idPedido;
    private Calendar data;
    private String status;
    private String funcionarioAtendente;

    Pedido(int idPedido, String funcionarioAtendente){
        this.idPedido = idPedido;
        this.funcionarioAtendente = funcionarioAtendente;
        status = "Em Aguardo";
        data = Calendar.getInstance();
    }
}
