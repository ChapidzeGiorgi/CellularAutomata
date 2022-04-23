package GUI;
public class Controller {
	private MainFrame mainFrame;
	private ArtPanel artPanel;

	public Controller() {
		mainFrame = new MainFrame();
		artPanel = new ArtPanel(new Rule(90));
		mainFrame.setContentPane(artPanel);
	}
}
