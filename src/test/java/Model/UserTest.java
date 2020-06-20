package Model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class UserTest extends ApplicationTest {
    private static String username="testUser";
    private static String password="testPass";
    private static String name="testNume";
    private static String email="testEmail@yahoo.com";
    private static String phone="0745812645";

    private static User user = new User(username, password, name, email, phone);

    @BeforeClass
    public static void beforeClass() throws Exception{
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
    }

    @Test
    public void getUsername() {
        String username1=user.getUsername();
        assertEquals(username,username1);
    }

    @Test
    public void getPassword() {
        String password1=user.getPassword();
        assertEquals(password,password1);
    }

    @Test
    public void getName() {
        String name1=user.getName();
        assertEquals(name, name1);
    }

    @Test
    public void getEmail() {
        String email1=user.getEmail();
        assertEquals(email, email1);
    }

    @Test
    public void getPhone() {
        String phone1=user.getPhone();
        assertEquals(phone, phone1);
    }

    @Test
    public void testToString() {
        String var="User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
        assertEquals(var,user.toString());
    }

    public void testEquals(){
        assertEquals(true, user.equals(new User("testUser","testPass","testNume","testEmail@yahoo.com","0745812645")));
    }
}