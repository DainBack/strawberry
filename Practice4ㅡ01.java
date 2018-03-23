class Song{
	String title;
	String artist;
	String album;
	String composer;
	int year;
	int track;
	
	public Song() { }
	public Song(String title, String artist, String album, String composer, int year, int track) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.composer = composer;
		this.year = year;
		this.track = track;
	}
	public void Song() {
		title = null;
		artist = null;
		album = null;
		composer = null;
		year = 0;
		track = 0;
	}
	
	public void show() {
		System.out.println("Song's Name : "+ title);
		System.out.println("Artist : "+artist);
		System.out.println("Album's Name : "+album);
		System.out.println("Composer : "+composer);
		System.out.println("Issue year : "+year);
		System.out.println("Track : "+track);
	}
}
public class Practice4คั01 {
	public static void main(String args[]) {
		Song aSong = new Song("Dancing Queen", "ABBA", "Happy", "james", 1987, 2);
		aSong.show();
		aSong.Song();
		aSong.show();
	}
}
