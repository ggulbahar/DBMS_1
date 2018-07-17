
import java.util.Scanner;

public class main {
	
	public static void main (String []args) {
		
		Scanner s = new Scanner(System.in);
		
		askUser();
		
		int operation = s.nextInt();
		
		showChoice(operation);
		
		
		
	}
	
	static void askUser (){
		System.out.println ("Operations:");
		System.out.println ("1. Create a type ");
		System.out.println ("2. Delete a type ");
		System.out.println ("3. List all types");
		System.out.println ("4. Create a record");
		System.out.println ("5. Delete a record");
		System.out.println ("6. Search a record by a key");
		System.out.println ("7. List all records of a type");
		System.out.println (" \nPlease choose an operation :");
		
	}
	
	static void showChoice (int operation) {
		Scanner s = new Scanner (System.in);
		
		switch (operation){
		
		case 1 : System.out.println ("You are creating a type.\nSpecify the name of the type: ");
				String typeName = s.next();
				
				System.out.println ("Specify how many fields the type will have: ");
				int fieldNumber = s.nextInt();
				
				DDL.createType(typeName, fieldNumber);
				
				break;
				
		case 2 : System.out.println ("You are deleting a type.\nEnter the name of the type: ");
				typeName = s.next();
				
				DDL.deleteType(typeName);
		
				break;
				
		case 3 : System.out.println ("You are listing all types.");
			
				DDL.listAllTypes();

				break;
				
		case 4 : System.out.println ("You are creating a record.\nEnter the type name: ");
				typeName= s.next();
				
				System.out.println("Enter the number of fields: ");
				fieldNumber= s.nextInt();
				
				DML.createRecord(typeName, fieldNumber);
				
				break;
				
		case 5 : System.out.println ("You are deleting a record.\nEnter the type name:  ");
				typeName= s.next();
		
				System.out.println("Enter the key for the record: ");
				int key= s.nextInt();
				
				DML.deleteRecord(typeName, key);
				
				break;
				
		case 6 : System.out.println ("You are searching a record by key.\nEnter the type name: ");
				typeName= s.next();
		
				System.out.println("Enter the key for the record: ");
				key= s.nextInt();
		
				DML.searchRecord(typeName, key);
		
				break;
				
		case 7 : System.out.println ("You are listing all records of a type.\nEnter the type name: ");
				typeName= s.next();
		
				DML.listAllRecords(typeName);
				
				break;
				
		default : System.out.println ("You entered an invalid value. Please enter again: ");
				operation=s.nextInt();
				showChoice(operation);
				break;
		}
	}
}
