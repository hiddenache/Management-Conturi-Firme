package screens;

import databse.DatabaseManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Optional;

public abstract class Screen {
    protected static final int STAGE_DEFAULT_HEIGHT = 600;
    protected static final int SPACING_VALUE = 10;
    protected VBox vBox;
    protected Scene scene;
    protected Stage stage;
    public static Connection sqlConnection ;

    public Screen() {
        sqlConnection = getConnection().orElse(null);
    }

    protected void createScene() {
        scene = new Scene(vBox);
    }

    protected void createStage(int stageWidth, int stageHeight) {
        stage = new Stage();
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

    }

    protected void createVBox() {
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(SPACING_VALUE);
    }

    protected abstract void createControls();

    private static Optional<Connection> getConnection() {

        DatabaseManager databaseManager = new DatabaseManager();
        return databaseManager.connect();


    }
}



