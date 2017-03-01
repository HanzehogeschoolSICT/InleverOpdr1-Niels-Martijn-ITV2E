package inleverOpdr1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends Sort{
	
	private ArrayList<PivotArray> arrays;
	
	public QuickSort(Main main){
		super(main);
		this.lastKey = 0;
		this.arrays = new ArrayList<PivotArray>();
		PivotArray firstArray = new PivotArray(this.main.getArray(), 0, this.main.getArraySize());
		this.arrays.add(firstArray);
	}
	
	public void step(){
		if(this.sortDone == false){
			PivotArray currentArray = this.arrays.get(this.arrays.size()-1);
			this.main.screen.addLine("Working on array with indexes: " + Integer.toString(currentArray.min) + " - " + Integer.toString(currentArray.max));
			int pivot = (ThreadLocalRandom.current().nextInt(0, currentArray.numbers.length - 1)) % currentArray.numbers.length;
			this.main.screen.addLine("The pivot is: " + Integer.toString(currentArray.numbers[pivot]));
			boolean run = true;
			while(run){
				int forward = this.searchForward(currentArray, pivot);
				int reverse = this.searchReverse(currentArray, pivot);
				if(forward == pivot && reverse == pivot){
					run = false;
				}else{
					if(forward == pivot){
						pivot = reverse;
					}else if(reverse == pivot){
						pivot = forward;
					}
					this.swap(currentArray, forward, reverse);
				}			
			}	
			this.arrays.remove(this.arrays.size()-1);
			this.createPivots(currentArray, pivot);
			this.updateLastKey(pivot + currentArray.min);
		}
	}
	
	private int searchForward(PivotArray currentArray, int pivot){
		int pivotValue = currentArray.numbers[pivot];
		for(int x = 0; x < pivot; x++){
			int xValue = currentArray.numbers[x];
			if(xValue > pivotValue){
				return x;
			}
		}
		return pivot;
	}
	
	private int searchReverse(PivotArray currentArray, int pivot){
		int pivotValue = currentArray.numbers[pivot];
		for(int x = currentArray.numbers.length - 1; x > pivot; x--){
			int xValue = currentArray.numbers[x];
			if(xValue < pivotValue){
				return x;
			}
		}
		return pivot;
	}
	
	private void swap(PivotArray currentArray, int a, int b){
		int ValueA = currentArray.numbers[a];
		int ValueB = currentArray.numbers[b];
		this.main.setArrayValue(a + currentArray.min, ValueB);
		this.main.setArrayValue(b + currentArray.min, ValueA);
		currentArray.setArrayValue(a, ValueB);
		currentArray.setArrayValue(b, ValueA);
		this.main.screen.addLine("Swapped " + Integer.toString(ValueA) + ", " + Integer.toString(ValueB));
	}
	
	private void createPivots(PivotArray currentArray, int pivot){
		if((currentArray.numbers.length-1) - pivot > 1){
			//CREATE AFTER PIVOT
			int newMin = pivot + currentArray.min + 1;
			int newMax = currentArray.max;
			int[] newArray = Arrays.copyOfRange(this.main.getArray(), newMin, newMax);
			PivotArray newPivot = new PivotArray(newArray, newMin, newMax);
			this.arrays.add(newPivot);
		}
		
		if(pivot > 1){
			//CREATE BEFORE PIVOT
			int newMin = currentArray.min;
			int newMax = currentArray.min + pivot;
			int[] newArray2 = Arrays.copyOfRange(this.main.getArray(), newMin, newMax);
			PivotArray newPivot2 = new PivotArray(newArray2, newMin, newMax);
			this.arrays.add(newPivot2);
		}
	}
	
	private void updateLastKey(int pivot){
		this.lastKey = pivot;
		if(this.arrays.size() == 0){
			this.sortDone = true;
		}
	}

}
