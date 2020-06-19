package Services;

import Exceptions.EmptyFieldException;
import Exceptions.LoginFail;
import Exceptions.UsernameAlreadyExistsException;
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

public class UserServiceTest extends ApplicationTest {
    @BeforeClass
    public static void setupClass() {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getPathToFile().toFile());
    }


    @Test
    public void testLoadUsersFromFile() throws Exception {
        UserService.loadUsersFromFile();
        assertNotNull(UserService.users);
        assertEquals(1, UserService.users.size());
    }

    @Test
    public void testAddOneUser() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "name", "email@yahoo.com","0756218953");
        assertNotNull(UserService.users);
        assertEquals(2, UserService.users.size());
    }

    @Test
    public void testAddTwoUsers() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "name1", "email1@yahoo.com","0756523951");
        UserService.addUser("test2", "testPass2", "name2", "email2@yahoo.com","0765215985");
        assertNotNull(UserService.users);
        assertEquals(3, UserService.users.size());
    }

    @Test(expected = UsernameAlreadyExistsException.class)
    public void testAddUserAlreadyExists() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "name1", "email1@yahoo.com","0756523951");
        assertNotNull(UserService.users);
        UserService.checkUsernameAlreadyExist("test1");
    }

    @Test
    public void testAddOneUserIsPersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "name", "email@yahoo.com","0756218953");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "name1", "email1@yahoo.com","0756523951");
        UserService.addUser("test2", "testPass2", "name2", "email2@yahoo.com","0765215985");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    public void testPasswordEncoding() {
        assertNotEquals("testPass1", UserService.encodePassword("username1", "testPass1"));

    }

    @Test(expected = EmptyFieldException.class)
    public void checkEmptyFieldTest() throws EmptyFieldException{
        UserService.checkEmptyField("","");
    }

    @Test(expected = EmptyFieldException.class)
    public void checkEmptyFieldTest1() throws EmptyFieldException{
        UserService.checkEmptyField1("","","","","");
    }


    @Test(expected = LoginFail.class)
    public void checkLoginCredentialsTest() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "name1", "email1@yahoo.com","0756523951");
        UserService.checkLoginCredentials("test2","testPass1");
    }
}