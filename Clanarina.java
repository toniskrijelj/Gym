package teretana;

import java.time.LocalDate;

public class Clanarina{
	private static final int neo = 10000;
	private static final int[] duzinaPoTipu = {1,31,31,366}; 
	private static final int[] brojTreningaPoTipu = {neo,12,neo,neo}; 
	public static final String[] opis = {"DAN","MESEC 12","MESEC NEOGRANICENO","GODISNJA"};
	
	public LocalDate datum, istice;
	public int tip; // 0 - dan, 1 - mesec 12, 2 - mesec neo, 3 - godisnja neo,
	public int brojTreninga;
	
	public Clanarina() {
		this(LocalDate.EPOCH, 0, neo);
	}
	
	public Clanarina(int tip) {
		this(LocalDate.now(), tip, brojTreningaPoTipu[tip]);
	}
	
	public Clanarina(LocalDate datum, int tip, int brojTreninga) {
		this.datum = datum;
		this.tip = tip;
		this.brojTreninga = brojTreninga;
		this.istice = datum.plusDays(duzinaPoTipu[tip]);
	}
}