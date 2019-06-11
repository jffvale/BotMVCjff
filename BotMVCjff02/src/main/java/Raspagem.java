//import javax.lang.model.element.Element;
//import org.junit.experimental.theories.suppliers.TestedOn;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.IOException;

/**
 * @author JFreitas
 *
 */
public class Raspagem {
	private Document document;	// atributo document do tipo Jsoup armazena todo conteudo da pagina HTML.
	
	public Raspagem(Document document) {
		this.document = document;
	}
	
	public static void main(String[] args) {	//A classe Jsoup tem metodo connect (recebe a url da pagina e o get captura os dados da pagina.
		try {
			Document document = Jsoup.connect("https://www.cinemark.com.br/sao-jose-dos-campos/filmes/em-cartaz?pagina=").get();
			Raspagem parserRaspagem = new Raspagem(document);
			parserRaspagem.getRaspagem();
			//System.out.println(document);	//Teste de impressão pagina capturada		
		} catch (IOException e) {  // O metodo connect exige que seja criada uma exceção do tipo IOException.
			e.printStackTrace();
			
		}

	}
	
	private void getRaspagem() {	// Criar lista Elements da bibiblioteca JSopup
		Elements elements = document.getElementsByClass("movie-container");
		
		getRaspagemFilme(elements);   // Retornando com 'eq' para  que ele retorne uma lista
		System.out.println(elements);
	}

	String classificacao = null;
	String title= null;
	String c = null;
	String trailer = null;
	String sinopse = "https://www.cinemark.com.br";
	
	private void getRaspagemFilme(Elements elements) {
		
		
		//System.out.println(sinopse);
		
		for (Element element: elements) {
			
			Elements container = element.getElementsByClass("movie-container");
			//System.out.println("\nFOR01\n" + container);
			Elements children = element.getElementsByTag("div");
			
			
			for(Element div : children) {
				// Classificacao
				Elements limpa = div.getElementsByClass("rating-title");
				classificacao = limpa.text();
				String aux = div.getElementsByTag("a").attr("title");
				//System.out.println(classificacao);
				//Titulo
				Elements limpa2 = div.getElementsByClass("movie-image"); // identifica a tag html que possui o nome da classe "movie-image"
				c = limpa2.attr("title"); // retorna o conteudo do atributo title
				c= c.replace("Filme ", "");// retirando a string "filme " da String original
				title = c;
				//System.out.print(title + " ");
				
				// Link Trailer
				Elements children2 = element.getElementsByTag("li");
				for(Element li : children2) {
					String aux3 = li.getElementsByTag("a").attr("href");
					trailer = aux3;
					//System.out.println("\nlink: https:" + trailer);
				}
				
				// Link Sinopse
				Elements limpa3 = div.getElementsByClass("movie-title");
				aux = limpa3.text();
				String aux2 = div.getElementsByTag("a").attr("href");
				sinopse = aux2;
				
				//System.out.println(sinopse);
				
				//String classificacao = div.getElementsByTag("span").text();
				
	// Como pegar só a primeiro texto do span class????			
				//String horario =;
//				String Sinopse = div.getElementsByTag("a").attr("href");
//				System.out.println(title + "\n"  + classificacao + "\nEsta é a sinopse " + " https://www.cinemark.com.br"+ Sinopse);
			}
			
			//System.out.println(classificacao);
			//System.out.println("Trailer link: https:" + trailer);
			//System.out.println(sinopse);
			//System.out.println(title);
			
//			Elements children2 = element.getElementsByTag("li");
//			for(Element li : children2) {
//				String trailer = li.getElementsByTag("a").attr("href");
//				System.out.println("\nlink: https:" + trailer);
//			}
			
		}
		
			/* Phyton
			 * s = bs(p.content, "html.parser")    #usando o bs do comentario anterior
		    for filme in s.findAll('a',
		                {'class':'movie-image'}):                     
		        filmes.add(filme['title'][6:])
		
		for f in filmes:
		    print (f)
			 
		*/
	}
}
