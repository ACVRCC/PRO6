package rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name="GetLyricResult", namespace="http://api.chartlyrics.com/")
public class GetLyrics {


	@XmlElement(name="Lyric")
	private String lyricMusic;

	public String getLyric() {
		return lyricMusic;
	}

	public void setLyric(String lyric) {
		this.lyricMusic = lyric;
	}
	
	
}
