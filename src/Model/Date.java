package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date extends ChronoModel {

	// Instance of this class
    private static Date INSTANCE;

    // offset values 
    protected static int offsetYears = 0, offsetMonths = 0, offsetDays = 0;

	public Date() {
		super();
	}

	// There will just be one instance of this class
    public static Date getInstance() {
    	
        if(INSTANCE == null) {INSTANCE = new Date();}

        return INSTANCE;
    }
    
    public void resetOffsetValues() {
    	offsetYears = offsetMonths = offsetDays = 0;
    }

    // add offset values to the current date 
    public void setOffset(int x, int y, int z) {
        offsetYears += x;
        offsetMonths += y;
        offsetDays += z;
    }

    // get the current date with the set offset values
    public String getInstant() {
        return LocalDateTime
                .now()
                .plusYears(offsetYears)
                .plusMonths(offsetMonths)
                .plusDays(offsetDays)
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
