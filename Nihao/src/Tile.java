import java.awt.Button;
import java.applet.*;

public class Tile extends Button{
//this code codes for the three fields tiles with words,
// the phrases..the phrases! shesays

	private String eWord;
	private String cWord;
	private AudioClip aFile;


	public Tile(String eWord, String cWord, AudioClip aFile){
		this.eWord = eWord;
		this.cWord = cWord;
		this.aFile = aFile;
		setLabel(eWord);
	}
	
	public String getE(){
		return eWord;
	}
	
	public String getC(){
		return cWord;
	}
	
	public AudioClip getAudio(){
		return aFile;
	}
}
