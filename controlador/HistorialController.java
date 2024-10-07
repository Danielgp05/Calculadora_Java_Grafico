package controlador;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import utilidades.I18N;

public class HistorialController implements Initializable{

	@FXML
	private Label label_titulo;

	@FXML
	private TextArea operaciones;
	
	public void initialize(URL url, ResourceBundle resourceBundle) {

		//Titulos
		label_titulo.textProperty().bind(I18N.createStringBinding("form.historial"));
		operaciones.setText(lineasHistorial());

	}


	public String lineasHistorial() {

		String linea;

		//Sacado de stackOverfloow
		StringBuilder contenido = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader("src/Historial.txt"))) {
			while ((linea = br.readLine()) != null) {
				contenido.append(linea).append("\n");

			}

		} catch (IOException e) {
			e.printStackTrace();

		}

		return contenido.toString();
	}

}
