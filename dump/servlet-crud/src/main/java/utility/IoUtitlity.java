package utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class IoUtitlity {

	public static void uploadImage(Part part, String location) throws IOException {

		try (InputStream is = part.getInputStream(); FileOutputStream fStream = new FileOutputStream(location)) {
			byte[] image = new byte[is.available()];
			is.read(image);
			fStream.write(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
