package teretana;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;

public class Korisnici {
	private static final Korisnici korisnici = new Korisnici();
	
	public static ArrayList<Korisnik> lista;
	
	private Korisnici() {
		lista = new ArrayList<>();
		ucitaj();
	}
	
	private void ucitaj() {
		try {
			File f = new File(HelloApplication.path);
			if(!f.exists()) return;
			
            Scanner fr = new Scanner(f);
            while(fr.hasNext()){
                String s = fr.nextLine();  
                try {
                	lista.add(ucitaj(s));
                }
                catch(Exception e) {
                	/*preskacemo*/
                }
            }
            fr.close();
        } catch (Exception e){
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("GRESKA!");
            alert.setHeaderText("NEUSPESNO CITANJE IZ FILA\nPOZOVI 061 276 8858 TONI");
            alert.show();
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
		return new Korisnik(id, ime, prezime, dolazak, new Clanarina(LocalDate.parse(datumClanarine), tipClanarine, brojTreninga));
	}
	
	private void ispisi() {
        StringBuilder postojeci= new StringBuilder();
        for(int i=0; i<lista.size(); i++) {
            postojeci.append(ispisi(lista.get(i)));
        	if(i+1 < lista.size()) postojeci.append('\n');
        }
        try{
        	File folder = new File(HelloApplication.folderPath);
        	folder.mkdir();
        	
        	File f2 = new File(HelloApplication.path2);
        	if(f2.exists()) f2.delete();
        	
        	File f = new File(HelloApplication.path);
        	if(!f.exists()) f.createNewFile();
        	
        	File tmp = new File(HelloApplication.tempPath);
        	if(!tmp.exists())  tmp.createNewFile();
        	
        	FileWriter fw = new FileWriter(HelloApplication.tempPath);
        	fw.write(postojeci.toString());
        	fw.close();
        	
        	if(!f.renameTo(f2) || !tmp.renameTo(f)) {
        		Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("GRESKA!");
                alert.setHeaderText("NEUSPESNO MENJANJE IME FAJLA\nPOZOVI 061 276 8858 TONI");
                alert.show();
        	}
        	
        	File backup = new File(HelloApplication.backupPath);
        	if(backup.exists()) {
        		String backupFile = LocalDate.now().toString() + "-" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + ".txt";
        		Files.copy(
        				Path.of(HelloApplication.path), 
        				Path.of(HelloApplication.backupPath + backupFile), 
        				StandardCopyOption.REPLACE_EXISTING);
        	}
        }
        catch(Exception e) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("GRESKA!");
            alert.setHeaderText("NEUSPESNO PISANJE U FILE\nPOZOVI 061 276 8858 TONI");
            alert.setContentText("POKUSAJ PONOVO\n" + e.toString());
            alert.show();
        }
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
	
	public static void save() {
		korisnici.ispisi();
	}
	
	public static int dodaj(String ime, String prezime) {
		int id=find_id();
		lista.add(new Korisnik(id,ime,prezime));
		save();
		return id;
	}
	
	private static int find_id() {
		int id=0;
		for(int i=0; i<lista.size(); i++) {
			id=Math.max(id, lista.get(i).id+1);
		}
		return id;
	}
}
