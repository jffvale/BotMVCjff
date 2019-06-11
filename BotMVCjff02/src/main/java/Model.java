/**
 * 
 */

/**
 * @author JFreitas
 *
 */

import java.util.LinkedList;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import com.pengrad.telegrambot.model.Update;

public class Model implements Subject {
	
	private List<Observer> observers = new LinkedList<Observer>();
	
	ObjectContainer filmes = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/filmes.db4o");
	
	private static Model uniqueInstance;
	
	private Model(){}
	
	public static Model getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}
	
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
	
	public void notifyObservers(long chatId, String filmesData){
		for(Observer observer:observers){
			observer.update(chatId, filmesData);
		}
	}
	
	public void addFilme(Filme filme){
		    
			filmes.store(filme);
			filmes.commit();
			
	}
	
	
	public void searchFilme(Update update){
		Query query = filmes.query();
		query.constrain(Filme.class);
	    ObjectSet<Filme> allFilmes = query.execute();

	    
		String filmesData = null;
		for(Filme filme: allFilmes){
			String senha = "jff"; //Palavra 'todos' ao invés de 'filme' no bot.
			if(filme.getName().equalsIgnoreCase(update.message().text())){ 
				filmesData = "Filme: " + filme.getName();
				filmesData += "\nHorário: " + filme.getHorario();
				filmesData += "\nTrailer: " + filme.getTrailer();
				filmesData += "\nSinopse: " + filme.getSinopse();
				filmesData += "\nEscreva Filme para procurar outro filme.\n";
				System.out.println("\nFilme pesquisado\n" + filmesData);
			}
			if(senha.equals(update.message().text())){
				for (Filme filme1 : allFilmes) {
					this.notifyObservers(update.message().chat().id(), filme1.getName());
					this.notifyObservers(update.message().chat().id(), filme1.getHorario());
					this.notifyObservers(update.message().chat().id(), filme1.getTrailer());
					this.notifyObservers(update.message().chat().id(), filme1.getSinopse());
				}
				
				printAllFilmesDb();
				filmesData = "";
				filmesData += "Escreva Filme para procurar outro filme.\n";
				break;
			}
		}
		
		if(filmesData != null){
			this.notifyObservers(update.message().chat().id(), filmesData);
		} else {
			this.notifyObservers(update.message().chat().id(), "Filme not found\nEscreva filme");
		}
		
	}
	
	public void printAllFilmesDb() {
		Query query = filmes.query();
		query.constrain(Filme.class);
	    ObjectSet<Filme> allFilmes = query.execute();
	    
	    System.out.println("\n\nListagem de todos os Filmes registrados no BD");
	    
	    for (Filme filme : allFilmes) {
            System.out.println("Filme " + filme.getName() + "\tHorario " + filme.getHorario() + "\tTrailer " + filme.getTrailer()
            					+ "\tSinopse " + filme.getSinopse());
        }
	}
		
}

