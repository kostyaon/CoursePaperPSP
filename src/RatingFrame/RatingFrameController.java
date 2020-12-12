package RatingFrame;

import Client.Client;
import Models.Rating;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RatingFrameController {
    @FXML
    private Button BClose;

    @FXML
    private Label TRating;

    @FXML
    private TableView ratingTable;

    @FXML
    private TableColumn<Rating, String> ColTheme;

    @FXML
    private TableColumn<Rating, Integer> ColLevel;

    @FXML
    private TableColumn<Rating, Float> ColRating;


    @FXML
    private void initialize(){
        DecimalFormat df = new DecimalFormat("0.00");

        TRating.setText(df.format(Client.getInstance().getSumRate()) + "%");

        //Associate columns with data
        ColTheme.setCellValueFactory(new PropertyValueFactory<Rating, String>("testTheme"));
        ColLevel.setCellValueFactory(new PropertyValueFactory<Rating, Integer>("testLevel"));
        ColRating.setCellValueFactory(new PropertyValueFactory<Rating, Float>("rating"));

        //Retrieve List<Rating> from DB
        List<Rating> ratingList = Client.getInstance().getRatingDB(Client.getInstance().getUser().getUserID());

        final ObservableList<Rating> data = FXCollections.observableArrayList(ratingList);

        ratingTable.setItems(data);
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) BClose.getScene().getWindow();
        stage.close();
    }
}
