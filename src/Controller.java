import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button multimedia_b, logotype_b, cylinder_b, sphere_b, cube_b;

    @FXML
    void initialize() {
        cube_b.setOnAction(event -> {
            // Створюємо новий об'єкт
            Box figure = new Box(200, 200, 200);
            figure.setTranslateX(150);
            figure.setTranslateY(150);
            figure.setTranslateZ(0);

            // Створюємо матеріал для куба і встановлюємо колір
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.RED); // Встановлюємо колір
            material.setSpecularColor(Color.PINK);
            figure.setMaterial(material);

            openNewWindow(figure);
        });

        sphere_b.setOnAction(event -> {
            // Створюємо новий об'єкт
            Sphere figure = new Sphere(200);
            figure.setTranslateX(150);
            figure.setTranslateY(150);
            figure.setTranslateZ(0);

            // Створюємо матеріал для куба і встановлюємо колір
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.DARKBLUE); // Встановлюємо колір
            material.setSpecularColor(Color.BLUE);
            figure.setMaterial(material);

            openNewWindow(figure);
        });

        cylinder_b.setOnAction(event -> {
            // Створюємо новий об'єкт
            Cylinder figure = new Cylinder(100, 200);
            figure.setTranslateX(150);
            figure.setTranslateY(150);
            figure.setTranslateZ(0);

            // Створюємо матеріал для куба і встановлюємо колір
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.DARKCYAN); // Встановлюємо колір
            material.setSpecularColor(Color.CYAN);
            figure.setMaterial(material);

            openNewWindow(figure);
        });

        logotype_b.setOnAction(event -> {
            new Thread(() -> {
                // Виконуємо створення та показ нового вікна в потоці JavaFX
                Platform.runLater(() -> {
                    // Створення об'єкта ImageView для зображення логотипу
                    ImageView logoImageView = new ImageView(new Image("media/logot.png"));

                    // Встановлення розмірів логотипу (опціонально)
                    logoImageView.setFitWidth(300);
                    logoImageView.setFitHeight(200);

                    // Створення макету та додавання ImageView
                    StackPane root = new StackPane();
                    root.getChildren().add(logoImageView);

                    // Створення сцени
                    Scene scene = new Scene(root, 300, 200);

                    // Створюємо новий Stage (вікно)
                    Stage newStage = new Stage();
                    newStage.setTitle("3D Figures Window");
                    newStage.setScene(scene);
                    newStage.show();
                });
            }).start();
        });

        multimedia_b.setOnAction(event -> {
            new Thread(() -> {
                // Виконуємо створення та показ нового вікна в потоці JavaFX
                Platform.runLater(() -> {
                    // Створюємо об'єкт Media
                    Media videoMedia = new Media(new File("C:\\Users\\ACER\\Desktop\\VID_20201108_153910.mp4").toURI().toString());

                    // Створюємо об'єкт MediaPlayer
                    MediaPlayer videoPlayer = new MediaPlayer(videoMedia);

                    // Створюємо об'єкт MediaView
                    MediaView videoView = new MediaView(videoPlayer);

                    // Створюємо макет та додаємо MediaView
                    StackPane root = new StackPane();
                    root.getChildren().addAll(videoView);

                    // Створюємо сцену
                    Scene scene = new Scene(root, 1000, 700);

                    // Створюємо новий Stage (вікно)
                    Stage newStage = new Stage();
                    newStage.setTitle("3D Figures Window");
                    newStage.setScene(scene);
                    newStage.show();

                    // Відтворення відеофайлу
                    videoPlayer.play();
                });
            }).start();
        });
    }

    private void openNewWindow(Shape3D figure) {
        // Створюємо новий потік для відкриття нового вікна
        new Thread(() -> {
            // Виконуємо створення та показ нового вікна в потоці JavaFX
            Platform.runLater(() -> {
                // Створюємо анімацію обертання
                RotateTransition rotateTransition = new RotateTransition();
                rotateTransition.setAxis(new Point3D(1, 1, 0)); // Встановлюємо ось обертання (Y_AXIS)
                rotateTransition.setByAngle(360); // Кут обертання (360 градусів)
                rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Безкінечна анімація
                rotateTransition.setDuration(Duration.seconds(4)); // Тривалість анімації

                // Запускаємо анімацію для куба
                rotateTransition.setNode(figure);
                rotateTransition.play();

                // Створюємо новий об'єкт групи та додаємо куб до групи
                Group group = new Group(figure);

                // Створюємо новий Scene та додаємо групу
                Scene scene = new Scene(group, 300, 300, Color.BLACK);

                // Налаштовуємо камеру для перспективного виду
                PerspectiveCamera camera = new PerspectiveCamera(false);
                camera.setTranslateZ(-300);
                scene.setCamera(camera);

                // Створюємо новий Stage (вікно)
                Stage newStage = new Stage();
                newStage.setTitle("3D Figures Window");
                newStage.setScene(scene);
                newStage.show();
            });
        }).start();
    }
}