import junit.framework.TestCase;

public class TestHashtable extends TestCase{

    private Tabla<Integer , String> ht1;
    private Tabla<String, String> ht2;

    public void setupEscenario1(){
        ht1 = new Tabla<>();
        ht1.addLast(new NodeHash<>("Pedro", 123 ), 0);
        ht1.addLast(new NodeHash<>("Matrtha", 3155215722), 24234);
        ht1.addLast(new NodeHash<>("Roberto", 13435), 24234);
        ht1.addLast(new NodeHash<>(913, 13435), 24234);
    }
}

