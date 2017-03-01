package inleverOpdr1;

import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application implements Runnable{
	public static Screen screen;
	public Sort sort;
	private int[] mixedArray;
	//private int[] mixedArray = {1,2,3,4,5,6,7,8,9,10,12,11};
	private static int arraySize = 12;
	private Thread sortThread;
	private int sleepTime;
	private boolean shouldRun;
	
	
	public static void main(String[] args){
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage){
		
		this.screen = new Screen(this);
		this.screen.show(primaryStage);
		this.sort = new BubbleSort(this);
		this.setup();
	}
	
	private void setup(){
		//Do stuff	
		this.reset("BubbleSort");
		this.screen.render();
	}
	
	public int getArraySize(){
		return this.arraySize;
	}
	
	public int getArrayValue(int key){
		return this.mixedArray[key];
	}
	
	public void setArrayValue(int key, int value){
		this.mixedArray[key] = value;
	}
	
	private void randomizeArray(){
		this.mixedArray = new int[this.arraySize];
		for(int x = 0; x < this.arraySize; x++){
			this.mixedArray[x] = ThreadLocalRandom.current().nextInt(1, this.arraySize + 1);
		}
	}
	
	public void reset(String algorithm){
		this.randomizeArray();
		if(algorithm == "BubbleSort"){
			this.sort = new BubbleSort(this);
		}else if(algorithm == "InsertionSort"){
			this.sort = new InsertionSort(this);
		}else if(algorithm == "QuickSort"){
			this.sort = new QuickSort(this);
		}
		this.screen.textArea.clear();
	}
	
	public void startSort(int time){
		if(this.shouldRun){
			this.shouldRun = false;
			System.out.println("Interrupted");
		}else{
			this.sortThread = new Thread(this);
			this.sortThread.start();
			this.setSleepTime(time);
		}
	}
	
	public void run(){
		this.shouldRun = true;
		while(this.shouldRun){
			this.sort.step();	
			Platform.runLater(new Runnable() {                          
                @Override
                public void run() {
                    try{
                    	screen.render();
                    }finally{
                       
                    }
                }
            });
			try{
				Thread.sleep(this.sleepTime);
			}catch(Exception E){
				System.out.println(E);
			}
			if(this.sort.checkDone()){
				this.shouldRun = false;
			}
		}
	}
	
	public void setSleepTime(int time){
		this.sleepTime = time;
	}
	
	public int[] getArray(){
		return this.mixedArray;
	}
}
