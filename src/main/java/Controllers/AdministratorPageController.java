package Controllers;

import Services.ItemService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.TilePane;


public class AdministratorPageController {

    @FXML
    private TilePane tilepane;
     @FXML
        private Button additem;

        @FXML
        private Button Back;
    @FXML
    private void initialize(){
        ItemService.injectmp(this);
    }
        @FXML
        void gotocategoriespage(ActionEvent event) {

        }

        @FXML
        void gotoadditempage(ActionEvent event) {

        }


    public TilePane getTilepane() {
        return tilepane;
    }
}




