package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import sample.model.Ranking;

import java.io.IOException;

public class RankingController {
    public ListView list;
    public Button backToMenu;

    private Controller controller;
    private Ranking ranking;

    public void initialize() throws IOException {
        controller = new Controller();
        ranking = new Ranking();


        ObservableList<String> items = FXCollections.observableArrayList (ranking.rankingList);
        list.setItems(items);
    }

    public void menu(ActionEvent actionEvent) throws Exception {
        controller.setMenuScene();
    }
}
