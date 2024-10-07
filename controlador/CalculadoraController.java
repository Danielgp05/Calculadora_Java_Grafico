package controlador;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import excepciones.DivisionPorCeroException;
import excepciones.RaizNegativaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Calculadora;
import utilidades.I18N;

public class CalculadoraController implements Initializable{

	@FXML private Button boton_0;
	@FXML private Button boton_1;
	@FXML private Button boton_2;
	@FXML private Button boton_3;
	@FXML private Button boton_4;
	@FXML private Button boton_5;
	@FXML private Button boton_6;
	@FXML private Button boton_7;
	@FXML private Button boton_8;
	@FXML private Button boton_9;
	@FXML private Button boton_borrar;
	@FXML private Button boton_c;
	@FXML private Button boton_ce;
	@FXML private Button boton_coma;
	@FXML private Button boton_division;
	@FXML private Button boton_igual;
	@FXML private Button boton_inversa;
	@FXML private Button boton_m_mas;
	@FXML private Button boton_m_menos;
	@FXML private Button boton_mc;
	@FXML private Button boton_mr;
	@FXML private Button boton_ms;
	@FXML private Button boton_multiplicar;
	@FXML private Button boton_porcentaje;
	@FXML private Button boton_pos_neg;
	@FXML private Button boton_raiz;
	@FXML private Button boton_restar;
	@FXML private Button boton_sumar;

	@FXML private Label label_info;
	@FXML private Label label_operacion;

	@FXML private MenuItem menu_AcercaDe;
	@FXML private MenuItem menu_Ayuda;
	@FXML private MenuItem menu_Copiar;
	@FXML private MenuItem menu_Historial;
	@FXML private MenuItem menu_Pegar;
	@FXML private MenuItem menu_Salir;	

	@FXML private MenuItem castellano;
	@FXML private MenuItem ingles; 
	@FXML private MenuItem valenciano;


	@FXML private Menu text_ver;
	@FXML private Menu text_edicion;
	@FXML private Menu text_ayuda;
	@FXML private Menu text_idioma;

	@FXML private ColorPicker cambiar_fondo;

	@FXML private AnchorPane fondo;

	Calculadora calculadora;

	//****************************************************************
	// Métodos de la clase CalculadoraController
	//****************************************************************

