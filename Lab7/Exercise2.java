package Lab7;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Exercise2 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Button calculate = new Button("Calculate");
        Label charge = new Label("Meal charge:");
        TextField money = new TextField();

        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        HBox hbox = new HBox(charge,money);
        hbox.setAlignment(Pos.CENTER);
        VBox labelBox = new VBox(label1,label2,label3);
        labelBox.setAlignment(Pos.CENTER_LEFT);
        VBox vbox = new VBox(hbox,labelBox,calculate);
        vbox.setAlignment(Pos.CENTER);
        BorderPane pane = new BorderPane();
        calculate.setOnAction(event -> {
            int info = Integer.parseInt(money.getText());
            String str1 = String.format("Tip: %.2f",info*0.18);
            String str2 = String.format("Tax: %.2f",info*0.07);
            String str3 = String.format("Total: %.2f",info*1.25);
            label1.setText(str1);
            label2.setText(str2);
            label3.setText(str3);
        });
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 300, 300);        // 将pane加入到Scene中
        // 设置stage的scene，然后显示我们的stage
        stage.setScene(scene);
        stage.setTitle("Exercise_2");
        stage.show();
    }
        public static void main(String[] args) {
            launch(args);
        }
}
