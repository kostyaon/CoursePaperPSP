package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignUpView extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Fxml/SignUpFrame.fxml"));
        Scene scene = new Scene(root, 325, 452);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Hello World");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
