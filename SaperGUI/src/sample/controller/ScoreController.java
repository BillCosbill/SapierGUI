package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.model.Ranking;

public class ScoreController {
    public Button back;
    public Button add;
    public Text time;
    public TextField name;

    private Controller controller;

    public void initialize(){
        controller = new Controller();
        time.setText(controller.gameTimeString + " seconds");
    }

    public void add(ActionEvent actionEvent) throws Exception {
        Ranking ranking = new Ranking();
        ranking.addToRanking(controller.gameTimeString, name.getText());
        controller.setMenuScene();
    }

    public void menu(ActionEvent actionEvent) throws Exception {
        controller.setMenuScene();
    }
}
