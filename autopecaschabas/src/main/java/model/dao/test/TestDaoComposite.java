package model.dao.test;

import java.util.List;

public class TestDaoComposite implements TestDaoComponent{
    List<TestDaoComponent> testes;

    TestDaoComposite(TestDaoComponent... args){
        testes = List.of(args);
    }

    @Override
    public boolean teste() {
        for(TestDaoComponent t : testes){
            if(!t.teste())
                return false;
        }
        return true;
    }
}
