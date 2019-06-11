/**
 * @author JFreitas
 *
 */

public class Main {

	private static Model model;
	
	public static void main(String[] args) {

		model = Model.getInstance();
		//initializeModel(model);   // Ao inicializar uma vez a lista foi gravada.
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
		model.addFilme(new Filme("Casablanca", "23:50", "http://www.cine6.com", "1234"));
		model.addFilme(new Filme("Tubarão", "15:00", "http://www.cine7.com", "8564"));
		model.addFilme(new Filme("Titanic", "21:00", "http://www.cine8.com", "6472"));
		model.addFilme(new Filme("Matrix", "17:00", "http://www.cine9.com", "3295"));
		model.addFilme(new Filme("Gladiador", "20:30", "http://www.cine10.com", "6194"));
		model.addFilme(new Filme("Capitão America - O Primeiro Vingador", "18:30", "http://www.cine11.com", "8394"));
		model.addFilme(new Filme("Capitão America - Soldado Invernal", "21:30", "http://www.cine12.com", "8594"));
		model.addFilme(new Filme("Capitão America - Guerra Civil", "20:30", "http://www.cine17.com", "8594"));
		model.addFilme(new Filme("Homem de Ferro", "15:00", "http://www.cine14.com", "4962"));
		model.addFilme(new Filme("Homem de Ferro 2", "14:00", "http://www.cine15.com", "7625"));
		model.addFilme(new Filme("Homem de Ferro 3", "17:00", "http://www.cine18.com", "8925"));
		
	}
	
}