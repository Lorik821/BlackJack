package client.metier;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Musique 
{
	private static String chemin = System.getProperty("user.dir");
	
	private Son musiqueAmbiance, bruitAmbiance, petitBruit;
	private int dureeMA, dureeBA;
	
	private Son bruitDeCarte;
	
	private Son fanfare;
	
	private Thread tMA, tBA;
	
	public Musique ()
	{
		musiqueAmbiance = new Son (chemin + "/ressources/musiques/Casino Ambiance Music.wav");
		bruitDeCarte = new Son (chemin + "/ressources/musiques/Bruit Carte.wav");
		bruitAmbiance = new Son (chemin + "/ressources/musiques/demo_casino_ambi_machines_01.wav");
		petitBruit = new Son (chemin + "/ressources/musiques/PetitBruit.wav");
		fanfare = new Son (chemin + "/ressources/musiques/BigFanfare.wav");
		
		try{
			String chaine = "";
			InputStream ips=new FileInputStream(chemin + "/ressources/musiques/Casino Ambiance Music.data"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne;
			}
			br.close();
			dureeMA = Integer.parseInt (chaine);
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		try{
			String chaine = "";
			InputStream ips=new FileInputStream(chemin + "/ressources/musiques/demo_casino_ambi_machines_01.data"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne;
			}
			br.close();
			dureeBA = Integer.parseInt (chaine);
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void playFanfare () { fanfare.play(); }
	
	public void playMusiqueAmbiance ()
	{
		tMA = new Thread() {
			public void run () {
				while (true){
					musiqueAmbiance.play();
					try { Thread.sleep(dureeMA); }
					catch (InterruptedException e) {e.printStackTrace();}
					musiqueAmbiance.stop();
				}
			}
		};
		tMA.start();
	}
	
	public void playBruitAmbiance ()
	{
		tBA = new Thread() {
			public void run () {
				while (true){
					bruitAmbiance.play();
					try { Thread.sleep(dureeBA); }
					catch (InterruptedException e) {}
					bruitAmbiance.stop();
				}
			}
		};
		tBA.start();
	}
	
	public void playBruitDeCarte () { bruitDeCarte.play(); }
	
	public void stopMusqiueAmbiance ()
	{
		musiqueAmbiance.stop();
		tMA.stop();
	}
	
	public void stopBruitAmbiance ()
	{
		bruitAmbiance.stop();
		tBA.stop();
	}
	
	public void playPetitBruit() { petitBruit.play(); }
}
