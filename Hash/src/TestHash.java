import junit.framework.TestCase;

public class TestHash extends TestCase{
    private Tabla<Integer, String> tabla1;
    private Tabla<String, String> tabla2;

    public void setupEscenario1(){
        tabla1 = new Tabla<>(3);
        tabla1.insert("juan", 1212);
        tabla1.insert("juan2", 9077);
        tabla1.insert("juan3", 124254);
        tabla1.insert("juan4", 5445);
    }

    public void setupEscenario2(){
        tabla2 = new Tabla<>(3);
        tabla2.insert("juan", "jijijaja");
        tabla2.insert("juan2", "9077");
        tabla2.insert("juan3", "sfdf");
        tabla2.insert("juan4", "oo");
    }

    public void testSearch2(){
        setupEscenario2();
        assertEquals(tabla2.search("juan"), "jijijaja");
        assertEquals(tabla2.search("juan2"), "9077");
        assertEquals(tabla2.search("juan3"),"sfdf");
        assertEquals(tabla2.search("juan4"), "oo");
    }
    
    public void testAddSameKey2(){
        setupEscenario2();
        tabla2.insert("juan4", "ii");
        assertEquals(tabla2.search("juan4"), "ii");
    }

    public void testTablesize2(){
        setupEscenario2();
        assertEquals(tabla2.size(), 4);
        tabla2.insert("juan5", "42");
        assertEquals(tabla2.size(), 5);
    }

    public void testDelete2(){
        setupEscenario2();
        tabla2.delete("juan4");
        tabla2.delete("juan");
        tabla2.delete("juan3");
        assertNull(tabla2.search("juan4"));
        assertNull(tabla2.search("juan"));
        assertNull(tabla2.search("juan3"));
        assertEquals(tabla2.size(), 1);
    }
    public void testSearch1(){
        setupEscenario1();
        assertEquals(tabla1.search("juan").intValue(), 1212);
        assertEquals(tabla1.search("juan2").intValue(), 9077);
        assertEquals(tabla1.search("juan3").intValue(),124254);
        assertEquals(tabla1.search("juan4").intValue(), 5445);
    }
    
    public void testAddSameKey1(){
        setupEscenario1();
        tabla1.insert("juan4", 45234);
        assertEquals(tabla1.search("juan4").intValue(), 45234);
    }

    public void testTablesize1(){
        setupEscenario1();
        assertEquals(tabla1.size(), 4);
    }

    public void testDelete1(){
        setupEscenario1();
        tabla1.delete("juan4");
        tabla1.delete("juan");
        tabla1.delete("juan3");
        assertNull(tabla1.search("juan4"));
        assertNull(tabla1.search("juan"));
        assertNull(tabla1.search("juan3"));
        assertEquals(tabla1.size(), 1);
    }
}
