package Services;


import Model.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

    public class ItemService {
        private static List<Item> toys=new ArrayList<>();
        private static List<Item> accessories=new ArrayList<>();
        private static List<Item> food=new ArrayList<>();
        private static List<Item> pets=new ArrayList<>();
        private static List<Item> items;
        private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "Item.json");



        public static void loadItemFromFile() throws IOException {

            if (!Files.exists(USERS_PATH)) {
                FileUtils.copyURLToFile(ItemService.class.getClassLoader().getResource("Item.json"), USERS_PATH.toFile());
            }

            ObjectMapper objectMapper = new ObjectMapper();

            items = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<Item>>() {
            });
        }
     public static void divide() {
         for (Item item : items) {
             if(item.getCategorie().equals("toy"))
                 toys.add(item);
             else
                 if(item.getCategorie().equals("pet"))
                         pets.add(item);
               else
                   if(item.getCategorie().equals("accessory"))
                       accessories.add(item);
                   else
                       if(item.getCategorie().equals("food"))
                               food.add(item);
         }
     }














    }

}
