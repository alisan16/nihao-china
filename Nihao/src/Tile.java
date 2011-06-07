import java.awt.Button;
import java.applet.*;

public class Tile extends Button{
//this code codes for Tile, which all the phrases are represented as
//Three fields: phrase in English, phrase in Chinese, and Audio Clip of phrase in both languages
	private String eWord;
	private String cWord;
	private AudioClip aFile;


	public Tile(String eWord, String cWord, AudioClip aFile){
		this.eWord = eWord;
		this.cWord = cWord;
		this.aFile = aFile;
		setLabel(eWord);
	}
	
	//Switches displayed phrase to English if Chinese, and vice versa
	public void switchLabel(){
		if(getLabel() == eWord){
			setLabel(cWord);
		} else if(getLabel() == cWord){
			setLabel(eWord);
		}
	}
	
	//Resets the displayed phrase to English
	//not currently used
	public void reset(){
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
