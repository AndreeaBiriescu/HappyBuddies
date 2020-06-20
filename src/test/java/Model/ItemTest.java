package Model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class ItemTest extends ApplicationTest {
    private static String nume="testNume";
    private static int pret=10;
    private static int cantitate=70;
    private static String descriere="testDesc";
    private static String categorie="testCateg";
    private static String imagine="testImg";

    private static Item item= new Item(nume,pret,cantitate,descriere,categorie,imagine);

    @BeforeClass
    public static void beforeClass() throws Exception{
        item.setNume(nume);
        item.setPret(pret);
        item.setCantitate(cantitate);
        item.setDescriere(descriere);
        item.setCategorie(categorie);
        item.setImagine(imagine);
    }

    @Test
    public void getNume() {
        String nume1=item.getNume();
        assertEquals(nume,nume1);
    }

    @Test
    public void getPret() {
        int pret1=item.getPret();
        assertEquals(pret,pret1);
    }

    @Test
    public void getCantitate() {
        int cantitate1=item.getCantitate();
        assertEquals(cantitate,cantitate1);
    }

    @Test
    public void getDescriere() {
        String descriere1=item.getDescriere();
        assertEquals(descriere,descriere1);
    }

    @Test
    public void getCategorie() {
        String categorie1=item.getCategorie();
        assertEquals(categorie,categorie1);
    }

    @Test
    public void getImagine() {
        String imagine1=item.getImagine();
        assertEquals(imagine,imagine1);
    }

    @Test
    public void testToString(){
        String var="Item{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                ", descriere='" + descriere + '\'' +
                ", categorie='" + categorie + '\'' +
                ", imagine='" + imagine + '\'' +
                '}';
        assertEquals(var,item.toString());
    }

    public void testEquals(){
        assertEquals(true,item.equals(new Item("testNume",10, 70,"testDesc","testCateg","testImg")));
    }
}