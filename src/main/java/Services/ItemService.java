package Services;


import Controllers.AdministratorPageController;
import Controllers.ShopPageController;
import Exceptions.CouldNotWriteUsersException;
import Exceptions.EmptyFieldException;
import Model.Item;
import Model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

    public class ItemService {
        private static List<Item> toys = new ArrayList<>();
        private static List<Item> accessories = new ArrayList<>();
        private static List<Item> food = new ArrayList<>();
        private static List<Item> pets = new ArrayList<>();
        private static List<Item> items;
        private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "Item.json");
        private static ShopPageController spc;
        private static AdministratorPageController apc;


        public static void loadItemFromFile() throws IOException {

            if (!Files.exists(USERS_PATH)) {
                FileUtils.copyURLToFile(ItemService.class.getClassLoader().getResource("Item.json"), USERS_PATH.toFile());
            }

            ObjectMapper objectMapper = new ObjectMapper();

            items = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<Item>>() {
            });
            divide();
        }
        public static void addItem(String nume, int pret, int cantitate, String descriere, String categorie, String imagine) throws Exception {

            checkEmptyField1(nume,pret,cantitate,descriere,categorie,imagine);

            items.add(new Item(nume, pret,cantitate,descriere,categorie,imagine));
            persistItems();
        }

        private static void persistItems() {
            try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), items);

        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }


        }

        private static void checkEmptyField1(String nume, Integer pret, Integer cantitate, String descriere, String categorie, String imagine) throws EmptyFieldException {
            if (nume.equals("") || pret==null || descriere.equals("") || categorie.equals("") || cantitate==null || imagine.equals("")) throw new EmptyFieldException();
        }

        public static void divide() {
            for (Item item : items) {
                if (item.getCategorie().equals("toy"))
                    toys.add(item);
                else if (item.getCategorie().equals("pet"))
                    pets.add(item);
                else if (item.getCategorie().equals("accessory"))
                    accessories.add(item);
                else if (item.getCategorie().equals("food"))
                {food.add(item); }
            }
        }

        public static void injectmp(ShopPageController u) {
            spc= u;
        }
        public static void injectmp(AdministratorPageController u) {
            apc= u;
        }
        public static void addItems(String cath){
            if(cath.equals("toy"))
                for(Item item : toys)
                    spc.getTilePane().getChildren().add(addItem(item));
                else
            if(cath.equals("pet"))
                for(Item item : pets)
                    spc.getTilePane().getChildren().add(addItem(item));
                else
            if(cath.equals("food"))
                for(Item item : food)
                    spc.getTilePane().getChildren().add(addItem(item));
                else
            if(cath.equals("accessory"))
                for(Item item : accessories)
                    spc.getTilePane().getChildren().add(addItem(item));

        }

        private static GridPane addItem(Item item) {
            GridPane pane=new GridPane();
            pane.setPrefWidth(1050);
            pane.setPrefHeight(370);
            pane.setStyle("-fx-background-color: white;");

            Image image=new Image(item.getImagine());
            ImageView imageView= new ImageView(image);
            imageView.setFitHeight(350);
            imageView.setFitWidth(250);


            Label descr=new Label(item.getDescriere());
            descr.setWrapText(true);
            descr.setPrefWidth(600);
            descr.setPrefHeight(250);
            descr.setFont(Font.font(24));
            descr.setAlignment(Pos.TOP_LEFT);

            String pret="";
            pret+=item.getPret();
            Label price= new Label(pret);
            price.setPrefWidth(100);
            price.setPrefHeight(70);
            price.setFont(Font.font(24));

            String cantitate="";
            cantitate+=item.getCantitate();
            Label cant= new Label(cantitate);
            cant.setPrefWidth(100);
            cant.setPrefHeight(70);
            cant.setFont(Font.font(24));

            TextField cwish=new TextField();
            cwish.setPrefWidth(100);
            cwish.setPrefHeight(70);
            cwish.setFont(Font.font(24));

            Button buy=new Button("buy");
            buy.setPrefWidth(100);
            buy.setPrefHeight(70);
            buy.setFont(Font.font(24));

            pane.setHgap(10);

            GridPane.setMargin(price, new Insets(15, 15, 5, 15));
            GridPane.setMargin(cant, new Insets(5, 15, 5, 15));
            GridPane.setMargin(cwish, new Insets(5, 15, 5, 15));
            GridPane.setMargin(buy, new Insets(5, 15, 5, 15));

            pane.setAlignment(Pos.BASELINE_LEFT);
            pane.add(imageView,0,0,1,4);
            pane.add(descr,1,0,1,4);
            pane.add(price,2,0,1,1);
            pane.add(cant,2,1,1,1);
            pane.add(cwish,2,2,1,1);
            pane.add(buy,2,3,1,1);



            return pane;
        }
        public static void addItemsAdmin(String cath){
            if(cath.equals("toy"))
                for(Item item : toys)
                    apc.getTilepane().getChildren().add(addItemAdmin(item));
            else
            if(cath.equals("pet"))
                for(Item item : pets)
                    apc.getTilepane().getChildren().add(addItemAdmin(item));
            else
            if(cath.equals("food"))
                for(Item item : food)
                    apc.getTilepane().getChildren().add(addItemAdmin(item));
            else
            if(cath.equals("accessory"))
                for(Item item : accessories)
                    apc.getTilepane().getChildren().add(addItemAdmin(item));

        }
        private static GridPane addItemAdmin(Item item) {
            GridPane pane = new GridPane();
            pane.setPrefWidth(1050);
            pane.setPrefHeight(370);
            pane.setStyle("-fx-background-color: white;");

            Image image = new Image(item.getImagine());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(350);
            imageView.setFitWidth(250);


            Label descr = new Label(item.getDescriere());
            descr.setWrapText(true);
            descr.setPrefWidth(600);
            descr.setPrefHeight(250);
            descr.setFont(Font.font(24));
            descr.setAlignment(Pos.TOP_LEFT);


            Button delete = new Button("delete");
            delete.setPrefWidth(100);
            delete.setPrefHeight(70);
            delete.setFont(Font.font(24));

            Button edit = new Button("edit");
            edit.setPrefWidth(100);
            edit.setPrefHeight(70);
            edit.setFont(Font.font(24));

            pane.setHgap(10);


            pane.setAlignment(Pos.BASELINE_LEFT);
            pane.add(imageView, 0, 0, 1, 4);
            pane.add(descr, 1, 0, 1, 4);

            pane.add(edit, 2, 1, 1, 1);
            pane.add(delete, 2, 2, 1, 1);




            return pane;

        }

    }















