package inleverOpdr1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Screen{

	public Main main;
	private GridPane content;
	private HBox footer;
	private static int contentHeight = 200;
	private static int contentWidth = 600;
	private static int textAreaWidth = 200;
	public TextField time;
	public ComboBox algorithms;
	public TextArea textArea;
	
	public Screen(Main main){
		this.main = main;
	}

	
	public void show(Stage primaryStage){
		primaryStage.setTitle("Sort algorithms");
		
		Scene mainScene = new Scene(this.setupScene(), this.contentWidth + this.textAreaWidth, (this.contentHeight + 100));
		primaryStage.setScene(mainScene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private BorderPane setupScene(){
		BorderPane root = new BorderPane();
		HBox header = this.setHeader();
		this.content = new GridPane();
		this.content.setAlignment(Pos.BOTTOM_CENTER);
		HBox footer = this.setFooter();
		this.textArea = new TextArea();
		this.textArea.setMaxWidth(this.textAreaWidth);
		this.textArea.setEditable(false);
		root.setTop(header);
		root.setCenter(this.content);
		root.setLeft(this.textArea);
		root.setBottom(footer);
		return root;	
	}
	
	public void render(){
		content.getChildren().clear();
		int lastKey = this.main.sort.getLastKey();
		for(int x = 0; x < this.main.getArraySize(); x++){
			boolean highlight = false;
			if(x == lastKey){
				highlight = true;
			}	
			StackPane bar = this.createBar(x, highlight);
			content.add(bar, x, 1);
		}
		if(main.sort.checkDone()){
			setDone();
		}
		
	}
	
	private int getValueHeight(){
		int arraySize = this.main.getArraySize();	
		return Math.round(this.contentHeight / arraySize);
	}
	
	private int getValueWidth(){
		int arraySize = this.main.getArraySize();	
		return Math.round(this.contentWidth / arraySize);
	}
	
	private StackPane createBar(int key, boolean highlight){
		int arrayValue = this.main.getArrayValue(key);
		StackPane bar = new StackPane();
		Rectangle rect = new Rectangle();
		Label value = new Label(Integer.toString(arrayValue));
		value.setTextFill(Color.WHITE);
		rect.setWidth(this.getValueWidth());
		rect.setHeight(this.getValueHeight() * arrayValue);
		rect.setX(this.getValueWidth() * key);
		rect.setY(0);
		if(highlight){ rect.setFill(Color.GRAY); }
		bar.getChildren().addAll(rect, value);
		bar.setLayoutY(0);
		bar.setMinHeight(this.contentHeight);
		bar.setAlignment(Pos.BOTTOM_CENTER);
		return bar;
	}
	
	private HBox setHeader(){
		HBox header = new HBox();
		Label text  = new Label("Sorting Algorithms Mw/Nv");
		Font font = new Font("Calibri", 16);
		text.setFont(font);
		text.setTextFill(Color.WHITE);
		header.setAlignment(Pos.TOP_CENTER);
		header.setStyle("-fx-background-color: #42c5f4");
		header.getChildren().add(text);
		return header;
	}
	
	private HBox setFooter(){
		HBox footer = new HBox();
		footer.setAlignment(Pos.BOTTOM_CENTER);
		footer.setStyle("-fx-background-color: #c9c9c9;");
		footer.setPadding(new Insets(10,10,10,10));
		footer.setSpacing(5);
		Button start = this.getStartButton();
		Button reset = this.getResetButton();
		Button step = this.getStepButton();
		this.time = new TextField("100");
		this.algorithms = this.getAlgorithmBox();
		footer.getChildren().addAll(start, reset, step, time, algorithms);
		return footer;
	}
	
	private Button getStartButton(){
		Button start = new Button("Start");
		start.setOnAction(new StartHandlerClass());
		return start;
	}
	
	private Button getResetButton(){
		Button reset = new Button("Reset");
		reset.setOnAction(new ResetHandlerClass());
		return reset;
	}
	
	private Button getStepButton(){
		Button step = new Button("Step");
		step.setOnAction(new StepHandlerClass());
		return step;
	}
	
	private ComboBox getAlgorithmBox(){
		ObservableList<String> options = FXCollections.observableArrayList("BubbleSort", "InsertionSort", "QuickSort");
		ComboBox algorithms = new ComboBox(options);
		algorithms.getSelectionModel().selectFirst();
		algorithms.setOnAction(new ResetHandlerClass());
		return algorithms;
	}
	
	public void setDone(){
		StackPane done = new StackPane();
		Rectangle rect = new Rectangle();
		Label value = new Label("Done!");
		value.setTextFill(Color.WHITE);
		rect.setFill(Color.RED);
		rect.setWidth(50);
		rect.setHeight(50);
		rect.setX(5);
		rect.setY(5);
		done.setMinHeight(this.contentHeight);
		done.getChildren().addAll(rect, value);
		content.add(done, 0,1);
		this.addLine("DONE!");
	}
	
	public void reset(){
		this.main.reset(this.algorithms.getSelectionModel().getSelectedItem().toString());
		this.render();
	}
	
	public void addLine(String text){
		this.textArea.appendText(text + "\n");
	}
	
	class StartHandlerClass implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			main.startSort(Integer.parseInt(time.getText()));
		}
	}
	
	class ResetHandlerClass implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			reset();
		}
	}
	
	class StepHandlerClass implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			main.sort.step();
			render();
			
		}
	}
	
}
