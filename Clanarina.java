package teretana;

import java.time.LocalDate;

public class Clanarina{
	public String datum, istice;
	public int tip; // 0 - dan, 1 - mesec 12, 2 - mesec neo, 3 - godisnja neo,
	public int brojTreninga;
	
	public Clanarina() {
		this(LocalDate.EPOCH.toString(), 0, 10000);
	}
	
	public Clanarina(int tip) {
		this(LocalDate.now().toString(), tip, tip == 1 ? 12 : 10000);
	}
	
	public Clanarina(String datum, int tip, int brojTreninga) {
		this.datum = datum;
		this.tip = tip;
		int duzina = 31;
		if(tip == 0) duzina = 1;
		else if(tip == 3) duzina = 366;
		this.brojTreninga = brojTreninga;
		this.istice = LocalDate.parse(datum).plusDays(duzina).toString();
	}
}