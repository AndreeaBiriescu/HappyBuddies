
package Services;


import Exceptions.EmptyFieldException;
import Exceptions.LoginFail;
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
    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");


    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }


    public static void checkEmptyField(String username, String password) throws EmptyFieldException {
        if (username.equals("") || password.equals("")) throw new EmptyFieldException();
    }


    public static void checkLoginCredentials(String username, String password) throws LoginFail {

        int sw = 0;
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                sw = 1;
                if (!Objects.equals(password, user.getPassword()))
                    throw new LoginFail();
            }
        }
        if (sw == 0) throw new LoginFail();
    }

}




