package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controller.Controller;


public class ViewGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
    private JPanel chronoOffsetButtonsPanel;
	private JLabel chronoDisplay;
	private JLabel indexLabel;
    private JButton chronoToggleButton;
    private JButton configButton;
    private JButton plusBtn;
    private JButton minusBtn;
    private JButton nextBtn;
    private Controller controller;
    
    public ViewGUI() {
    	
		setControllerInstance(); // Initialise controller
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 320, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));        
		contentPane.add(createChronoDisplayPanel(),BorderLayout.NORTH);
		contentPane.add(createModeAndChangeButtonsPanel(),BorderLayout.SOUTH);		
		setContentPane(contentPane);
				
		createOffsetButtonsPanel(); // Initialised but not in use
    }
    
    public void setControllerInstance() {
	    controller = new Controller();
    }
    
    @Override
	public void actionPerformed(ActionEvent event) {
		
    	JButton clickedButton = (JButton) event.getSource();
	    String buttonType = (String) clickedButton.getClientProperty("buttonType");
	    
	    // call the handleInputEventFromGUI method in the controller
	    // to process the event with the specified buttonType
		controller.handleInputEventFromGUI(buttonType);
	}
    
    private JPanel createOffsetButtonsPanel() {
    	
    	// Panel contains offset buttons
        chronoOffsetButtonsPanel = new JPanel();
        chronoOffsetButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
                
        // Offset buttons
        indexLabel = new JLabel("index: 1");
        plusBtn = new JButton("+");
        minusBtn = new JButton("-");
        nextBtn = new JButton("NEXT");
        
        plusBtn.putClientProperty("buttonType", "plusBtn");
        minusBtn.putClientProperty("buttonType", "minusBtn");
        nextBtn.putClientProperty("buttonType", "nextBtn");
        
        
        plusBtn.addActionListener(this);
        minusBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        
        chronoOffsetButtonsPanel.add(indexLabel);
        chronoOffsetButtonsPanel.add(plusBtn);
        chronoOffsetButtonsPanel.add(minusBtn);
        chronoOffsetButtonsPanel.add(nextBtn);
        
        return chronoOffsetButtonsPanel;
    }
    
    private JPanel createChronoDisplayPanel() {
    	
        chronoDisplay = new JLabel();
        chronoDisplay.setFont(new Font("Serif", Font.BOLD, 32));
        
        JPanel chronoDisplayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        chronoDisplayPanel.add(chronoDisplay);

        return chronoDisplayPanel;
    }
    
    private JPanel createModeAndChangeButtonsPanel() {
    	
        chronoToggleButton = new JButton("MODE");
        configButton = new JButton("CONFIG");
        
        chronoToggleButton.putClientProperty("buttonType", "chronoToggleBtn");
        configButton.putClientProperty("buttonType", "configBtn");
        
        chronoToggleButton.addActionListener(this);
        configButton.addActionListener(this);
        
        JPanel modeAndChangeValueButtons = new JPanel();
        modeAndChangeValueButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        modeAndChangeValueButtons.add(chronoToggleButton);
        modeAndChangeValueButtons.add(configButton);
    	
    	return modeAndChangeValueButtons;
    }
    
	public void showOffsetButtonsPanel(boolean ifShow) {
    	
    	if (ifShow) {
    		contentPane.add(chronoOffsetButtonsPanel);
    	} else {
    		contentPane.remove(chronoOffsetButtonsPanel);
    	}
    	repaint();
    }
    
    public void setChronoDisplay(String message) {
    	chronoDisplay.setText(message);
    }
    
    public void setChronoToggleButtonText(String message) {
    	chronoToggleButton.setText(message);
    }
    
    public void setConfigButtonText(String message) {
    	configButton.setText(message);
    }
    
    public void setIndexLabel(String message) {
    	indexLabel.setText(message);
    }
    
    public void nextBtnSetEnabled(Boolean bool) {
    	nextBtn.setEnabled(bool);
    }

    // make this GUI application visible for the user 
	public void makeJFrameVisible() {
				
		EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
}
