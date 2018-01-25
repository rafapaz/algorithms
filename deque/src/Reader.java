import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Reader {
	String file;
	
	public Reader(String file) {
		this.file = file;
	}
	
	public ArrayList<String> readFromFileToListSpaceSeparated() {
		ArrayList<String> list = new ArrayList<String>();
		try {						
			Path path = Paths.get(this.file);		
			BufferedReader reader;
			reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);		
			
			String[] lines; 
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines = line.split(" ");
				for (String s : lines) {
					list.add(s);
				}
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return list;
	}
}
