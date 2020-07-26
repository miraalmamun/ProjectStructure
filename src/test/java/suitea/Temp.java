package suitea;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Temp {

	public static void main(String[] args)  {
		Date d = new Date();
		System.out.println(d.toString());
		String date_selected="12-04-2018";// dd-MM-yyyy

		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date dateToSelected = sd.parse(date_selected);
			System.out.println(dateToSelected.toString());
			// compare dates
			System.out.println(d.compareTo(dateToSelected));
			
			String day = new SimpleDateFormat("dd").format(dateToSelected);
			System.out.println(day);
			String month= new SimpleDateFormat("MMMM").format(dateToSelected);
			System.out.println(month);
			String year= new SimpleDateFormat("yyyy").format(dateToSelected);
			System.out.println(year);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
