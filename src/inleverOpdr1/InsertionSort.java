package inleverOpdr1;

public class InsertionSort extends Sort{
	
	public InsertionSort(Main main){
		super(main);
		this.lastKey = 1;
	}
	
	@Override
	public void step(){
		if(this.sortDone == false){
			int lastNumber = this.main.getArrayValue(this.lastKey);
			for(int i = (this.lastKey - 1); i >=0; i--){
				int arrayValue = this.main.getArrayValue(i);
				if(arrayValue > lastNumber){
					this.main.setArrayValue(i + 1, arrayValue);
					this.main.setArrayValue(i, lastNumber);
				}
			}
			this.main.screen.addLine("Set " + Integer.toString(lastNumber) + " on its place");
			this.updateLastKey();
		}
		
	}
	
	private void updateLastKey(){
		if((this.lastKey+1) >= this.main.getArraySize()){
			this.sortDone = true;
		}else{
			this.lastKey++;
		}
	}
	
}
