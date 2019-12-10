package Lab7;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
public class Exercise4 extends Application {
    double total = 0.00;
    @Override
    public void start(Stage stage) throws Exception {
        Label[] labels = new Label[3];
        for(int i = 0; i < 3; i++){
            labels[i] = new Label();
            labels[i].setGraphic(new ImageView(new Image("file:.\\src\\Lab7\\sources\\fruits(0).png")));
        }
        HBox hbox1 = new HBox(labels);
        hbox1.setAlignment(Pos.TOP_CENTER);
        Label label1 = new Label("Amount Inserted:$ ");
        TextField money = new TextField();
        HBox hbox2 = new HBox(label1,money);
        hbox2.setAlignment(Pos.CENTER);
        Label blank1 = new Label();
        Label blank2 = new Label();
        Label blank3 = new Label();
        Label label2 = new Label("Amount Won This Spin:$ 0.00");
        Label label3 = new Label("Total Amount Won:$ 0.00");
        Label label4 = new Label("Insert an amount to play.");
        Button button = new Button("Spin");
        button.setOnAction(event ->{
            double Principal = Double.parseDouble(money.getText());   //本金
            int temp1 = new Random().nextInt(10)+1;
            int temp2 = new Random().nextInt(10)+1;
            int temp3 = new Random().nextInt(10)+1;
            labels[0].setGraphic(new ImageView(new Image("file:.\\src\\Lab7\\sources\\fruits("+temp1+").png")));
            labels[1].setGraphic(new ImageView(new Image("file:.\\src\\Lab7\\sources\\fruits("+temp2+").png")));
            labels[2].setGraphic(new ImageView(new Image("file:.\\src\\Lab7\\sources\\fruits("+temp3+").png")));
            if(temp1 == temp2 && temp1 == temp3){       //人品爆发
                total += Principal*3;
                label2.setText(String.format("Amount Won This Spin:$ %.2f",Principal*3));
                label3.setText(String.format("Total Amount Won:$ %.2f",total));
                label4.setText(String.format("Sweet! TRIPLE WIN*3!!!"));
            }else if(temp1 != temp2 && temp2 != temp3 && temp1 != temp3){
                label2.setText(String.format("Amount Won This Spin:$ %.2f",Principal*0));
                label4.setText("No Luck.Play again!");
            }else{
                total += Principal*2;
                label2.setText(String.format("Amount Won This Spin:$ %.2f",Principal*2));
                label3.setText(String.format("Total Amount Won:$ %.2f",total));
                label4.setText(String.format("Sweet! DOUBLE WIN*2!!"));
            }
        });
        VBox vbox = new VBox(hbox1,blank1,hbox2,blank2,label2,label3,blank3,button,label4);
        vbox.setAlignment(Pos.TOP_CENTER);
        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane,400,270);
        stage.setScene(scene);
        stage.setTitle("Exercise_4");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
