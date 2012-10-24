import java.io.*;
import java.util.*;

public class Bustime {
	public static void main (String[] args){

		Calendar today = Calendar.getInstance();

		ArrayList<Calendar> table = new ArrayList<Calendar>();
		Calendar bustime;
		
		try{
			FileReader f = new FileReader("regbus.txt");
			BufferedReader b = new BufferedReader(f);
			String line;
			String[] hm;
			while(( line = b.readLine()) != null ){
				hm = line.split(":");				
				bustime = (Calendar)today.clone();
				bustime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hm[0]) );
				bustime.set(Calendar.MINUTE, Integer.parseInt(hm[1]) );
				bustime.set(Calendar.SECOND, 0);

				table.add(bustime);
			}
		}catch(Exception e){
			System.out.println("File Access Error");
		}
		int len = table.size();
		for( int i=0; i< len; i++ ){
			System.out.println( 
				table.get(i).get(Calendar.HOUR_OF_DAY)+
				":"+
				table.get(i).get(Calendar.MINUTE)
			);
		}
	}
}
