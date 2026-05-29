import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    // init
    public String url = "jdbc:mariadb://localhost:3306/hangman";
    public String user = "root";
    public String password = "password";
    public Connection connection;  // for connection to MySQL

    public Database(){  // connect database
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveScore(String username, int score){
        String sql = "INSERT INTO players (username, score) VALUES (?, ?)";  // mysql query to save score

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            // set variables and execute
            statement.setString(1, username);
            statement.setInt(2, score);
            statement.executeUpdate();
            System.out.println("Score save for " + username + ": " + score);
        } catch (SQLException e){
            throw new RuntimeException("Failed to save for " + username, e);
        }
    }

    public void printScores(){
        String sql = """
                SELECT username, score
                FROM players
                ORDER BY score DESC
                LIMIT 10
                """;                                                  // mysql query to print scores of the top 10
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("\n------ Top 10 leaderboard ------ ");
            boolean hasRows = false;

            // print scores
            while (resultSet.next()){
                hasRows = true;
                String username = resultSet.getString("username");
                int score = resultSet.getInt("score");
                System.out.println(username + ": "+ score);
            }

            if(!hasRows){
                System.out.println("No scores saved yet");
            }
        } catch (SQLException e){
            throw new RuntimeException("Failed to read score from database", e);
        }
    }
}
