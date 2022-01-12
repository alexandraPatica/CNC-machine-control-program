package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.*;

public class Grid {

    private Rectangle[][] rec;
    private Pane gridPane;

    public Grid (int n){
        double width = (double)500/n;
        gridPane = new Pane();
        rec = new Rectangle [n][n];

        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                rec[i][j] = new Rectangle();
                rec[i][j].setX(i * width);
                rec[i][j].setY(j * width);
                rec[i][j].setWidth(width);
                rec[i][j].setHeight(width);
                rec[i][j].setFill(Color.WHITE);
                rec[i][j].setStroke(Color.GREY);
                gridPane.getChildren().add(rec[i][j]);

            }
        }
    }

    public void setColorRed(int x, int y){
        rec[x][y].setFill(Color.RED);
    }

    public Rectangle[][] getGrid(){
        return rec;
    }

    public Pane getGridPane(){
        return gridPane;
    }

}
