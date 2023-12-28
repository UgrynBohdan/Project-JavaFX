import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Parent prog = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("prog.fxml")));
        primaryStage.setTitle("3D");
        primaryStage.setScene(new Scene(prog, 456, 348));
        primaryStage.show();

    }

}

