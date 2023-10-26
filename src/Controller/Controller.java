package Controller;

import Model.ChronoModel;
import Model.Date;
import Model.Time;
import View.ViewGUI;
import util.State;

public class Controller implements PossibleActions {

    private static State state = State.DISPLAY_DATE;
	private static ViewGUI viewGui;
	private static ChronoModel date;
	private static ChronoModel time;
	private static int offsetIndex = 0;

	public Controller() {}
	
    public Controller(ViewGUI swingGui) {
    	Controller.viewGui = swingGui;
    }
        
    public void runChrono() {
    	
		date = Date.getInstance();
		time = Time.getInstance();
    	
        viewGui.setChronoToggleButtonText("TIME"); 
        viewGui.makeJFrameVisible();
        updateChronoDisplay();
    }
    
    public void handleInputEventFromGUI(String buttonType) {
    	
    	switch (buttonType) {
	        case "chronoToggleBtn" -> toggleChronoType(); // just let those methods do everything as you're wishing
	        case "configBtn" -> handleConfigButton();
	        case "plusBtn" -> handlePlusButton();
	        case "minusBtn" -> handleMinusButton();
	        case "nextBtn" -> handleNextButton();
	        default -> System.out.println("Unknown button type: " + buttonType);
    	}
    }

    private State getState() {
        return state;
    }

    private void setState(State state) {
        Controller.state = state;
        System.out.print("\r Current state: " + state); // this is letting you know which state you're in now
    }
    
    // reset index of offset 
    private void resetOffsetIndex() {
    	offsetIndex = 0;
    }
    
    // switch state between time or date
    public void toggleChronoType() {
    	
    	switch (getState()) {
	        case DISPLAY_TIME -> {
	            setState(State.DISPLAY_DATE); 
	            viewGui.setChronoToggleButtonText("TIME"); 
	        }
	        
	        case DISPLAY_DATE -> {
	            setState(State.DISPLAY_TIME);
	            viewGui.setChronoToggleButtonText("DATE");
	        }
	        
	        case CONFIG_TIME -> {
	            setState(State.DISPLAY_TIME);
	            resetOffsetIndex();
	            time.resetOffsetValues();
	            viewGui.showOffsetButtonsPanel(false);
	            viewGui.setChronoToggleButtonText("DATE");
            	viewGui.setConfigButtonText("CONFIG");
	        }
	        
	        case CONFIG_DATE -> {
	            setState(State.DISPLAY_DATE);
	            resetOffsetIndex();
	            date.resetOffsetValues();
	            viewGui.showOffsetButtonsPanel(false);
	            viewGui.setChronoToggleButtonText("TIME");
            	viewGui.setConfigButtonText("CONFIG");
	        }
	        
	        default -> {
	            System.out.println("Error at Chrono.Java inside toggleChronoType()");
	        }
    	}
    }
    
    // set state to allow change the instance of date or time with offset function
    public void handleConfigButton() {
    	
        switch (getState()) {
        
            case DISPLAY_TIME -> {
            	setState(State.CONFIG_TIME);
            	viewGui.setIndexLabel("hour");
	            viewGui.showOffsetButtonsPanel(true);
    			viewGui.nextBtnSetEnabled(true);
            	viewGui.setChronoToggleButtonText("RESET");
            	viewGui.setConfigButtonText("SAVE");
            	}
            
            case DISPLAY_DATE -> {
            	setState(State.CONFIG_DATE);
            	viewGui.setIndexLabel("year");
	            viewGui.showOffsetButtonsPanel(true);
    			viewGui.nextBtnSetEnabled(true);
            	viewGui.setChronoToggleButtonText("RESET");
            	viewGui.setConfigButtonText("SAVE");
            	}
            
            case CONFIG_TIME -> {            	
            	setState(State.DISPLAY_TIME);
            	resetOffsetIndex();
	            viewGui.showOffsetButtonsPanel(false);
            	viewGui.setChronoToggleButtonText("DATE");
            	viewGui.setConfigButtonText("CONFIG");
            	}
            
            case CONFIG_DATE -> {
            	setState(State.DISPLAY_DATE);
            	resetOffsetIndex();
	            viewGui.showOffsetButtonsPanel(false);
            	viewGui.setChronoToggleButtonText("TIME");
            	viewGui.setConfigButtonText("CONFIG");
            	}
        }
    }
    
    // increase the specified offset from the given time or date instance
    public void handlePlusButton() {
    	    	
        if (getState() == State.CONFIG_TIME) {
        	
        	switch(offsetIndex) {
        	case 0 -> time.setOffset(1,0,0);
        	case 1 -> time.setOffset(0,1,0);
        	case 2 -> time.setOffset(0,0,1);
        	}
        	
        } else if (getState() == State.CONFIG_DATE) {
        	
        	switch(offsetIndex) {
        	case 0 -> date.setOffset(1,0,0);
        	case 1 -> date.setOffset(0,1,0);
        	case 2 -> date.setOffset(0,0,1);
        	}
        }
    }

    // subtract the specified offset from the given time or date instance
    public void handleMinusButton() {
    	
    	if (getState() == State.CONFIG_TIME) {
        	
        	switch(offsetIndex) {
        	case 0 -> time.setOffset(-1,0,0);
        	case 1 -> time.setOffset(0,-1,0);
        	case 2 -> time.setOffset(0,0,-1);
        	}
        	
        } else if (getState() == State.CONFIG_DATE) {
        	
        	switch(offsetIndex) {
        	case 0 -> date.setOffset(-1,0,0);
        	case 1 -> date.setOffset(0,-1,0);
        	case 2 -> date.setOffset(0,0,-1);
        	}
        }
    }
    
    // increase the array index of specified offset
    public void handleNextButton() {
    	
    	offsetIndex++;
    	    	    	    	
    	if (getState() == State.CONFIG_TIME) {
    		
    		if(offsetIndex == 1) {
    			
    			viewGui.setIndexLabel("minute");

    		} else if (offsetIndex == 2) {
    			
    			viewGui.setIndexLabel("second");
    			viewGui.nextBtnSetEnabled(false);
    		}
    		    		
        } else if (getState() == State.CONFIG_DATE) {
        	
        	if(offsetIndex == 1) {
    			
        		viewGui.setIndexLabel("month");

    		} else if (offsetIndex == 2) {
    			
    			viewGui.setIndexLabel("day");
    			viewGui.nextBtnSetEnabled(false);
    		}
        } 
    }
    
    public void updateChronoDisplay() {    	
   	 try {
   		 while(true) {
   			 
   			 if(getState() == State.CONFIG_TIME || getState() == State.DISPLAY_TIME) {
   				 
   	 				viewGui.setChronoDisplay( time.getInstant() );
   				 
   			 } else if (getState() == State.CONFIG_DATE || getState() == State.DISPLAY_DATE) {
   				 
   	 				viewGui.setChronoDisplay( date.getInstant() );

   			 }
				Thread.sleep(250);
   		}
   		 
   	 } catch (InterruptedException e) {
				System.out.println("Couldn't update the display by controller...");
				System.out.println(e.getMessage());
		 }
   }
}