package inleverOpdr1;

public class PivotArray {
	
	public int[] numbers;
	public int min;
	public int max;
	
	public PivotArray(int[] numbers, int min, int max){
		this.numbers = numbers;
		this.min = min;
		this.max = max;
		
	}
	
	public void swap(int a, int b){
		int tempA = this.getArrayValue(a);
		int tempB = this.getArrayValue(b);
		this.numbers[a] = tempB;
		this.numbers[b] = tempA;
	}
	
	public int getArrayValue(int x){
		return this.numbers[x];
	}

	public void setArrayValue(int x, int value){
		this.numbers[x] = value;
	}
}
