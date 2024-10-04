package control.test;

import java.util.Arrays;
import java.util.List;

public class TestControllerComposite implements TestControllerComponent{
    List<TestControllerComponent> testes;

    public TestControllerComposite(TestControllerComponent... args){
        testes = Arrays.stream(args).toList();
    }

    @Override
    public boolean teste() {
        for (TestControllerComponent t : testes)
            if(!t.teste())
                return false;
        return true;
    }
}
