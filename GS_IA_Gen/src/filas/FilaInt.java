package filas;

import entidades.Sensores;

public class FilaInt {

	private class NO {
		Sensores dado;
		NO prox;
	}
	
	NO ini, fim;

	public void init() {
		ini = fim = null;
	}
	
	public boolean isEmpty() {
		if (ini == null && fim == null)
			return true;
		return false;
	}
	
	public void enqueue(Sensores elem) {
		NO novo = new NO();
		novo.dado = elem;
		if (isEmpty())
			ini = novo;
		else
			fim.prox = novo;
		fim = novo;
	}
	
	public Sensores dequeue() {
		Sensores v = ini.dado;
		ini = ini.prox;
		if (ini == null)
			fim = null;
		return v;
	}
	
	public Sensores first() {
		return (ini.dado);
	}
	
	public Sensores pesquisarSensorInadequado(double coodernadasSoma) {
        NO aux = ini;
        Sensores aux2 = null;
        while (aux != null) {
            if (aux.dado.getCoordenadasSoma() == coodernadasSoma) {
                aux2 = aux.dado;
                break;
            }
            aux = aux.prox;
        }
        return aux2;
    }
	
	public String apresentarSetores () {
		NO aux = ini;
		String auxSensor = "Fila dos sensores inadequados: \n";
        while (aux != null) {
        	auxSensor += aux.dado + "\n";
            aux = aux.prox;
        }
        return auxSensor;
	}
	
	public void removerSensoresInadequados(double coordenadasSoma) {
        NO anterior = null;
        NO atual = ini;

        while (atual != null) {
            if (atual.dado.getCoordenadasSoma() == coordenadasSoma) {
                if (anterior == null) {
                    ini = atual.prox;
                    if (ini == null) {
                        fim = null;
                    }
                } else {
                    anterior.prox = atual.prox;
                    if (atual.prox == null) {
                        fim = anterior;
                    }
                }
                return;
            }

            anterior = atual;
            atual = atual.prox;
        }
    }
	
}
