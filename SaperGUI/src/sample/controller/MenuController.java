package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import sample.model.Game;

public class MenuController {
    public Button start;
    public Button exit;
    public Button ranking;
    public Button ok;

    private Controller controller;
    private Game game;

    public void initialize(){
        controller = new Controller();
        game = new Game();
    }



    public void exit(ActionEvent actionEvent) {
        System.exit(1);
    }

    public void ranking(ActionEvent actionEvent) throws Exception{
        controller.setRankingScene();
    }

    public void easy(ActionEvent actionEvent) throws Exception {
        controller.setEasyGameScene();
    }

    public void medium(ActionEvent actionEvent) throws Exception {
        controller.setMediumGameScene();
    }

    public void hard(ActionEvent actionEvent) throws Exception {
        controller.setHardGameScene();
    }

    public void extreme(ActionEvent actionEvent) throws Exception {
        controller.setExtremeGameScene();
    }
}
