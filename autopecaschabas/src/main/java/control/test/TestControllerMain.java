package control.test;

public class TestControllerMain {
    public static void main(String[] args){
        TestControllerComposite t;
        t = new TestControllerComposite(new TestControllerFuncionario());
        t.teste();
    }
}
