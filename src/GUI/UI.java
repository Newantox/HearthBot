package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.GamePlay;

public class UI extends JFrame {

    private ArrayList<GamePlay> games;
    private GameScreen currentScreen;
    
    private boolean ready;
	
	JPanel mainPanel = new JPanel(new BorderLayout());
	JPanel infoPanel = new JPanel();
	JPanel navigationPanel = new JPanel();
	JPanel boardPanel = new JPanel(new BorderLayout());
	
	JButton prevStepButton = new JButton("Prev Step");
	JButton nextStepButton = new JButton("Next Step");
	JButton prevTurnButton = new JButton("Prev Turn");
	JButton nextTurnButton = new JButton("Next Turn");

	public UI() {
		setTitle("HearthBot");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		if (ready) buildScreen();
    }
	
	public void setGames(ArrayList<GamePlay> games) {
		this.games = games;
		this.ready = true;
	}

	public static void main(String[] args) {
		new UI();
	}
    
	public void buildScreen() {
		
		prevStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentScreen.prevStep();
                getContentPane().removeAll();
                mainPanel.remove(boardPanel);
                boardPanel.removeAll();
                boardPanel.add(currentScreen,BorderLayout.CENTER);
                mainPanel.add(boardPanel);
                getContentPane().add(mainPanel);
                repaint();
                printAll(getGraphics());
            }
        });
		
		nextStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
               currentScreen.nextStep();
               getContentPane().removeAll();
               mainPanel.remove(boardPanel);
               boardPanel.removeAll();
               boardPanel.add(currentScreen,BorderLayout.CENTER);
               mainPanel.add(boardPanel);
               getContentPane().add(mainPanel);
               repaint();
               printAll(getGraphics());
            }
        });
		
		prevTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentScreen.prevTurn();
                getContentPane().removeAll();
                mainPanel.remove(boardPanel);
                boardPanel.removeAll();
                boardPanel.add(currentScreen,BorderLayout.CENTER);
                mainPanel.add(boardPanel);
                getContentPane().add(mainPanel);
                repaint();
                printAll(getGraphics());
            }
        });
		
		nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
               currentScreen.nextTurn();
               getContentPane().removeAll();
               mainPanel.remove(boardPanel);
               boardPanel.removeAll();
               boardPanel.add(currentScreen,BorderLayout.CENTER);
               mainPanel.add(boardPanel);
               getContentPane().add(mainPanel);
               repaint();
               printAll(getGraphics());
            }
        });
		
		JComboBox<Integer> optionList = new JComboBox<Integer>();
		for (int i = 0; i<games.size(); i++) {
			optionList.addItem(i+1);
		}
		optionList.addActionListener(new ActionListener() {
		    @SuppressWarnings("unchecked")
			@Override
		    public void actionPerformed(ActionEvent event) {
		       JComboBox<Integer> combo = (JComboBox<Integer>) event.getSource();
		       int choice = (int) combo.getSelectedItem();
		       currentScreen.reset();
		       currentScreen = new GameScreen(games.get(choice-1));
		       getContentPane().removeAll();
               mainPanel.remove(boardPanel);
               boardPanel.removeAll();
               boardPanel.add(currentScreen,BorderLayout.CENTER);
               mainPanel.add(boardPanel);
               getContentPane().add(mainPanel);
               repaint();
               printAll(getGraphics());
		    }
		});
		
		infoPanel.add(optionList);
		     
		navigationPanel.add(prevStepButton);
		navigationPanel.add(nextStepButton);
		navigationPanel.add(prevTurnButton);
		navigationPanel.add(nextTurnButton);
		
		currentScreen = new GameScreen(games.get(0));
		boardPanel.add(currentScreen,BorderLayout.CENTER);
		     
		mainPanel.add(infoPanel,BorderLayout.NORTH);
		mainPanel.add(boardPanel,BorderLayout.CENTER);
		mainPanel.add(navigationPanel,BorderLayout.SOUTH);
		     
		add(mainPanel);
		     
		setVisible(true);
	}
    
}