public class Main {
    public static void main(String[] args) {
        Tabla<Integer, String> a= new Tabla<>(10);
        a.insert("marcos",123);
        a.insert("lena",124);
        a.insert("mateo",125);
        a.insert("Ana",126);
        a.insert("Juan jose",127);
        a.insert("Domi",128);
        a.insert("orlando",129);
        a.insert("peir",130);
        a.insert("Scode",131);
        a.insert("Santiago",123);
        a.insert("victor",124);
        a.insert("mohana",133);
        a.insert("andres",123);
        a.insert("ariana",124);
        a.insert("scanor",125);
        a.insert("belinda",126);
        a.insert("carlos",127);
        a.insert("camilo",128);
        a.insert("carla",129);
        a.insert("capri",130);
        a.insert("dahiana",131);
        a.insert("dayana",123);
        a.insert("don",124);
        a.insert("dustin",133);
        a.insert("esteban",123);
        a.insert("eslavo",124);
        a.insert("Esquimal",125);
        a.insert("Fargan",126);
        a.insert("ferdinan",127);
        a.insert("fernanfloo",128);
        a.insert("gomez",129);
        a.insert("ganardo",130);
        a.insert("Haland",131);
        a.insert("Homer",123);
        a.insert("hilo",124);
        a.insert("irma",133);
        a.insert("indigo",123);
        a.insert("iliarte",124);
        a.insert("jambo",125);
        a.insert("jardo",126);
        a.insert("karla",127);
        a.insert("laura",128);
        a.insert("liliana",129);
        a.insert("larissa",130);
        a.insert("luna",131);
        a.insert("lacoste",123);
        a.insert("mariana",124);
        a.insert("micasa",133);
        Integer en=a.search("gomez");
        System.out.println(en==null? "No existe": en);
        a.delete("gomez");
        System.out.println("--------------");
        Integer in=a.search("gomez");
        System.out.println(in==null? "No existe": in);
    }
}