	void setIdioma(String idioma) {
		I18N.setLocale(new Locale(idioma));

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Crear la Calculadora
		calculadora = new Calculadora();

		//Cambiar color de fondo y de los colores
		cambiar_fondo.setOnAction(event -> {
			String colorHex = "#" + cambiar_fondo.getValue().toString().substring(2, 8);
			fondo.setStyle("-fx-background-color: " + colorHex + ";");

			//Cambiar color de los botones
			
			//Parsear a color el color de fondo para poder aclarar o oscurecer
			Color fondo = Color.web(colorHex);

			Color claro = fondo.brighter(); 
			Color oscuro = fondo.darker(); 

			//Si el fondo es claro
			if (claro.getBrightness() > 0.9) {
				colorHex = parsearColor(oscuro);

				boton_0.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_1.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_2.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_3.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_4.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_5.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_6.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_7.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_8.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_9.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_m_mas.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_m_menos.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_mr.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				boton_ms.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: black;");
				
			} else {
				
				//Si el fondo es oscuro
				colorHex = parsearColor(claro);

				boton_0.setStyle("-fx-background-color: " + colorHex + ";");
				boton_1.setStyle("-fx-background-color: " + colorHex + ";");
				boton_2.setStyle("-fx-background-color: " + colorHex + ";");
				boton_3.setStyle("-fx-background-color: " + colorHex + ";");
				boton_4.setStyle("-fx-background-color: " + colorHex + ";");
				boton_5.setStyle("-fx-background-color: " + colorHex + ";");
				boton_6.setStyle("-fx-background-color: " + colorHex + ";");
				boton_7.setStyle("-fx-background-color: " + colorHex + ";");
				boton_8.setStyle("-fx-background-color: " + colorHex + ";");
				boton_9.setStyle("-fx-background-color: " + colorHex + ";");
				boton_m_mas.setStyle("-fx-background-color: " + colorHex + ";");
				boton_m_menos.setStyle("-fx-background-color: " + colorHex + ";");
				boton_mr.setStyle("-fx-background-color: " + colorHex + ";");
				boton_ms.setStyle("-fx-background-color: " + colorHex + ";");
				
			}
			
			//Si el fondo es negro, los colores por defecto en el css
			if (fondo.equals(Color.rgb(0, 0, 0)) ) {
				boton_0.setStyle("");
				boton_1.setStyle("");
				boton_2.setStyle("");
				boton_3.setStyle("");
				boton_4.setStyle("");
				boton_5.setStyle("");
				boton_6.setStyle("");
				boton_7.setStyle("");
				boton_8.setStyle("");
				boton_9.setStyle("");
				boton_m_mas.setStyle("");
				boton_m_menos.setStyle("");
				boton_mr.setStyle("");
				boton_ms.setStyle("");
				
			}
			
		});

		//Asignar idioma
		text_ver.textProperty().bind(I18N.createStringBinding("form.ver"));
		menu_Historial.textProperty().bind(I18N.createStringBinding("form.historial"));
		menu_Salir.textProperty().bind(I18N.createStringBinding("form.salir"));

		text_edicion.textProperty().bind(I18N.createStringBinding("form.edicion"));
		menu_Copiar.textProperty().bind(I18N.createStringBinding("form.copiar"));
		menu_Pegar.textProperty().bind(I18N.createStringBinding("form.pegar"));

		text_ayuda.textProperty().bind(I18N.createStringBinding("form.ayuda"));

		menu_Ayuda.textProperty().bind(I18N.createStringBinding("form.verayuda"));
		menu_AcercaDe.textProperty().bind(I18N.createStringBinding("form.acercade"));

		text_idioma.textProperty().bind(I18N.createStringBinding("form.idioma"));
		castellano.textProperty().bind(I18N.createStringBinding("form.castellano"));
		valenciano.textProperty().bind(I18N.createStringBinding("form.valenciano"));
		ingles.textProperty().bind(I18N.createStringBinding("form.ingles"));

		// Asignar los Eventos de las opciones de menu
		menu_AcercaDe.setOnAction((event) -> mostrarVentana("/vista/AcercaDe.fxml", menu_AcercaDe.getText()));
		menu_Ayuda.setOnAction((event) -> mostrarVentana("/vista/Ayuda.fxml", menu_Ayuda.getText()));
		menu_Historial.setOnAction((event) -> mostrarVentana("/vista/Historial.fxml", menu_Historial.getText()));

		menu_Salir.setOnAction((event) -> salir());

		castellano.setOnAction((event) -> setIdioma("es"));
		valenciano.setOnAction((event) -> setIdioma("ca"));
		ingles.setOnAction((event) -> setIdioma("en"));

		// Asignar los Eventos a los botones
		boton_sumar.setOnMouseClicked((event) -> asignarOperacion("+"));
		boton_restar.setOnMouseClicked((event) -> asignarOperacion("-"));
		boton_multiplicar.setOnMouseClicked((event) -> asignarOperacion("*"));
		boton_division.setOnMouseClicked((event) -> asignarOperacion("/"));

		boton_raiz.setOnMouseClicked((event) -> raiz());
		boton_porcentaje.setOnMouseClicked((event) -> porcentaje());
		boton_inversa.setOnMouseClicked((event) -> inversa());

		boton_m_mas.setOnMouseClicked((event) -> memorySumar());
		boton_m_menos.setOnMouseClicked((event) -> memoryRestar());
		boton_mc.setOnMouseClicked((event) -> memoryClear());
		boton_mr.setOnMouseClicked((event) -> memoryRecall());
		boton_ms.setOnMouseClicked((event) -> memoryStorage());

		boton_igual.setOnMouseClicked((event) -> darIgual());

		boton_borrar.setOnMouseClicked((event) -> retroceder());

		boton_c.setOnMouseClicked((event) -> clear());
		boton_borrar.setOnMouseClicked((event) -> retroceder());
		boton_pos_neg.setOnMouseClicked((event) -> cambiarSigno());

		boton_0.setOnMouseClicked((event) -> insertarNumero("0"));
		boton_1.setOnMouseClicked((event) -> insertarNumero("1"));
		boton_2.setOnMouseClicked((event) -> insertarNumero("2"));
		boton_3.setOnMouseClicked((event) -> insertarNumero("3"));
		boton_4.setOnMouseClicked((event) -> insertarNumero("4"));
		boton_5.setOnMouseClicked((event) -> insertarNumero("5"));
		boton_6.setOnMouseClicked((event) -> insertarNumero("6"));
		boton_7.setOnMouseClicked((event) -> insertarNumero("7"));
		boton_8.setOnMouseClicked((event) -> insertarNumero("8"));
		boton_9.setOnMouseClicked((event) -> insertarNumero("9"));
		boton_coma.setOnMouseClicked((event) -> insertarNumero("."));

	}

