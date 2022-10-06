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
        //stack2 = new ImpStack<>();
    }

    public void testSearch1(){
            setupEscenario1();
            assertEquals(tabla1.search("juan").intValue(), 1212);
            assertEquals(tabla1.search("juan2").intValue(), 9077);
            assertEquals(tabla1.search("juan3").intValue(),124254);
            assertEquals(tabla1.search("juan4").intValue(), 5445);
    }
    
    public void testAddSameKey(){
        setupEscenario1();
        tabla1.insert("juan4", 45234);
        assertEquals(tabla1.search("juan4").intValue(), 45234);
    }

    public void testTablesize(){
        setupEscenario1();
        assertEquals(tabla1.size(), 4);
    }

    public void testDelete(){
        setupEscenario1();
        tabla1.delete("juan4");
        assertEquals(tabla1.search("juan4").intValue(), 5445);
    }
    

    /*
    public void testExeption1(){
        try{
            setupEscenario1();
            assertEquals(stack1.poll().intValue(), 8989);
            assertEquals(stack1.poll().intValue(), 8008135);
            assertEquals(stack1.poll().intValue(),3);
            assertEquals(stack1.poll().intValue(), 0);
            fail();
        } catch (NoSuchFieldException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void testClear1(){
        try{
            setupEscenario1();
            stack1.clear();
            assertEquals(stack1.poll().intValue(), 3);
            fail();
        } catch (NoSuchFieldException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void testSize1(){
        try{
            setupEscenario1();
            assertEquals(stack1.size(),3);
            assertEquals(stack1.poll().intValue(), 8989);
            assertEquals(stack1.size(),2);
            assertEquals(stack1.poll().intValue(), 8008135);
            assertEquals(stack1.size(),1);
            assertEquals(stack1.poll().intValue(),3);
            assertEquals(stack1.size(),0);
        } catch (NoSuchFieldException exception){
            System.out.println(exception.getMessage());
        }

    }*/
}
