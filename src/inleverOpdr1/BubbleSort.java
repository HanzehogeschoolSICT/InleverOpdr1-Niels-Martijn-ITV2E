package inleverOpdr1;

public class BubbleSort extends Sort{
	private int numSwaps;
	
	public BubbleSort(Main main){
		super(main);
		this.lastKey = 0;
		this.numSwaps = 0;
	}
	
	@Override
	public void step(){
		if(this.sortDone == false){
			int valueA = this.main.getArrayValue(this.lastKey);
			int valueB = this.main.getArrayValue(this.lastKey + 1);
			if(valueA > valueB){
				this.main.screen.addLine("Swapped values: " + Integer.toString(valueA)+ ", " +Integer.toString(valueB));
				this.main.setArrayValue(lastKey, valueB);
				this.main.setArrayValue(lastKey + 1, valueA);
				this.numSwaps++;
			}
			
			this.updateLastKey();
		}
	}
	
	private void updateLastKey(){
		if((this.lastKey+1) >= (this.main.getArraySize() - 1)){
			this.lastKey = 0;
			if(this.numSwaps == 0){
				this.sortDone = true;
			}else{
				this.numSwaps = 0;
			}
		}else{
			this.lastKey++;
		}
	}
	
}
