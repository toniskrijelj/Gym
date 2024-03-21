package teretana;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Korisnici {
	private static final Korisnici korisnici = new Korisnici();
	
	public static ArrayList<Korisnik> lista;
	
	private Korisnici() {
		lista = new ArrayList<>();
		ucitaj();
	}
	
	private void ucitaj() {
		try {
            Scanner fr = new Scanner(new File(HelloApplication.path));
            while(fr.hasNext()){
                String s = fr.nextLine();  
                try {
                	lista.add(ucitaj(s));
                }
                catch(Exception e) {
                	
                }
            }
            fr.close();
        } catch (Exception e){
        	
        }
	}
	
	private Korisnik ucitaj(String s) {
		String[] str = s.split(";");
		int id = Integer.parseInt(str[0]);
		String ime = str[1];
		String prezime = str[2];
		String datumClanarine = str[3];
		int tipClanarine = Integer.parseInt(str[4]);
		int brojTreninga = Integer.parseInt(str[5]);
		String dolazak=str[6];
		return new Korisnik(id, ime, prezime, dolazak, new Clanarina(datumClanarine, tipClanarine, brojTreninga));
	}
	
	private void ispisi() {
        StringBuilder postojeci= new StringBuilder();
        for(int i=0; i<lista.size(); i++) {
            postojeci.append(ispisi(lista.get(i)));
        	if(i+1 < lista.size()) postojeci.append('\n');
        }
        try{
        	File f2 = new File(HelloApplication.path2);
        	if(f2.exists()) f2.delete();
        	//else f2.mkdirs();
        	
        	File f = new File(HelloApplication.path);
        	if(!f.exists()) {
        		//f.mkdirs();
        		f.createNewFile();
        	}
        	File tmp = new File(HelloApplication.tempPath);
        	if(!tmp.exists()) {
        		//tmp.mkdirs();
        		tmp.createNewFile();
        	}
        	
        	FileWriter fw = new FileWriter(HelloApplication.tempPath);
        	fw.write(postojeci.toString());
        	fw.close();
        	
        	f.renameTo(f2);
        	tmp.renameTo(f);
        	//obrisi f2
        }
        catch(Exception e) {
        	// RIP podaci
        }
	}
	public static void save() {
		korisnici.ispisi();
	}
	
	private String ispisi(Korisnik k) {
		StringBuilder s = new StringBuilder();
		s.append(k.id+";");
		s.append(k.ime+";");
		s.append(k.prezime+";");
		s.append(k.clanarina.datum+";");
		s.append(k.clanarina.tip+";");
		s.append(k.clanarina.brojTreninga+";");
		s.append(k.dolazak+";");
		return s.toString();
	}
	
	public static void dodaj(String ime, String prezime) {
		int id=find_id();
		korisnici.lista.add(new Korisnik(id,ime,prezime));
		korisnici.ispisi();
	}
	
	private static int find_id() {
		int id=0;
		for(int i=0; i<korisnici.lista.size(); i++) {
			id=Math.max(id, korisnici.lista.get(i).id+1);
		}
		return id;
	}
	
	public static Korisnik find(int id) {
		for(int i=0; i<korisnici.lista.size(); i++) {
			if(korisnici.lista.get(i).id==id) {
				return korisnici.lista.get(i);
			}
		}
		System.out.print("neradi");
		return null;
	}
}
