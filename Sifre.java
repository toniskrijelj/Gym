package teretana;

import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

import javafx.util.Pair;

import java.util.Map;

public class Sifre {
	
	private static Map<String, Pair<String,Integer>> mapa;

	static {
		mapa = new TreeMap<String, Pair<String,Integer>>();
		ucitaj();
	}
	
	private static void ucitaj() {
		try {
			File f = new File(HelloApplication.loginPath);
			if(!f.exists()) return;
			
            Scanner fr = new Scanner(f);
            while(fr.hasNext()){
                String s = fr.nextLine();  
                try {
                	ucitaj(s);
                }
                catch(Exception e) {}
            }
            fr.close();
        } catch (Exception e) {}
	}
	
	private static void ucitaj(String s) {
		String[] str = s.split(";");
		mapa.put(str[0], new Pair<String,Integer>(str[1],Integer.parseInt(str[2])));
	}
	
	public static int login(String ime, String sifra) {
		if(!mapa.containsKey(ime)) return -1; 
		Pair<String,Integer> pair = mapa.get(ime);
		System.out.println(sifra + " " + pair.getKey());
		if(pair.getKey().equals(sifra)) return pair.getValue();
		return -1;
	}

}