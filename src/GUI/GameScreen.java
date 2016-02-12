package GUI;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.GamePlay;

public class GameScreen extends JPanel {
		
	private static final long serialVersionUID = 1L;

	private GamePlay game = new GamePlay();
		
	JPanel mainPanel = new JPanel(new BorderLayout());
	JPanel boardPanel = new JPanel(new BorderLayout());
		
	public GameScreen(GamePlay game) {
		this.game = game;
		/*//--------------------------
		Deck myDeck = new Deck();
		myDeck = myDeck.add(new BlessingOfMight(),2);
		myDeck = myDeck.add(new Equality(),2);
		myDeck = myDeck.add(new ShieldedMinibot(),2);
		myDeck = myDeck.add(new SwordOfJustice(),1);
	//	myDeck = myDeck.add(new DivineFavour(),2);
		myDeck = myDeck.add(new MusterForBattle(),2);
		myDeck = myDeck.add(new TruesilverChampion(),2);
		myDeck = myDeck.add(new Consecration(),2);
		myDeck = myDeck.add(new HammerOfWrath(),2);
		//myDeck = myDeck.add(new AvengingWrath(),1);
		myDeck = myDeck.add(new AbusiveSergeant(),2);
		myDeck = myDeck.add(new ArgentSquire(),2);
		myDeck = myDeck.add(new LeperGnome(),2);
		myDeck = myDeck.add(new SouthseaDeckhand(),2);
		myDeck = myDeck.add(new KnifeJuggler(),2);
		myDeck = myDeck.add(new LootHoarder(),1);
		myDeck = myDeck.add(new Wolfrider(), 1);
		
		Deck enemyDeck = new Deck();
		enemyDeck = enemyDeck.add(new BlessingOfMight(),2);
		enemyDeck = enemyDeck.add(new Equality(),2);
		enemyDeck = enemyDeck.add(new ShieldedMinibot(),2);
		enemyDeck = enemyDeck.add(new SwordOfJustice(),1);
//		enemyDeck = enemyDeck.add(new DivineFavour(),2);
		enemyDeck = enemyDeck.add(new MusterForBattle(),2);
		enemyDeck = enemyDeck.add(new TruesilverChampion(),2);
		enemyDeck = enemyDeck.add(new Consecration(),2);
		enemyDeck = enemyDeck.add(new HammerOfWrath(),2);
		//enemyDeck = enemyDeck.add(new AvengingWrath(),1);
		enemyDeck = enemyDeck.add(new AbusiveSergeant(),2);
		enemyDeck = enemyDeck.add(new ArgentSquire(),2);
		enemyDeck = enemyDeck.add(new LeperGnome(),2);
		enemyDeck = enemyDeck.add(new SouthseaDeckhand(),2);
		enemyDeck = enemyDeck.add(new KnifeJuggler(),2);
		enemyDeck = enemyDeck.add(new LootHoarder(),1);
		enemyDeck = enemyDeck.add(new Wolfrider(), 1);
		Hero hero1 = new Uther("Uther",TargetsType.ALLYCHAR,30,30,0,0,0,new Hand(), myDeck, 0, 0, null);
		Hero hero2 = new Uther("Uther2",TargetsType.ENEMYCHAR,30,30,0,0,0,new Hand(), enemyDeck, 0, 0, null);
		MyTurnState config = new BoardState(ViewType.UNBIASED,hero1,hero2,new ArrayList<Minion>(),new ArrayList<Minion>(),new ArrayList<Integer>(),0,false);
		MyTurnState config2 = new BoardState(ViewType.UNBIASED,hero2,hero1,new ArrayList<Minion>(),new ArrayList<Minion>(),new ArrayList<Integer>(),0,false);;
		MyTurnState config3 = new BoardState(ViewType.UNBIASED,hero1,hero2,new ArrayList<Minion>(),new ArrayList<Minion>(),new ArrayList<Integer>(),0,false);;
		game.addNewTurn(config);
		config = config.drawCard();
		config = config.drawCard();
		config = config.drawCard();
		config = config.drawCard();
		config = config.resolveRNG(false);
		game.addNewStep(config);
		
		config = config.getActionResult(new PlayCard(new KnifeJuggler(),hero1,0));
		config = config.resolveRNG(false);
		game.addNewStep(config);
		config = config.getActionResult(new PlayCard(new ShieldedMinibot(),hero1,0));
		config = config.resolveRNG(false);
		game.addNewStep(config);
		
		config = config.getActionResult(new PlayCard(new ChillwindYeti(),hero1,0));
		config = ((MyTurnState) config).resolveRNG(false);
		game.addNewStep(config);
		config = config.getActionResult(new EndTurn());
		config = ((MyTurnState) config).resolveRNG(false);
		game.addNewStep(config);
		game.addNewTurn(config2);
		game.addNewStep(config3);*/
		
		buildScreen();
	}
	
	public void reset() {
		game.reset();
	}
	
	public void prevStep() {
		game.prevStep();
		
		mainPanel.remove(boardPanel);
	    boardPanel.removeAll();
	    boardPanel.add(new JLabel(game.outputText()),BorderLayout.NORTH);
	    boardPanel.add(new JLabel(game.outputBoard()),BorderLayout.CENTER);
	    mainPanel.add(boardPanel);
	       
	    repaint();
	    printAll(getGraphics());
	}
	
	public void nextStep() {
		game.nextStep();
		
		mainPanel.remove(boardPanel);
	    boardPanel.removeAll();
	    boardPanel.add(new JLabel(game.outputText()),BorderLayout.NORTH);
	    boardPanel.add(new JLabel(game.outputBoard()),BorderLayout.CENTER);
	    mainPanel.add(boardPanel);
	       
	    repaint();
	    printAll(getGraphics());
	}
	
	public void prevTurn() {
		game.prevTurn();
		
		mainPanel.remove(boardPanel);
	    boardPanel.removeAll();
	    boardPanel.add(new JLabel(game.outputText()),BorderLayout.NORTH);
	    boardPanel.add(new JLabel(game.outputBoard()),BorderLayout.CENTER);
	    mainPanel.add(boardPanel);
	       
	    repaint();
	    printAll(getGraphics());
	}
	
	public void nextTurn() {
		game.nextTurn();

        mainPanel.remove(boardPanel);
        boardPanel.removeAll();
        boardPanel.add(new JLabel(game.outputText()),BorderLayout.NORTH);
        boardPanel.add(new JLabel(game.outputBoard()),BorderLayout.CENTER);
        mainPanel.add(boardPanel);
       
        repaint();
        printAll(getGraphics());
	}
	
	private void buildScreen() {
		
		boardPanel.add(new JLabel(game.outputText()),BorderLayout.NORTH);
		
		boardPanel.add(new JLabel(game.outputBoard()),BorderLayout.CENTER);
		     
		mainPanel.add(boardPanel,BorderLayout.CENTER);
		     
		add(mainPanel);
		     
		setVisible(true);
	}

}