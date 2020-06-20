package Controllers;

import Services.FileSystemService;
import Services.ItemService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javax.swing.*;

import static org.junit.Assert.*;

public class AddItemPageControllerTest extends ApplicationTest {
    public static final String TEST_IMAGE="test_image";
    public static final String TEST_PRICE="12";
    public static final String TEST_NAME="test_name";
    public static final String TEST_DESCRIPTION="test_description";
    public static final String TEST_QUANTITY="120";
    public static final Button TEST_BUTTON=new Button();
    private AddItemPageController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-happybuddies";
        FileSystemService.initApplicationHomeDirIfNeeded();
        ItemService.loadItemFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        ItemService.loadItemFromFile();

        controller= new AddItemPageController();
        controller.imageField=new TextField();
        controller.priceField=new TextField();
        controller.nameField=new TextField();
        controller.descriptionField=new TextField();
        controller.quantityField=new TextField();
        controller.addField=new Button();

        controller.imageField.setText(TEST_IMAGE);
        controller.priceField.setText(TEST_PRICE);
        controller.nameField.setText(TEST_NAME);
        controller.descriptionField.setText(TEST_DESCRIPTION);
        controller.quantityField.setText(TEST_QUANTITY);
       // controller.addField.setDefaultButton();
    }

   // @Test
   // public void addFunctionTest() {
      //  controller.addFunction(ActionEvent addField);
    //}
}