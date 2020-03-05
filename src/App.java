import controllers.MainController;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        try {
            mainController.run();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
