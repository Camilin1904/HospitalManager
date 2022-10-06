import junit.framework.TestCase;

public class TestHash extends TestCase{
    private Tabla<Integer, String> tabla1;
    private Tabla<String, String> tabla2;

    public void setupEscenario1(){
        tabla1 = new Tabla<>();
        tabla1.insert("juan", 1212);
        tabla1.insert("juan2", 9077);
        tabla1.insert("juan3", 124254);
    }
/*
    public void setupEscenario2(){
        stack2 = new ImpStack<>();
    }

    public void test1(){
        try{
            setupEscenario1();
            assertEquals(tabla1.search("juan").intValue(), 8989);
            assertEquals(stack1.poll().intValue(), 8008135);
            assertEquals(stack1.poll().intValue(),3);
        } catch (NoSuchFieldException exception){
            System.out.println(exception.getMessage());
            fail();
        }

    }
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
