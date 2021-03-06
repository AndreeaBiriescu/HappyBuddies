package Services;


import Exceptions.CouldNotWriteUsersException;
import Exceptions.EmptyFieldException;
import Exceptions.LoginFail;
import Exceptions.UsernameAlreadyExistsException;
import Model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class UserService {
    public static List<User> users;
    public static  Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");


    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("User.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }


    public static void addUser(String username, String password, String name, String email,String phone) throws Exception {

        checkEmptyField1(username,password,name,email,phone);
        checkUsernameAlreadyExist(username);
        users.add(new User(username, encodePassword(username, password),name,email,phone));
        persistUsers();
    }

    public static void checkUsernameAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())){
                throw new UsernameAlreadyExistsException();
            }

    }
    }

    private static void persistUsers() {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);

        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    public static void checkEmptyField(String username, String password) throws EmptyFieldException {
        if (username.equals("") || password.equals("")) throw new EmptyFieldException();
    }

    public static void checkEmptyField1(String username, String password,String name, String email,String phone) throws EmptyFieldException {
        if (username.equals("") || password.equals("")||name.equals("")||email.equals("")||phone.equals("")) throw new EmptyFieldException();
    }

    public static User activeUser(String username) {


        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                    return user;
            }


        }
        return null;
    }


    public static void checkLoginCredentials(String username, String password) throws LoginFail {
        String pass=encodePassword(username,password);
        int sw = 0;
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                sw = 1;
                if (!Objects.equals(pass, user.getPassword()))
                    throw new LoginFail();
            }
        }
        if (sw == 0) throw new LoginFail();

    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}




