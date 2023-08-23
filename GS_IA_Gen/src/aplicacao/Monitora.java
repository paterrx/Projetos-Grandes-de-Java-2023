package aplicacao;

import java.util.Random;
import java.util.Scanner;

public class Monitora {
	public static final float PH_MIN = 0;
	public static final float PH_MAX = 14;
	public static final int UMIDADE_MIN = 30;
	public static final int UMIDADE_MAX = 70;
	private int metano_MIN = 100;
	private int metano_MAX = 5000;

	private double coordenadasXSensores[] = {4755.44, 4755.42, 4755.39, 4755.44, 4755.40, 4755.32, 4755.28, 4755.28, 4755.33};
	private double coordenadasYSensores[] = {2100.34, 2100.355, 2100.37, 2100.39, 2100.40, 2100.34, 2100.34, 2100.38, 2100.39};
	private int n = coordenadasYSensores.length;
	private int umidadesSensores[] = new int [n];
	private float phSensores[] = new float [n];
	private int metanoSensores[] = new int [n];
	
	public double[] getCoordenadasXSensores() {
		return coordenadasXSensores;
	}

	public double[] getCoordenadasYSensores() {
		return coordenadasYSensores;
	}
	
	public int getN() {
		return n;
	}

	public int[] getUmidadesSensores() {
		return umidadesSensores;
	}

	public float[] getPhSensores() {
		return phSensores;
	}
	
	public int[] getMetanoSensores() {
		return metanoSensores;
	}

	public void preencherUmidade () {
		Random gerador = new Random();
		for (int i = 0; i < n; i++) {
			int u = gerador.nextInt(UMIDADE_MAX) + UMIDADE_MIN;
			umidadesSensores[i] = u;
		}
	}
	
	public void preencherPh () {
		Random gerador = new Random();
		for (int i = 0; i < n; i++) {
			float v = PH_MIN + (3 * ((float) gerador.nextInt((int)PH_MAX-3)));
			phSensores[i] = v;
		}
	}
	
	public void preencherMetano () {
		Random gerador = new Random();
		for (int i = 0; i < n; i++) {
			int m = gerador.nextInt(metano_MAX) + metano_MIN*10;
			metanoSensores[i] = m;
		}
	}

}