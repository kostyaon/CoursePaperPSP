package AdminFrame.SelectFrame;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SelectFrameView extends Application {
    private double xOffset;
    private double yOffset;
    public static boolean whatFrame;//0-delete, 1-setAdmin

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SelectFrame.fxml"));

        Scene scene = new Scene(root, 245, 173);
        scene.setFill(Color.TRANSPARENT);

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = stage.getX() - mouseEvent.getScreenX();
                yOffset = stage.getY() - mouseEvent.getScreenY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX() + xOffset);
                stage.setY(mouseEvent.getScreenY() + yOffset);
            }
        });

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
