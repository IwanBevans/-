import java.io.File;
import java.util.Scanner;

public class FileAccess {

	public static Scanner readFile(String path) {
		try {
			File f = new File(path);
			Scanner in = new Scanner(f);
			return in;
		} catch (Exception e) {
		System.out.println("ERROR: File not found");
		//e.printStackTrace();
		return null;
		}
	}
}
