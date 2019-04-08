/**
 * 
 */

/**
 * @author JFreitas
 *
 */

public class Main {

	private static Model model;
	
	public static void main(String[] args) {

		model = Model.getInstance();
		initializeModel(model);
		View view = new View(model);
		model.registerObserver(view); //connection Model -> View
		view.receiveUsersMessages();

	}
	
	public static void initializeModel(Model model){
		model.addFilme(new Filme("Shazam", "14:00", "http://www.cine1.com", "5678"));
		model.addFilme(new Filme("Dumbo", "17:00", "http://www.cine2.com", "3456"));
		model.addFilme(new Filme("Capitã Marvel", "15:45", "http://www.cinemark.com", "9462"));
		model.addFilme(new Filme("Aquaman", "20:00", "http://www.cine4.com", "3175"));
		model.addFilme(new Filme("Batman", "23:00", "http://www.cine5.com", "7924"));
	}

}