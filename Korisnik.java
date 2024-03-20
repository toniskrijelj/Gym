package teretana;

public class Korisnik {
	private String ime, prezime;
	private int id;
	
	
	public Korisnik(String ime, String prezime, int id) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.id = id;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getIme(){
		return ime;
	}
	
}