	@FXML
	void pulsarTecla(KeyEvent event) {
		switch (event.getCode()) {
		case DIGIT0: case NUMPAD0: insertarNumero("0"); break;
		case DIGIT1: case NUMPAD1: insertarNumero("1"); break;
		case DIGIT2: case NUMPAD2: insertarNumero("2"); break;
		case DIGIT3: case NUMPAD3: insertarNumero("3"); break;
		case DIGIT4: case NUMPAD4: insertarNumero("4"); break;
		case DIGIT5: case NUMPAD5: insertarNumero("5"); break;
		case DIGIT6: case NUMPAD6: insertarNumero("6"); break;
		case DIGIT7: case NUMPAD7: insertarNumero("7"); break;
		case DIGIT8: case NUMPAD8: insertarNumero("8"); break;
		case DIGIT9: case NUMPAD9: insertarNumero("9"); break;
		case COMMA: insertarNumero("."); break;

		case PLUS: case ADD: asignarOperacion("+"); break;
		case MINUS: case SUBTRACT : asignarOperacion("-"); break;
		case MULTIPLY: asignarOperacion("*"); break;
		case DIVIDE: asignarOperacion("/"); break;
		case ENTER: case FINAL: calcular(); break;
		
		case BACK_SPACE: retroceder(); break;

		//CE
		case DELETE: clearError(); break;	

		//C
		case ESCAPE: clear();

		default: break;
		}

		//* DEL TECLADO
		KeyCombination multiplicar = new KeyCodeCombination(KeyCode.PLUS, KeyCodeCombination.SHIFT_DOWN);
		if (multiplicar.match(event)) asignarOperacion("*");

		// / del teclado
		KeyCombination dividir = new KeyCodeCombination(KeyCode.DIGIT7, KeyCodeCombination.SHIFT_DOWN);
		if (dividir.match(event)) asignarOperacion("/");

		//M+
		KeyCombination m_mas = new KeyCodeCombination(KeyCode.P, KeyCodeCombination.CONTROL_DOWN);
		if (m_mas.match(event)) memorySumar();

		//M-
		KeyCombination m_menos = new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.CONTROL_DOWN);
		if (m_menos.match(event)) memoryRestar();

		//MC
		KeyCombination memory_clear = new KeyCodeCombination(KeyCode.L, KeyCodeCombination.CONTROL_DOWN);
		if (memory_clear.match(event)) memoryClear();

