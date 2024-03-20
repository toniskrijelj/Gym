package teretana;

import java.time.LocalDate;

public class Clanarina{
	public String datum;
	public int tip; // 0 - dan, 1 - mesec 12, mesec neo,
	public int brojTreninga;
	
	public Clanarina() {
		this(LocalDate.EPOCH.toString(), 0, 0);
	}
	
	public Clanarina(String datum, int tip) {
		this(datum, tip, tip == 1 ? 12 : 366);
	}
	
	public Clanarina(String datum, int tip, int brojTreninga) {
		this.datum = datum;
		this.tip = tip;
	}
}