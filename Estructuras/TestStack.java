import junit.framework.TestCase;

public class TestStack extends TestCase{

    private ImpStack<Integer> stack1;
    private ImpStack<String> stack2;

    public void setupEscenario1(){
        stack1 = new ImpStack<>();
        stack1.push(3);
        stack1.push(8008135);
        stack1.push(8989);

    }

    public void setupEscenario2(){
        stack2 = new ImpStack<>();
    }

    public void testPoll1(){
        try{
            setupEscenario1();
            assertEquals(stack1.poll().intValue(), 8989);
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

    }

    // Se espera que esta pruebe falle, descomentar para verificar
//    public void testAgregarCarro2Fail(){
//        setupEscenario1();
//        assertEquals(con.getCarros().get("A").getMarca(), "B");
//        assertEquals(con.getCarros().get("B").getColor(), "C");
//        assertEquals(con.getCarros().get("C").getNumAsientos(), 4);
//    }
/*
    public void testAgregarCarroException(){
        setupEscenario2();
        try {
            con.agregarCarro("   ", "A", "A", 4, 40000);
            fail();
        } catch (CarroException exception){
            assertEquals(con.getCarros().size(),0);
        }

    }

    public void testContarCarros1(){
        setupEscenario2();
        assertEquals(con.getCarros().size(),0);
    }
    public void testContarCarros2(){
        try {
            setupEscenario1();
            assertEquals(con.getCarros().size(),3);
        } catch (CarroException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void testEliminarCarro1(){
        try{
            setupEscenario1();
            assertNotNull(con.eliminarCarro("A"));
        } catch (CarroException exception){
            System.out.println(exception.getMessage());
        }

    }
    public void testEliminarCarro2(){
        setupEscenario2();
        assertNull(con.eliminarCarro("Z"));
    }
*/
    // TODO
//    public void testCalcularValorPromedio(){
//        try {
//            setupEscenario1();
//            assertEquals(con.calcularValorPromedio(), 43733);
//        } catch (CarroException exception){
//            System.out.println(exception.getMessage());
//        }
//    }

    // TODO
//    public void testAlgunaPlacaEmpiezaConLetra(){
//        try {
//            setupEscenario1();
//            assertTrue(con.algunaPlacaEmpiezaConLetra('A'));
//        } catch (CarroException exception){
//            System.out.println(exception.getMessage());
//        }
}
