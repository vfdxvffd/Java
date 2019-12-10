package Lab7;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
public class Exercise3 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label label1 = new Label();
        Label label2 = new Label();
        Button button = new Button("Toss");
        Image[] images = new Image[7];
        for(int i = 1; i <= 6; i++){
            images[i] = new Image("file:.\\src\\Lab7\\sources\\Die"+i+".png",120,120,false,false);
        }
        button.setOnAction(event ->{
            Random random = new Random();
            int first = random.nextInt(6)+1;
            int second = random.nextInt(6)+1;
            label1.setGraphic(new ImageView(images[first]));
            label2.setGraphic(new ImageView(images[second]));
        });
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox(label1,label2);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(hbox,button);
        vbox.setAlignment(Pos.CENTER);
        pane.setCenter(vbox);
        // 将pane加入到Scene中
        Scene scene = new Scene(pane, 300, 300);
        // 设置stage的scene，然后显示我们的stage
        stage.setScene(scene);
        stage.setTitle("Exercise_3");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
