package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utilidades.I18N;

public class AcercaDeController implements Initializable {

	@FXML
	private Label label_curso;

	@FXML
	private Label label_fecha;

	@FXML
	private Label label_nombre;

	@FXML
	private Label label_titulo;

	@FXML
	private Label label_version;

	@FXML
	private AnchorPane ventana_acercaDe;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		//Asignar idioma
		label_titulo.textProperty().bind(I18N.createStringBinding("form.titulo"));
		label_curso.textProperty().bind(I18N.createStringBinding("form.curso"));
		label_fecha.textProperty().bind(textoConcatenado);
		label_nombre.textProperty().bind(I18N.createStringBinding("form.creador"));
		label_version.textProperty().bind(I18N.createStringBinding("form.version"));

	}

	//Para obtener la fecha con el texto internacionalizado
	StringBinding fechaBinding = I18N.createStringBinding("form.fecha");

	StringBinding textoConcatenado = new StringBinding() {

		//el bloque de inicialización de instancia (dentro de las llaves {}) 
		//se ejecuta cada vez que se crea una nueva instancia de la clase StringBinding
		{
			super.bind(fechaBinding);
		}

		@Override
		protected String computeValue() {
			return fechaBinding.get() + ": " + obtenerFecha();

		}
	};

	private String obtenerFecha() {
		LocalDate a = LocalDate.now();
		return a.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	}



}
