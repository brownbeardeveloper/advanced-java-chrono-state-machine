package Controller;

public interface PossibleActions {

	public void runChrono();
	public void handleInputEventFromGUI(String buttonType);
	public void toggleChronoType();
	public void handleConfigButton();
	public void handlePlusButton();
	public void handleMinusButton();
	public void handleNextButton();
	public void updateChronoDisplay();
}