import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthenticationService {
    private Set<CredentialsEntry> entries;

    public AuthenticationService() {
        entries = Set.of(
                new CredentialsEntry("l1", "p1", "nickname1"),
                new CredentialsEntry("l2", "p2", "nickname2"),
                new CredentialsEntry("l3", "p3", "nickname3")
        );
    }

    public String findNicknameByLoginAndPassword(String login, String password) {
        for (CredentialsEntry entry : entries) {
            if (entry.getLogin().equals(login) && entry.getPassword().equals(password)) {
                return entry.getNickname();
            }
        }
        return null;
    }

    public String findNicknameByLoginAndPasswordWithDataBase(String login, String password) {
        System.out.println("Пытаюсь подключится");
        Connection connection = ConnectionService.connect();
        System.out.println("Вроде подключилось");
        try {
            System.out.println("Пытаюсь найти среди всех пользователей нужного");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credentials");

            ResultSet rs = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(
                        new User(
                                rs.getString("login"),
                                rs.getString("password"),
                                rs.getString("nickname")
                        )
                );
            }
            System.out.println("Пытаюсь найти этот логин: " + login + ", пытаюсь найти этот пароль: " + password);
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getLogin().equals(login) && users.get(i).getPassword().equals(password)){
                    System.out.println("Что-то нашлось");
                    System.out.println(users.get(i).getNickname());
                    return users.get(i).getNickname();
                }
            }

            System.out.println("Найти не удалось");
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("SWW", e);
        } finally {
            ConnectionService.close(connection);
        }


    }

    public static class CredentialsEntry {
        private String login;
        private String password;
        private String nickname;

        public CredentialsEntry(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }


        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        public String getNickname() {
            return nickname;
        }
    }
}
