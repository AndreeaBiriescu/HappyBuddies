package Services;

import Exceptions.EmptyFieldException;
import Model.Item;
import Model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ItemServiceTest extends ApplicationTest {
        @BeforeClass
        public static void setupClass() {
            FileSystemService.APPLICATION_FOLDER = ".test-happybuddies";
            FileSystemService.initApplicationHomeDirIfNeeded();
        }

        @Before
        public void setUp() throws IOException {
            FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        }

    @Test
    public void loadItemFromFile() throws IOException{
        FileUtils.cleanDirectory(FileSystemService.getPathToFile().toFile());
        ItemService.loadItemFromFile();
        assertNotNull(ItemService.items);
        assertEquals(0, ItemService.items.size());
    }

    @Test(expected = EmptyFieldException.class)
    public void checkEmptyFieldTest() throws EmptyFieldException{
        ItemService.checkEmptyField1("",null,null,"","","");
    }

   /* @Test
    public void testAddItem() throws Exception {
        ItemService.loadItemFromFile();
        ItemService.addItem("test", 10, 90,"testDesc","https://images-na.ssl-images-amazon.com/images/I/51%2B3jl01JfL._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_.jpg");
        assertNotNull(ItemService.items);
        assertEquals(2, ItemService.items.size());
    }*/

    /*@Test
    public void testAddOneItemIsPersisted() throws Exception {
        ItemService.loadItemFromFile();
        ItemService.addItem("testNume", 12, 100,"testDesc","https://images-na.ssl-images-amazon.com/images/I/51%2B3jl01JfL._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_.jpg");
        List<Item> items = new ObjectMapper().readValue(ItemService.USERS_PATH.toFile(), new TypeReference<List<Item>>() {
        });
        assertNotNull(items);
        assertEquals(2, items.size());
    }*/}

