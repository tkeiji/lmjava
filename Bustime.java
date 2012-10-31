import java.io.*;
import java.util.*;

public class Bustime {
	public static void main (String[] args){

		ArrayList<Bus> table = new ArrayList<Bus>();
		tableRead ("regbus.txt", false, table );
		tableRead ("twinbus.txt", true, table );
		Bus[] array = (Bus[])(table.toArray( new Bus[0] )); 
		Arrays.sort( array );
		
		Calendar bustime;
		Calendar begin = Calendar.getInstance();

		Calendar end = Calendar.getInstance();

		System.out.println( "Current Time is "
				+end.get(Calendar.HOUR_OF_DAY) +":"+
				+end.get(Calendar.MINUTE) +":"+
				+end.get(Calendar.SECOND) );		

		begin.add(Calendar.MINUTE,-10);
		end.add(Calendar.MINUTE,30);
		
		for( int i=0; i< array.length; i++ ){
			bustime = array[i].getTime();
			if( bustime.after(begin) && bustime.before(end) )
				System.out.println( array[i] );
		}
	}
	public static void tableRead (String filename, boolean twin, ArrayList<Bus> table ){
		Bus busdata;
		Calendar today = Calendar.getInstance();
		
		try{
			FileReader f = new FileReader( filename );
			BufferedReader b = new BufferedReader(f);
			String line;
			while(( line = b.readLine()) != null ){
				busdata = new Bus(line, twin, today);
				table.add(busdata);
			}
		}catch(Exception e){
			System.out.println("File Access Error");
		}
	}
}
class Bus implements Comparable{

	private Calendar time;
	private boolean twin;
	
	public Bus( String timeString, boolean twinliner, Calendar today ) {
		String[] hm;
		hm = timeString.split(":");				
		this.time = (Calendar)today.clone();
		this.time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hm[0]) );
		this.time.set(Calendar.MINUTE, Integer.parseInt(hm[1]) );
		this.time.set(Calendar.SECOND, 0);
		this.twin = twinliner;
	}
	public Calendar getTime(){
		return time;
	}
	public String toString() {
		if( twin == true )
			return time.get(Calendar.HOUR_OF_DAY)+
				":"+
				time.get(Calendar.MINUTE)+
				" twinliner";
		else
			return time.get(Calendar.HOUR_OF_DAY)+
				":"+
				time.get(Calendar.MINUTE);
	}
	public int compareTo(Object obj) {
		return this.time.compareTo(((Bus)obj).getTime());
	}
	
}

