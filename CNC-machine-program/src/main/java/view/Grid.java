package view;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Grid {

    private Rectangle[][] rec;

    public Pane makeGrid(int n) {

        double width = (double)500/n;
        Pane p = new Pane();
        rec = new Rectangle [n][n];

        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                rec[i][j] = new Rectangle();
                rec[i][j].setX(i * width);
                rec[i][j].setY(j * width);
                rec[i][j].setWidth(width);
                rec[i][j].setHeight(width);
                rec[i][j].setFill(null);
                rec[i][j].setStroke(Color.GREY);
                p.getChildren().add(rec[i][j]);

            }
        }

        return p;
    }

    public void setColorRed(int x, int y){
        rec[x][y].setFill(Color.RED);
    }

}
