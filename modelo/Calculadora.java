package modelo;

import java.math.BigDecimal;
import java.math.MathContext;

import excepciones.*;

public class Calculadora {

	//Atributos
	private double num1;
	private double num2;
	private String operacion;
	private String numActual;
	private String numMemoria;
	private String cadenaOperacion;

	//Constructor
	public Calculadora() {
		this.num1 = 0;
		this.num2 = 0;
		this.numActual = "0";
		this.numMemoria = "0";
		this.operacion = "";
		this.cadenaOperacion = "";

	}

	//Getters and Setters
	//	public double getNum1() {
	//		return num1;
	//	}
	//
	//	public double getNum2() {
	//		return num2;
	//	}
	//
	//	public String getOperacion() {
	//		return operacion;
	//	}

	public String getCadenaOperacion() {
		return cadenaOperacion;
	}

	public void setCadenaOperacion(String cadenaOperacion) {
		this.cadenaOperacion = cadenaOperacion;
	}

	public String getNumActual() {
		return numActual;
	}

	public String getNumMemoria() {
		return numMemoria;
	}

	//Operaciones aritméticas
	public double sumar(){
		//return this.num1 + this.num2;
		BigDecimal B1 = BigDecimal.valueOf(this.num1);
		BigDecimal B2 = BigDecimal.valueOf(this.num2);
		return B1.add(B2).doubleValue();

	}

	public double restar(){
		BigDecimal B1 = BigDecimal.valueOf(this.num1);
		BigDecimal B2 = BigDecimal.valueOf(this.num2);
		return B1.subtract(B2).doubleValue();

	}

	public double multiplicar(){
		BigDecimal B1 = BigDecimal.valueOf(this.num1);
		BigDecimal B2 = BigDecimal.valueOf(this.num2);
		return B1.multiply(B2).doubleValue();

	}

	public double dividir() throws DivisionPorCeroException{

		BigDecimal B1 = BigDecimal.valueOf(this.num1);
		BigDecimal B2 = BigDecimal.valueOf(this.num2);

		if(this.num1 == 0 || this.num2 == 0) {
			throw new DivisionPorCeroException();

		}else {
			return B1.divide(B2, MathContext.DECIMAL128).doubleValue();
			//MathContext.DECIMAL128: para limitar entre 32, 64 o 128 bits

		}

	}

	public void porcentaje() throws DivisionPorCeroException{
		double numero = Double.parseDouble(this.numActual);
		double porcentaje_decimal = 0;

		//Mostrar las operaciones realizadas
		this.cadenaOperacion += this.numActual + "%"; 

		//  "80 + 20%", estás aumentando el 80% de 80 en un 20%. Para calcularlo, primero necesitas
		// convertir el 20% en un número decimal, que es 0.20. Luego, multiplicas 80 por 0.20, lo que resulta en 16. 

		if(numero <= 0) {
			throw new DivisionPorCeroException();

		}else {
			try {

				//Si solo hay un numero y no hay operacion pendiente
				if(this.num1 == 0 && this.num2 == 0) {
					this.num2 = numero/100;
					this.numActual = String.valueOf(numero/100);

				}else {
					//Calcular el tanto porciento para despues realizar la operacion
					porcentaje_decimal = numero / 100;

					if(porcentaje_decimal <= 0) {
						throw new DivisionPorCeroException();

					}else {
						if(!this.operacion.equals("/")) {
							this.numActual = quitarCerosYComas(String.valueOf(num1 * porcentaje_decimal));

						}else {
							this.numActual = quitarCerosYComas(String.valueOf(num1 / porcentaje_decimal));

						}

					}

					//Para q funcione el test
					if(this.operacion.equals("+") || this.operacion.equals("-")) {
						calcular(false);

					}

				}

			}catch (NumberFormatException e) {
				throw new DivisionPorCeroException();

			}

		}

	}

	public double raiz() throws RaizNegativaException{
		double resultado = 0;
		String num = "0";

		//Mostrar las operaciones realizadas
		if(this.operacion.equals("")) {
			this.cadenaOperacion = "√" + this.numActual; 

		}else {
			this.cadenaOperacion += " √" + this.numActual; 
			
		}
		
		try {
			resultado =  Math.sqrt(Double.parseDouble(this.numActual));
			num = quitarCerosYComas(String.valueOf(resultado));

		}catch(NumberFormatException e) {
			this.numActual = "0";

		}

		if(num.equals("NaN")) {
			resultado = 0;
			this.numActual = "0";
			throw new RaizNegativaException();

		}else {
			this.numActual = num;

		}
		this.num1 = 0;

		return resultado;

	}

	public double inversa() throws DivisionPorCeroException{

		double numero = 0;
		try {
			numero = Double.parseDouble(this.numActual);

			if(numero == 0) {
				throw new DivisionPorCeroException();

			}
			//Mostrar operacion realizada
			this.cadenaOperacion += "1/" + quitarCerosYComas(this.numActual);
			this.numActual = String.valueOf(1/numero);

		}catch(NumberFormatException e) {
			throw new DivisionPorCeroException();

		}

		return 1/numero;
	}

	//Cambiar signo al valor actual
	public void cambiarSigno(){

		//Si es cero no puede ser negativo
		if(!this.numActual.equals("0")) {
			//Si no contiene un menos se lo añade
			if(!this.numActual.contains("-")) {
				this.numActual = "-" + this.numActual;

			}else {
				this.numActual = this.numActual.substring(1);

			}		
		}

	}

