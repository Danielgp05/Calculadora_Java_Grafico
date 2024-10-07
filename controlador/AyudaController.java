package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utilidades.I18N;

public class AyudaController implements Initializable{

	@FXML private Label combinacion_c;
	@FXML private Label combinacion_ce;
	@FXML private Label combinacion_m_mas;
	@FXML private Label combinacion_m_menos;
	@FXML private Label combinacion_mc;
	@FXML private Label combinacion_mr;
	@FXML private Label combinacion_ms;
	@FXML private Label info_c;
	@FXML private Label info_ce;
	@FXML private Label info_m_mas;
	@FXML private Label info_m_menos;
	@FXML private Label info_mc;
	@FXML private Label info_mr;
	@FXML private Label info_ms;
	@FXML private Label label_titulo;
	@FXML private Label titulo_c;
	@FXML private Label titulo_ce;
	@FXML private Label titulo_m_mas;
	@FXML private Label titulo_m_menos;
	@FXML private Label titulo_mc;
	@FXML private Label titulo_mr;
	@FXML private Label titulo_ms;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		//Titulos
		label_titulo.textProperty().bind(I18N.createStringBinding("form.ayuda"));
		titulo_mc.textProperty().bind(I18N.createStringBinding("form.titulo_mc"));
		titulo_mr.textProperty().bind(I18N.createStringBinding("form.titulo_mr"));
		titulo_ms.textProperty().bind(I18N.createStringBinding("form.titulo_ms"));
		titulo_ce.textProperty().bind(I18N.createStringBinding("form.titulo_ce"));
		titulo_c.textProperty().bind(I18N.createStringBinding("form.titulo_c"));
		
		//Descripcion
		info_mc.textProperty().bind(I18N.createStringBinding("form.info_mc"));
		info_mr.textProperty().bind(I18N.createStringBinding("form.info_mr"));
		info_ms.textProperty().bind(I18N.createStringBinding("form.info_ms"));
		info_m_mas.textProperty().bind(I18N.createStringBinding("form.info_m_mas"));
		info_m_menos.textProperty().bind(I18N.createStringBinding("form.info_m_menos"));
		info_ce.textProperty().bind(I18N.createStringBinding("form.info_ce"));
		info_c.textProperty().bind(I18N.createStringBinding("form.info_c"));

		//Combinacion de teclas
		combinacion_mc.textProperty().bind(I18N.createStringBinding("form.key_mc"));
		combinacion_mr.textProperty().bind(I18N.createStringBinding("form.key_mr"));
		combinacion_ms.textProperty().bind(I18N.createStringBinding("form.key_ms"));
		combinacion_m_mas.textProperty().bind(I18N.createStringBinding("form.key_m_mas"));
		combinacion_m_menos.textProperty().bind(I18N.createStringBinding("form.key_m_menos"));
		combinacion_ce.textProperty().bind(I18N.createStringBinding("form.key_ce"));
		combinacion_c.textProperty().bind(I18N.createStringBinding("form.key_c"));


	}
	


}
