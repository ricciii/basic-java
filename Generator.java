import java.util.Scanner;
import java.util.Random;

public class Generator{
	private String[][] stringArray;
	private int row;
	private int column;

	public static void main(String[] args){
		Generator gen = new Generator();
		gen.start();
	}

	public void start(){
		
		try{
			int num;
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the number of rows: ");
			num = scanner.nextInt();

			if(num<=0) {
				throw new Exception();
			} else {
				this.row = num;
			}

			System.out.print("Enter the number of columns: ");
			num = scanner.nextInt();

			if(num<=0) {
				throw new Exception();
			} else {
				this.column = num;
			}

		} catch(Exception e){
			System.out.println("Input should be an integer and greater than zero!");
			start();
		}
		create();
		menu();	
	}

	public void menu() {
		System.out.println("What do you want to do?");
		System.out.println("1=Print 2=Search 3=Edit 4=Reset 5=Exit");
		
		try{
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();

	        switch(choice){
	        case 1: print();
	        		menu();
	        		break;
	        case 2: search();
	        		menu(); 
	        		break;
	        case 3: edit(); 
	        		menu();
	        		break;
	        case 4:	reset();
	        		break;
	        case 5: exit(); 
	        		break;
	        default: 
		        	System.out.println("Not in the choices. Please try again.");
		        	menu();
			}
		}
		catch(Exception e){
			System.out.println("Not in the choices. Please try again.");
			menu();
		}
	}

	public void print(){
		int i, j;
		for(i=0;i<this.row;i++){
			for(j=0;j<this.column;j++){
				System.out.print("|  "+this.stringArray[i][j]+"  ");
			}
			System.out.println("|");
		}
	}

	public void edit(){

		try{
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the row index: ");
			int newRow = scanner.nextInt();

			System.out.print("Enter the column index: ");
			int newColumn = scanner.nextInt();

			String indexValue = this.stringArray[newRow][newColumn];

			System.out.print("Replace \""+indexValue+"\" with: ");
			scanner = new Scanner(System.in);
			String value = scanner.nextLine();
			stringArray[newColumn][newRow] = value;
		}catch(IndexOutOfBoundsException e1){
			System.out.format("No such index, max row index=%d, max column index=%d\n", this.row-1, this.column-1);
			//System.out.println("Please enter again:");
		}
		catch(Exception e2){
			System.out.println("Input must be a whole number. Please try again:");
			edit();
		}			
	}

	public void reset(){
		start();
	}

	public void search(){
		System.out.print("What do you want to search: ");
		Scanner scanner = new Scanner(System.in);
		String pattern = scanner.nextLine();
		int patternLength = pattern.length();
		int i,j,counter=0,total=0;
		for(i=0;i<this.row;i++){
			for(j=0;j<this.column;j++){
				String string = new String();
				string = this.stringArray[i][j];
				int length = string.length();
					int a;
					for(a=0; a<=length-patternLength;a++){
						 int b;
						 for(b=0; b<patternLength; b++){
						 	if(string.charAt(a+b)!=pattern.charAt(b)){
						 		break;
						 	}
						 } 
						 if(b==patternLength){
						 	counter++;
						 	b=0;
						 }
					}
					if(counter>0){
						System.out.println("Found at: Index["+i+"]["+j+"]");
						System.out.println("Number of occurences in this index:"+counter);
						total+=counter;
						counter=0;
					}
			}
		}
		System.out.println("Total # of \""+pattern+ "\" Occurences: " + total);
	}
	
	public static void exit(){
		System.exit(0);
	}

	private void create() {
		this.stringArray = new String[this.row][this.column];
		int i, j;
		for(i=0;i<this.row;i++){
			for(j=0;j<this.column;j++){
				this.stringArray[i][j]=generateRandom();
			}
		}
	}

	private String generateRandom() {
		Random random = new Random();
		String string = (String.valueOf((char) (32 + random.nextInt(94))));
		string += (String.valueOf((char) (32 + random.nextInt(94))));
		string += (String.valueOf((char) (32 + random.nextInt(94))));
		return string;
	}
}
