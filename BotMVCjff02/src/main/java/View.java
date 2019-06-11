/**
 * 
 */

/**
 * @author JFreitas
 *
 */

import java.util.List;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class View implements Observer{

	
	TelegramBot bot = TelegramBotAdapter.build("632725327:AAEqIaDKWKJbmgIih-EYEeZ_jrzY1OrC1-Q");

	//Object that receives messages
	GetUpdatesResponse updatesResponse;
	//Object that send responses
	SendResponse sendResponse;
	//Object that manage chat actions like "typing action"
	BaseResponse baseResponse;
			
	
	int queuesIndex=0;
	
	ControllerSearch controllerSearch; //Strategy Pattern -- connection View -> Controller
	
	boolean searchBehaviour = false;
	
	private Model model;
	
	public View(Model model){
		this.model = model; 
	}
	
	public void setControllerSearch(ControllerSearch controllerSearch){ //Strategy Pattern
		this.controllerSearch = controllerSearch;
	}
	
	public void receiveUsersMessages() {

		
		
		//infinity loop
		while (true){
		
			//taking the Queue of Messages
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(queuesIndex));
			
			//Queue of messages
			List<Update> updates = updatesResponse.updates();

			//taking each message in the Queue
			for (Update update : updates) {
				
				//updating queue's index
				queuesIndex = update.updateId()+1;
				
				if(this.searchBehaviour==true){
					this.callController(update);
					
				}else if(update.message().text().equalsIgnoreCase("filme")){
					setControllerSearch(new ControllerSearchFilmeByName(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Qual o nome do filme?"));
					this.searchBehaviour = true;
					
				}else if(update.message().text().equalsIgnoreCase("todos")){
					setControllerSearch(new ControllerSearchFilmeByName(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"senha:"));
					this.searchBehaviour = true;
					
				} else {
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Escreva filme"));
				}

			}

		}
		
		
	}
	
	
	public void callController(Update update){
		this.controllerSearch.search(update);
	}
	
	public void update(long chatId, String filmesData){
		sendResponse = bot.execute(new SendMessage(chatId, filmesData));
		this.searchBehaviour = false;
		
		//System.out.println(filmesData);	// Impressão da String 'filmesData', montada no Model com informações do banco.
		//model.printAllFilmesDb(); // Impressão de todas informações gravadas no BD4o.
	}
	
	public void sendTypingMessage(Update update){
		baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	}
	

}