package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Time extends ChronoModel {
	
	// Instance of this class
	private static Time INSTANCE;
    
    // offset values 
    protected static int offsetHours = 0, offsetMinutes = 0, offsetSeconds = 0;

    public Time() {
		super();
	}

	// There will just be one instance of this class
    public static Time getInstance() {

        if(INSTANCE == null) { INSTANCE = new Time();}

        return INSTANCE;
    }
    
    public void resetOffsetValues() {
		offsetHours = offsetMinutes = offsetSeconds = 0;
	}
    
    // add offset values to the current time 
    public void setOffset(int x, int y, int z) {
        offsetHours += x;
        offsetMinutes += y;
        offsetSeconds += z;
    }
    
    // get the current time with the set offset values
    public String getInstant() {
        return LocalDateTime
                .now()
                .plusHours(offsetHours)
                .plusMinutes(offsetMinutes)
                .plusSeconds(offsetSeconds)
                .truncatedTo(ChronoUnit.SECONDS)
                .format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
