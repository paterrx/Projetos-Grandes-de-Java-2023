package listas;

import entidades.Sensores;
import filas.FilaInt;

public class ListaIntCrescente {
	private int qtdInserida;
	
	private class NO {
		Sensores dado;
		NO prox;
	}

	private NO lista = null;

	public void insere(Sensores elem) {
		NO novo = new NO();
		novo.dado = elem;
		if (lista == null) {
			novo.prox = null;
			lista = novo;
		} else {
			if (novo.dado.getCoordenadasSoma() < lista.dado.getCoordenadasSoma()) {
				novo.prox = lista;
				lista = novo;
			} else {
				NO aux = lista;
				boolean achou = false;
				while (aux.prox != null && !achou) {
					if (aux.prox.dado.getCoordenadasSoma() < novo.dado.getCoordenadasSoma())
						aux = aux.prox;
					else
						achou = true;
				}
				novo.prox = aux.prox;
				aux.prox = novo;
			}
		}
		qtdInserida++;
	}

	public boolean remove(double valor) {
		boolean achou = false;
		if (lista != null) {
			if (valor == lista.dado.getCoordenadasSoma()) {
				achou = true;
				lista = lista.prox;
			} else {
				NO aux = lista;
				while (aux.prox != null && !achou) {
					if (aux.prox.dado.getCoordenadasSoma() != valor)
						aux = aux.prox;
					else {
						achou = true;
						aux.prox = aux.prox.prox;
					}
				}
			}
		}
		qtdInserida--;
		return achou;
	}
	
	public int contaNos() {
		int cont=0;
		NO aux = lista;
		while (aux!=null) {
			cont++;
			aux = aux.prox;
		}
		return cont;
	}
	
	public String apresenta() {
		NO aux = lista;
		String auxSensor = "Lista dos Sensores armazenados: \n";
		while (aux != null) {
			auxSensor += aux.dado + "\n";
			aux = aux.prox;
		}
		return auxSensor;
	}
	
	public Sensores apresentaUm(double coordenadasSoma) {
		NO aux = lista;
		while (aux.dado.getCoordenadasSoma() != coordenadasSoma) {
			aux = aux.prox;
		}
		return aux.dado;
	}
	
	public int getQtdInserida() {
		return qtdInserida;
	}

	public double pegarCoordenadasSoma(int i) {
		NO aux = lista;
		double auxCord;
		for (int j = 0; j < i; j++) {
			aux = aux.prox;
		}
		auxCord = aux.dado.getCoordenadasSoma();
		return auxCord;
	}
	
	public void detectarInadequados(FilaInt filaIntSensores) {
	    NO aux = lista;
	    NO anterior = null;
	    
	    while (aux != null) {
	        int qtdInadequada = 0;
	        
	        if (aux.dado.getPh() > aux.dado.getPh_MAX() || aux.dado.getPh() < aux.dado.getPh_MIN()) {
	            qtdInadequada++;
	        }
	        
	        if (aux.dado.getUmidade() > aux.dado.getUmidade_MAX() || aux.dado.getUmidade() < aux.dado.getUmidade_MIN()) {
	            qtdInadequada++;
	        }
	        
	        if (aux.dado.getMetano() > aux.dado.getMetano_MAX() || aux.dado.getMetano() < aux.dado.getMetano_MIN()) {
	            qtdInadequada++;
	        }
	        
	        if (qtdInadequada >= 2) {
	            filaIntSensores.enqueue(aux.dado);
	            if (anterior == null) {
	                lista = aux.prox;
	                aux = lista; 
	            } else {
	                anterior.prox = aux.prox; 
	                aux = aux.prox;
	            }
	            qtdInserida--; 
	        } else {
	            anterior = aux; 
	            aux = aux.prox; 
	        }
	    }
	}


}

