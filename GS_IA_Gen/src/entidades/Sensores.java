package entidades;

public class Sensores {
	private float ph_MIN = 0;
	private float ph_MAX = 14;
	private int umidade_MIN = 30;
	private int umidade_MAX = 70;
	private int metano_MIN = 100;
	private int metano_MAX = 5000;
	
	private double coordenadasX;
	private double coordenadasY;
	private double coordenadasSoma;
	
	private float ph;
	private int umidade;
	private int metano;
	
	public Sensores(float ph, int umidade, int metano, double coordenadasX, double coordenadasY, double coordenadasSoma) {
		this.ph = ph;
		this.umidade = umidade;
		this.metano = metano;
		this.coordenadasX = coordenadasX;
		this.coordenadasY = coordenadasY;
		this.coordenadasSoma = coordenadasSoma;
	}

	public float getPh_MIN() {
		return ph_MIN;
	}

	public float getPh_MAX() {
		return ph_MAX;
	}

	public int getUmidade_MIN() {
		return umidade_MIN;
	}

	public int getUmidade_MAX() {
		return umidade_MAX;
	}

	public int getMetano_MIN() {
		return metano_MIN;
	}

	public int getMetano_MAX() {
		return metano_MAX;
	}

	public float getPh() {
		return ph;
	}

	public int getUmidade() {
		return umidade;
	}

	public int getMetano() {
		return metano;
	}
	
	public double getCoordenadasX() {
		return coordenadasX;
	}

	public double getCoordenadasY() {
		return coordenadasY;
	}
	
	public double getCoordenadasSoma() {
		return coordenadasSoma;
	}

	@Override
	public String toString() {
		String aux = "";
		aux += "Coordenadas: " + getCoordenadasX() + ", " + getCoordenadasY() + ". \n";
		aux += "PH: " + getPh() + ". \n";
		aux += "Umidade do solo: " + getUmidade() + " v/v. \n";
		aux += "Metano: " + getMetano() + " ppm. \n";
		return aux;
	}

}
