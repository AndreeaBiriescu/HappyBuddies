package Model;


import java.util.Objects;

public class Item {
    private String nume;
    private int pret;
    private int cantitate;
    private String descriere;
    private String categorie;
    private String imagine;
    public Item(){

    }
    public Item(String nume, int pret, int cantitate, String descriere, String categorie, String imagine) {
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
        this.descriere = descriere;
        this.categorie = categorie;
        this.imagine = imagine;
    }

    public String getNume() {
        return nume;
    }

    public int getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getImagine() {
        return imagine;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(nume, item.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume);
    }

    @Override
    public String toString() {
        return "Item{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                ", descriere='" + descriere + '\'' +
                ", categorie='" + categorie + '\'' +
                ", imagine='" + imagine + '\'' +
                '}';
    }
}
