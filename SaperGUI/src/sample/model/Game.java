package sample.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class Game {
    private int TILE_SIZE;
    private final int W = 800;
    private final int H = 600;

    long starTime;
    long endTime;
    int gameTime;
    String gameTimeString;

    private String level;

    private int X_TILEX;
    private int Y_TILEX;

    private int bombs;

    private int mines = 0;
    private int tiles;

    private int[][] gridHelp;

    private Tile[][] grid;
    private Stage window = Main.window;
    private Controller controller = new Controller();

    public Game() {
        this.starTime = currentTimeMillis();
    }

    public Parent createContent(String level){
        this.level = level;
        Pane root = new Pane();
        root.setStyle("-fx-background-color: #4B4237");
        root.setPrefSize(W,H+100);
        Button back = new Button("Back to Menu");
        back.setLayoutY(650);
        back.setLayoutX(350);
        back.setPrefWidth(100);
        back.setStyle("-fx-background-color: #EDE7D9");

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    controller.setMenuScene();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        root.getChildren().add(back);

        if(level.equals("easy")){
            TILE_SIZE=160;
            bombs=4;
        }
        if(level.equals("medium")){
            TILE_SIZE=100;
            bombs=10;
        }
        if(level.equals("hard")){
            TILE_SIZE=80;
            bombs=20;
        }
        if(level.equals("extreme")){
            TILE_SIZE=40;
            bombs=40;
        }

        X_TILEX = W / TILE_SIZE;
        Y_TILEX = H / TILE_SIZE;
        tiles = X_TILEX * Y_TILEX;
        gridHelp = new int[X_TILEX][Y_TILEX];
        grid = new Tile[X_TILEX][Y_TILEX];

        for(int i=0; i<Y_TILEX;i++){
            for(int j=0; j<X_TILEX; j++){
                gridHelp[j][i] = 0;
            }
        }

            while(bombs != mines){
                int x = new Random().nextInt(X_TILEX);
                int y = new Random().nextInt(Y_TILEX);
                if(gridHelp[x][y]==0){
                    gridHelp[x][y]=1;
                    Tile tile = new Tile(x,y,true);
                    mines++;
                    grid[x][y] = tile;
                    root.getChildren().add(tile);
                }
            }


        for(int i=0; i<Y_TILEX; i++){
            for(int j=0; j<X_TILEX; j++){
                if(gridHelp[j][i]!=1){
                    Tile tile = new Tile(j,i, false);

                    grid[j][i] = tile;
                    root.getChildren().add(tile);
                }
            }
        }

        for(int i=0; i<Y_TILEX; i++){
            for(int j=0; j<X_TILEX; j++){
                Tile tile = grid[j][i];

                if(tile.hasBomb)
                    continue;

                long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                if(bombs > 0)
                    tile.text.setText(String.valueOf(bombs));
            }
        }

        return root;
    }


    private List<Tile> getNeighbors(Tile tile){
        List<Tile> neighbors = new ArrayList<>();

        int[] points = new int[]{
                -1,-1, -1,0, -1,1, 0,-1, 0,1, 1,-1, 1,0, 1,1
        };

        for(int i=0;i<points.length; i++){
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (newX >= 0 && newX < X_TILEX && newY >= 0 && newY < Y_TILEX){
                neighbors.add(grid[newX][newY]);
            }
        }

        return neighbors;
    }


    private class Tile extends StackPane {
        private int x,y;
        private boolean hasBomb;
        private boolean isOpen = false;

        private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
        private Text text = new Text();

        public Tile(int x, int y, boolean hasBomb){
            this.x = x;
            this.y = y;
            this.hasBomb = hasBomb;

            border.setStroke(Color.LIGHTGRAY);

            text.setFont(Font.font(20));
            text.setText(hasBomb ? "X" : "");
            text.setVisible(false);

            getChildren().addAll(border, text);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

            setOnMouseClicked(e -> open());
        }

        public void open(){
            if (isOpen)
                return;

            if (hasBomb){
                System.out.println("Game Over!");
                starTime = currentTimeMillis();
                tiles = X_TILEX * Y_TILEX;
                mines = 0;
                window.setScene(new Scene(createContent(level)));
                return;
            }

            isOpen = true;
            tiles--;
            text.setVisible(true);
            border.setFill(null);

            if(tiles == mines){
                endTime = currentTimeMillis();
                gameTime = (int)(endTime - starTime)/1000;
                gameTimeString = String.valueOf(gameTime);
                System.out.println("WYGRANAAA!");
                System.out.println("Twój czas: "+ gameTime +" sekund");

                    try {
                        controller.setScoreScene(gameTimeString);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;

            }

            if (text.getText().isEmpty()){
                getNeighbors(this).forEach(Tile::open);
            }
        }
    }
}
