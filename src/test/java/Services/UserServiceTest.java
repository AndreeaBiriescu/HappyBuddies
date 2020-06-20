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
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest extends ApplicationTest {
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
    public void testCopyDefaultFileIfNotExists() throws Exception {
        UserService.loadUsersFromFile();
        assertTrue(Files.exists(UserService.USERS_PATH));
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
        UserService.addUser("test", "testPass", "testName","testemail@yahoo.com","0751248953");
        assertNotNull(UserService.users);
        assertEquals(2, UserService.users.size());
    }

    @Test
    public void testAddTwoUsers() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "testName1","testeameil1@yahoo.com","0741542895");
        UserService.addUser("test2", "testPass2", "testName2","testeameil2@yahoo.com","0741451248");
        assertNotNull(UserService.users);
        assertEquals(3, UserService.users.size());
    }

    @Test(expected = UsernameAlreadyExistsException.class)
    public void testAddUserAlreadyExists() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "testName1","testeameil1@yahoo.com","0741542895");
        assertNotNull(UserService.users);
        UserService.checkUsernameAlreadyExist("test1");
    }

    @Test
    public void testAddOneUserIsPersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "testName","testemail@yahoo.com","0751248953");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "testPass1", "testName1","testeameil1@yahoo.com","0741542895");
        UserService.addUser("test2", "testPass2", "testName2","testeameil2@yahoo.com","0741451248");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test(expected = EmptyFieldException.class)
    public void checkEmptyFieldTest() throws EmptyFieldException{
        UserService.checkEmptyField("","");
    }

    @Test
    public void testPasswordEncoding() {
        assertNotEquals("testPass1", UserService.encodePassword("username1", "testPass1"));
    }

    @Test
    public void checkLoginCredentials() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "testName","testemail@yahoo.com","0751248953");
        assertNotNull(UserService.users);
        UserService.checkLoginCredentials("test","testPass");
    }

    @Test(expected = LoginFail.class)
    public void checkLoginCredentials1() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "testName","testemail@yahoo.com","0751248953");
        assertNotNull(UserService.users);
        UserService.checkLoginCredentials("test","da");
    }
}