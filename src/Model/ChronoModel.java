package Model;

public abstract class ChronoModel {
	
    protected static int offsetYears, offsetMonths, offsetDays;
	
	public ChronoModel() {
        ChronoModel.offsetYears = 0;
        ChronoModel.offsetMonths = 0;
        ChronoModel.offsetDays = 0;
	}

	public abstract void setOffset(int x, int y, int z);
	
	public abstract void resetOffsetValues();

	public abstract String getInstant();
}
