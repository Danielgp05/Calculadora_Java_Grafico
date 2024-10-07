package excepciones;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class DivisionPorCeroException extends Exception  implements Initializable {

	private static final long serialVersionUID = 1L;

	public String error() {
		return "División por 0";
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
	}

	
}