import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class DDL {
	
	

	 static void createType( String typeName, int fieldNumber) {
		
		boolean isDeleted;
		boolean isCreated;
		
		Scanner s = new Scanner(System.in);
		File f = new File ("/Users/gg/Documents/ggdb/sys.cat");
		byte fileContent[] = new byte [4096];
		
		try {
			
			RandomAccessFile raf = new RandomAccessFile(f,"rw");
			
			int cursor=(int)raf.length();
			
			/*
			while(cursor<4096 && raf.length()>0) {
				fileContent[cursor] = raf.readByte();
				if(cursor==raf.length()-1) break;
				cursor++;
			}
			*/
			
			if(cursor==0) { //We are creating the first page if sys.cat is empty
				raf.seek(0);
				raf.writeShort(0); //2 bytes for page-id 
				raf.writeShort(2042); //2 bytes for remaining space
				raf.writeShort(2048); //2 bytes for next page pointer
				
			}
			else if(cursor>1906 && cursor<2048) { //If there is not enough space in the first page of 
				                 //the sys.cat file, we create the second page 
				raf.seek(2048);
				raf.writeShort(2048); //2 bytes for page-id
				raf.writeShort(2042); //2 bytes for remaining space
				raf.write(null); //2 bytes for next page pointer
			}
			
			
			int lastPage, recordID; //RecordID will be the ID of the record to be created
			String fieldName;
			
			
			if(cursor<3954) { //If enough space for a record is left in the file
				lastPage = ((int)raf.length()/1906)+1; //Finds which one is the last page with remaining space 
				cursor= (lastPage-1)*2048+6; //Places the cursor immediately after the page header of the last page
				recordID=(lastPage-1)*20 + 1; 
				raf.seek(cursor); 
				while(raf.read()!= -1) {   //If not at the last record
					raf.seek(cursor+3);    //Move the cursor to the 'next record pointer' field of the current record header
					cursor=raf.readShort(); //Assign the next record pointer value into the cursor 		   
					recordID++; 				
					raf.seek(cursor); //Move the cursor to the beginning point of the next record
				}
				raf.writeShort((short)recordID); 
				raf.writeBoolean(false);
				raf.writeShort(((short)cursor+100));
				raf.writeBytes(typeName);
				for(int i=0; i<(12-typeName.length()) ; i++) { //rest of the field is filled with zeroes to reach 12 bytes
					raf.writeByte(0);
				}
				raf.writeInt(fieldNumber);
				for(int i = 0 ; i < fieldNumber ; i++) {//Field names are taken from the user
					System.out.println("Please enter the field name for the field #"+(i+1));
					fieldName=s.next();
					raf.writeBytes(fieldName);
					for(int j=0; j<(9-fieldName.length()) ; j++) {////rest of the field is filled with zeroes to reach 9 bytes
						raf.writeByte(0);
					}
				}
				
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	static void deleteType ( String typeName) {
		
		boolean typeFound;
		
	}
	
	
	static void listAllTypes () {
		
		
	}
	
	
}
 