/**
 * 
 */

/**
 * @author JFreitas
 *
 */
public class Filme {
	private String name;
	private String horario;
	private String trailer;
	private String sinopse;
	
	public Filme(String name, String horario, String trailer, String sinopse) {
		this.name = name;
		this.horario = horario;
		this.trailer = trailer;
		this.sinopse = sinopse;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}	
	
		
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

}

