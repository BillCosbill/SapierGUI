package sample.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Game;
import sample.model.Main;

public class Controller {
    public Stage window;
    public static String gameTimeString;

    public Controller(){
        window = Main.window;
    }

    public void setMenuScene() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
        window.setScene(new Scene(root));
        window.show();
    }

    public void setEasyGameScene() throws Exception{
        Game game = new Game();
        window.setScene(new Scene(game.createContent("easy")));
        window.show();
    }
    public void setMediumGameScene() throws Exception{
        Game game = new Game();
        window.setScene(new Scene(game.createContent("medium")));
        window.show();
    }
    public void setHardGameScene() throws Exception{
        Game game = new Game();
        window.setScene(new Scene(game.createContent("hard")));
        window.show();
    }
    public void setExtremeGameScene() throws Exception{
        Game game = new Game();
        window.setScene(new Scene(game.createContent("extreme")));
        window.show();
    }

    public void setScoreScene(String gameTimeString) throws Exception{
        this.gameTimeString = gameTimeString;
        Parent root = FXMLLoader.load(getClass().getResource("../view/score.fxml"));
        window.setScene(new Scene(root));
        window.show();
    }

    public void setRankingScene() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/ranking.fxml"));
        window.setScene(new Scene(root));
        window.show();
    }


}
