package teretana;

import java.time.LocalDate;

public class Korisnik {
	public int id, idTrener;
	public String ime, prezime;
	public String dolazak;
	public Clanarina clanarina;
	public String zakazanTrening;
	
	public Korisnik(int id, String ime, String prezime) {
		this(id, 0, ime, prezime, LocalDate.EPOCH.toString(), new Clanarina(), LocalDate.EPOCH.toString());
	}
	
	public Korisnik(int id, int idTrener, String ime, String prezime, String dolazak, Clanarina clanarina, String zakazanTrening) {
		this.id = id;
		this.idTrener = idTrener;
		this.ime = ime;
		this.prezime = prezime;
		this.dolazak = dolazak;
		this.clanarina = clanarina;
		this.zakazanTrening = zakazanTrening;
	}

	public boolean imaClanarinu() {
		return (clanarina.brojTreninga > 0 || dolazak.equals(LocalDate.now().toString())) && clanarina.istice.isAfter(LocalDate.now());
	}
	
	public boolean imaZakazanTrening() {
		return zakazanTrening != null && !LocalDate.now().isAfter(LocalDate.parse(zakazanTrening));
	}
}


