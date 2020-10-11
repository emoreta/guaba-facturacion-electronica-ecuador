package extreme.media.test;

import java.io.IOException;

import com.extreme.media.commons.MoveFile;

public class TestMoveFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MoveFile mf=new MoveFile();
		try {
			mf.FileTranslate("C:\\XMLPRUEBA\\", "0607201801171892136200110010010000000191234567811_Sign.xml", "C:\\xampp\\htdocs\\AdminPanelMaster\\DocumentosDigitales\\Facturas\\");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
