package inleverOpdr1;

public class Sort {
	public int steps;
	public Main main;
	public int lastKey;
	public boolean sortDone;
	
	public Sort(Main main){
		this.main = main;
		this.steps = 0;
		this.sortDone = false;
	}
	
	public void step(){
		//Do nothing
		System.out.println("Abstract Sort step");
	}
	
	public int getLastKey(){
		return this.lastKey;
	}
	
	public boolean checkDone(){
		return this.sortDone;
	}
	
	public void print_array(int[] array){
		for(int x = 0; x < array.length; x++){
			System.out.print(array[x]);
			System.out.print(",");
		}
		System.out.println("###############");
	}

	

	
}
