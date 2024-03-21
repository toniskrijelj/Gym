package teretana;

import java.time.LocalDate;
import java.util.ArrayList;

public class Korisnik {
	public int id;
	public String ime, prezime;
	public String dolazak;
	public Clanarina clanarina;
	
	public Korisnik(int id, String ime, String prezime) {
		this(id, ime,prezime,LocalDate.EPOCH.toString(), new Clanarina());
	}
	
	public Korisnik(int id, String ime, String prezime,String dolazak, Clanarina clanarina) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.dolazak = dolazak;
		this.clanarina = clanarina;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
