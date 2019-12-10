package Lab7;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Exercise1  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button1 = new Button("sinister");
        Button button2 = new Button("dexter");
        Button button3 = new Button("medium");
        Label MyLabel = new Label();
        VBox vbox = new VBox(button1,button2,button3,MyLabel);
        vbox.setAlignment(Pos.CENTER);
        button1.setOnAction(event -> {
            MyLabel.setText("left");
        });
        button2.setOnAction(event -> {
            MyLabel.setText("right");
        });
        button3.setOnAction(event -> {
            MyLabel.setText("center");
        });

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        // 将pane加入到Scene中
        Scene scene = new Scene(pane, 300, 200);
        // 设置stage的scene，然后显示我们的stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise_1");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

