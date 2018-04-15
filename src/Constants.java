import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Constants {

	public static final boolean DEBUG_ENABLED = true;
	public static final int GAP = 5;
	private static String key;

	public Constants() {
		try {
			key = Files.readAllLines(Paths.get("./APIKEY.txt")).get(0);
		} catch(Exception e) {
			if(DEBUG_ENABLED)
				e.printStackTrace();
			key = "";
		}
	}

	public String getKey() {
		return key;
	}

}
