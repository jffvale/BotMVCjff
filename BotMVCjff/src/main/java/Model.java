/**
 * 
 */

/**
 * @author JFreitas
 *
 */

import java.util.LinkedList;
import java.util.List;
import com.pengrad.telegrambot.model.Update;

public class Model implements Subject {

	private List<Observer> observers = new LinkedList<Observer>();
	
	private List<Filme> filmes = new LinkedList<Filme>();
	
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
		this.filmes.add(filme);
	}
	
		
	public void searchFilme(Update update){
		String filmesData = null;
		for(Filme filme: filmes){
			if(filme.getName().equalsIgnoreCase(update.message().text())){
				filmesData = "Filme: " + filme.getName();
				filmesData += "\nHorário: " + filme.getHorario();
				filmesData += "\nTrailer: " + filme.getTrailer();
				filmesData += "\nSinopse: " + filme.getSinopse();
				filmesData += "\nEscreva Filme para procurar outro filme.";
			}
		}
		
		if(filmesData != null){
			this.notifyObservers(update.message().chat().id(), filmesData);
		} else {
			this.notifyObservers(update.message().chat().id(), "Filme not found\nEscreva filme");
		}
		
	}
	
		
}

