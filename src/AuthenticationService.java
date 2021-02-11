import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
    public String authorization(String login, String password) {
        Connection connection = ConnectionService.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credentials WHERE login = ? and password = ?");
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            List<User> users = new ArrayList<>();
            if (rs.next()){
                users.add(new User(rs.getString("login"),rs.getString("password"), rs.getString("nickname")));
            }
            return users.get(0).getNickname();

        } catch (SQLException e) {
            throw new RuntimeException("SWW", e);
        } finally {
            ConnectionService.close(connection);
        }
    }
}