		//MR
		KeyCombination memory_recall = new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN);
		if (memory_recall.match(event)) memoryRecall();

		//MS
		KeyCombination memory_storage = new KeyCodeCombination(KeyCode.M, KeyCodeCombination.CONTROL_DOWN);
		if (memory_storage.match(event)) memoryStorage();

	}

	private void insertarNumero(String numero){
		calculadora.insertarNumero(numero);
		mostrarInfo();

	}

	private void asignarOperacion(String operacion){

		boton_igual.setDisable(false);

		try {
			calculadora.asignarOperacion(operacion);
			mostrarInfo();
			mostrarOperacion();

		}catch (DivisionPorCeroException e) {
			label_info.setText(I18N.createStringBinding("form.dividir0").get());
			calculadora.clear();

		}

	}

	private void retroceder(){
		calculadora.retroceder();
		mostrarInfo();

	}

	private void cambiarSigno(){
		calculadora.cambiarSigno();
		mostrarInfo();

	}


	private void darIgual() {
		calcular();
		guardarFichero();
		calculadora.setCadenaOperacion(calculadora.getNumActual());

	}

	private void calcular() {
		try {
			calculadora.calcular(false);
			mostrarInfo();
			mostrarOperacion();
			//			guardarFichero();

		}catch (DivisionPorCeroException e) {
			label_info.setText(I18N.createStringBinding("form.dividir0").get());
			calculadora.clear();

		}


	}

	private void raiz() {
		try {
			boton_igual.setDisable(true);
			calculadora.raiz();
			mostrarInfo();
			mostrarOperacion();
			guardarFichero();
			calculadora.setCadenaOperacion("");

		}catch (RaizNegativaException e) {
			label_info.setText(I18N.createStringBinding("form.raiz").get());
			calculadora.clear();

		}
	}

	private void porcentaje() {
		try {
			boton_igual.setDisable(true);
			calculadora.porcentaje();
			mostrarInfo();
			mostrarOperacion();
			guardarFichero();
			calculadora.setCadenaOperacion("");

		}catch (DivisionPorCeroException e) {
			label_info.setText(I18N.createStringBinding("form.dividir0").get());
			calculadora.clear();

		}
	}

	private void inversa() {
		try {
			boton_igual.setDisable(true);
			calculadora.inversa();
			mostrarInfo();
			guardarFichero();
			calculadora.setCadenaOperacion(calculadora.getNumActual());

		}catch (DivisionPorCeroException e) {
			label_info.setText(I18N.createStringBinding("form.dividir0").get());
			calculadora.clear();

		}

	}

	private void clearError() {
		calculadora.clearError();
		mostrarInfo();

	}

	private void clear() {
		boton_igual.setDisable(false);
		calculadora.clear();
		mostrarInfo();
		//		label_operacion.setText("");
		//		calculadora.setCadenaOperacion("");

	}

	//**************************************************
	// INFORMACION
	//**************************************************
	private void mostrarInfo() {
		label_info.setText(mostrarMillares(calculadora.getNumActual()));
		mostrarOperacion();

	}

	private void mostrarOperacion(){
		label_operacion.setText(calculadora.getCadenaOperacion());

	}

	//**************************************************
	// BOTONES DE MEMORIA
	//**************************************************

	private void memoryClear() { 
		calculadora.memoryClear(); 

	}

	private void memoryStorage() { 
		calculadora.memoryStorage(); 

	}

	private void memoryRecall() {
		calculadora.memoryRecall();
		mostrarInfo();

	}

	private void memorySumar() { 
		calculadora.memorySumar(); 

	}

	private void memoryRestar() { 
		calculadora.memoryRestar(); 

	}

	//**************************************************
	// OPCIONES PARA LOS MENUS
	//**************************************************

	//Mostrar otra ventana
	private void mostrarVentana(String rutaFXML, String titulo) {
		try{
			//Léeme el source del archivo que te digo fxml y te pongo el path
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
			Parent root = (Parent) fxmlLoader.load();

			//Creame un nuevo Stage (una nueva ventana vacía)
			Stage stage = new Stage();

			//Asignar al Stage la escena que anteriormente hemos leído y guardado en root
			stage.setTitle(titulo);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));

			stage.getIcons().add(new Image(getClass().getResource("/vista/img/info.jpeg").toExternalForm()));

			//Mostrar el Stage (ventana)
			stage.show();

		}
		catch (Exception e){
			e.printStackTrace();

		}
	}

	private void salir() {
		Stage stage = (Stage)menu_Salir.getParentPopup().getOwnerWindow();
		stage.close();

	}

	public String mostrarMillares(String numActual) {
		try {

			DecimalFormatSymbols simbol = new DecimalFormatSymbols();

			simbol.setDecimalSeparator(',');
			simbol.setGroupingSeparator('.');

			DecimalFormat formato = new DecimalFormat("#,###.###################", simbol);
			BigDecimal b = new BigDecimal(numActual);
			numActual = formato.format(b);

		} catch (NumberFormatException e) {
			return calculadora.getNumActual();

		}

		return numActual;
	}

	public void guardarFichero() {

		try {

			FileWriter file = new FileWriter("src/Historial.txt", true);
			PrintWriter pw = new PrintWriter(file); 

			LocalTime hora_Actual = LocalTime.now(); 
			DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

			pw.print( "[" + String.valueOf(hora_Actual.format(format)) + "] " +label_operacion.getText() + " = " + calculadora.getNumActual());
			pw.println();

			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}

	private String parsearColor(Color color) {
		return String.format("#%02X%02X%02X",
				(int) (color.getRed() * 255),
				(int) (color.getGreen() * 255),
				(int) (color.getBlue() * 255));
	}

}