	//	//Resetear todas las variables
	//	public void reset(){
	//		//reset Calculadora
	//
	//	}
	//
	//	//Concatenar número al numActual
	//	public String concatenar(String numero){
	//		// …
	//
	//	}

	public void retroceder(){
		this.numActual = this.numActual.substring(0, this.numActual.length()-1);

		if(this.numActual.equals("-")) {
			this.numActual = "0";

		}

		if(this.numActual.equals("")) {
			this.numActual = "0";

		}
	}



	public void asignarOperacion(String operacion) throws DivisionPorCeroException{

		//Para poner un numero negativo
		if(this.numActual.equals("0") && operacion.equals("-")) {
			this.numActual = "-";

		}else {
			
			if(!this.operacion.equals("")) {
				calcular(true);

			}

			//Si numActual es solo -
			try {
				this.operacion = operacion;

				//Mostrar las operaciones realizadas
				//TODO acabar historial

				if(this.num1 == 0) {
					this.cadenaOperacion += quitarCerosYComas(this.numActual) + " " + this.operacion;

				}else {
					this.cadenaOperacion += " " + this.operacion + " ";

				}



				this.num1 = Double.parseDouble(this.numActual);
				this.numActual = "0";

			}catch (NumberFormatException e) {
				this.numActual = "0";

			}

		}

	}

	public void calcular(boolean porcentaje) throws DivisionPorCeroException{

		//Si numActual es solo -
		try {
			this.num2 = Double.parseDouble(this.numActual);

		}catch (NumberFormatException e) {
			this.numActual = "-";

		}

		double resultado = 0;

		switch (this.operacion) {
		case "+": resultado = sumar(); break;
		case "-": resultado = restar(); break;
		case "*": resultado = multiplicar(); break;
		case "/": resultado = dividir(); break;
		default: resultado = sumar(); break;
		}

		this.numActual = quitarCerosYComas(String.valueOf(resultado));
		this.operacion = "";

		//Mostrar las operaciones realizadas
		this.cadenaOperacion += " " + quitarCerosYComas(String.valueOf(this.num2));

	}

	public void clear() {
		this.num1 = 0;
		this.num2 = 0;
		this.numActual = "0";
		this.operacion = "";
		this.numMemoria = "0";
		this.cadenaOperacion = "";

	}

	public void clearError() {
		this.numActual = "0";

	}

	public void memoryClear() {
		this.numMemoria = "0";

	}

	public void memoryStorage() {
		this.numMemoria = this.numActual;

	}

	public void memoryRecall() {
		this.numActual = this.numMemoria;

	}

	public void memorySumar() {
		double memoria = Double.parseDouble(this.numMemoria);
		double actual = Double.parseDouble(this.numActual);

		this.numMemoria = quitarCerosYComas(String.valueOf( memoria + actual));

	}

	public void memoryRestar() {
		double memoria = Double.parseDouble(this.numMemoria);
		double actual = Double.parseDouble(this.numActual);

		this.numMemoria = quitarCerosYComas(String.valueOf(memoria - actual));
	}

	//FUNCIONES PARA EL CONTROLADOR
	public void insertarNumero(String numero) {

		//Si es una coma
		if(numero.equals(".")) {

			//Si hace -. que ponga -0.
			if(this.numActual.equals("-")) {
				this.numActual = "-0.";

			}

			//Si es 0 que ponga 0.
			if (this.numActual.equals("0")) {
				this.numActual = "0.";

			}

			//Si no contiene una coma q la ponga
			if (!this.numActual.contains(".")) {

				this.numActual += ".";
			}

			//Si los numeros no estan vacios es que ya se ha hecho una operacion y insertas una coma es 0.
			if(this.num1 != 0 && this.num2 != 0 && numero.equals(".")) {
				this.numActual = "0.";

			}

		}else {

			//Por ejemplo -0,3
			if(this.numActual.contains("-") && this.numActual.contains(".")) {
				this.numActual += numero;

			}else {
				//Si es -0005, quita los ceros a la izquierda y a la derecha
				BigDecimal num2 = new BigDecimal(numero);

				//Si el numero introducido y el actual son 0
				if(num2.equals(BigDecimal.ZERO) && this.numActual.equals("0")) {

					//Si la primera posicion es - no borrar	
				}else if(this.numActual.charAt(0) == '-') {

					//Para poder -30 por ejemplo
					if(numero.equals("0") && !this.numActual.equals("-0")) {
						this.numActual = this.numActual + numero;

					}else if(numero.equals("0") || this.numActual.equals("-0")){

						//Para en el caso anterior guardar solo -5
						this.numActual = "-" + numero;

					}else {

						//Para poder borrar y añadir numeros a un numero negativo
						this.numActual += numero;

					}

				}else {

					//Juntas los dos numeros
					try {
						num2 = new BigDecimal(this.numActual + numero);
						this.numActual = String.valueOf(num2);

					}catch(NumberFormatException e) {


					}

				}	

			}

		}

	}

	public String quitarCerosYComas(String numActual) {

		double a = Double.parseDouble(numActual);
		int num = (int) a;

		if(num == a) {
			num = (int) a;
			return String.valueOf(num);

		}else {
			return numActual;

		}

	}

}
