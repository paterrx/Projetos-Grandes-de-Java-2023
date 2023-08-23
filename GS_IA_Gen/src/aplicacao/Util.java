package aplicacao;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import entidades.Sensores;
import filas.FilaInt;
import listas.ListaIntCrescente;

public class Util {
	private ListaIntCrescente listaIntSensores = new ListaIntCrescente();
    private FilaInt filaIntSensores = new FilaInt();
    private boolean adicionou = false;
   
	public void menu() {
    	int opcao = 0;
    	
    	do {
			try {
				opcao = parseInt(showInputDialog(gerarMenu()));
				if (opcao < 1 || opcao > 10) {
					showMessageDialog(null, "Opcao invalida. ");
				} else {
					switch (opcao) {
						case 1:
							inserirSensor();
							break;
						case 2:
							pesquisarSensor();
							break;
						case 3:
							visualizarSensores();
							break;
						case 4:
							removerSensor();
							break;
						case 5:
							adicionarSensoresMocado();
							break;
						case 6:
							verificarSensoresInadequados();
							break;
						case 7:
							pesquisarSensorInadequado();
							break;
						case 8:
							visualizarSensoresInadequados();
							break;
						case 9:
							removerSensorInadequado();
							break;
						case 10:
							break;
					}
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "Voce deve digitar um numero. \n" + "Erro: " + e);
			} catch(Exception e) {
				showMessageDialog(null, "Erro inesperado. \n" + "Erro: " + e);
			}
		} while(opcao!=10);
    }

	private void removerSensorInadequado() {
		if (filaIntSensores.isEmpty()) {
			showMessageDialog(null, "Nao existem sensores na fila no momento, adicione primeiro e tente novamente. ");
		} else {
			double coordenadasX;
			double coordenadasY;
			coordenadasX = parseDouble(showInputDialog("Coordenada X do sensor. (somente os numeros)"));
			coordenadasY = parseDouble(showInputDialog("Coordenada Y do sensor. (somente os numeros)"));
			double coordenadasSoma = coordenadasX + coordenadasY;
			Sensores aux = filaIntSensores.pesquisarSensorInadequado(coordenadasSoma);
			if (aux == null) {
				showMessageDialog(null, "Sensor com essas coordenadas nao encontrado, tente novamente.");
			} else {
				int resposta = 0;
				resposta = parseInt(showInputDialog(aux + "\nEste e o sensor que deseja remover? (1-sim / 2-nao)"));
				while (resposta < 1 || resposta > 2) {
					showMessageDialog(null, "Opcao invalida, tente novamente. ");
					resposta = parseInt(showInputDialog(aux + "\nEste e o sensor que deseja remover? (1-sim / 2-nao)"));
				}
				if (resposta == 2) {
					showMessageDialog(null, "Sensor nao removido, tente novamente trocando as coordenadas. ");
				} else {
					filaIntSensores.removerSensoresInadequados(coordenadasSoma);
					showMessageDialog(null, "Removido com sucesso. ");
				}
			}
		}
	}

	private void visualizarSensoresInadequados() {
		if (filaIntSensores.isEmpty()) {
			showMessageDialog(null, "Nao existem sensores na fila no momento, adicione primeiro e tente novamente. ");
		} else {			
			String auxSensor = filaIntSensores.apresentarSetores();
			showMessageDialog(null, auxSensor);
		}
	}

	private void pesquisarSensorInadequado() {
		if (filaIntSensores.isEmpty()) {
			showMessageDialog(null, "Nao existem sensores na fila no momento, adicione primeiro e tente novamente. ");
		} else {
			double coordenadasX;
			double coordenadasY;
			coordenadasX = parseDouble(showInputDialog("Coordenada X do sensor. (somente os numeros)"));
			coordenadasY = parseDouble(showInputDialog("Coordenada Y do sensor. (somente os numeros)"));
			double coordenadasSoma = coordenadasX + coordenadasY;
			Sensores aux = filaIntSensores.pesquisarSensorInadequado(coordenadasSoma);
			if (aux == null) {
				showMessageDialog(null, "Sensor com essas coordenadas nao encontrado, tente novamente.");
			} else {
				showMessageDialog(null, aux);
			}
		}
	}

	private void verificarSensoresInadequados() {
		if (listaIntSensores.getQtdInserida() == 0) {
			showMessageDialog(null, "Nao existem sensores na lista no momento, adicione primeiro e tente novamente. ");
		} else {
			filaIntSensores.init();
			listaIntSensores.detectarInadequados(filaIntSensores);
			showMessageDialog(null, "Sensores inadequados removidos da lista e inseridos na fila com sucesso. ");
		}
	}

	private void adicionarSensoresMocado() {
		if (!adicionou) {
			Monitora monitora = new Monitora();
			monitora.preencherPh();
			monitora.preencherUmidade();
			monitora.preencherMetano();
			double coordenadasX[] = monitora.getCoordenadasXSensores();
			double coordenadasY[] = monitora.getCoordenadasYSensores();
			double coordenadasSoma[] = new double [monitora.getN()];
			float ph[] = monitora.getPhSensores();
			int umidade[] = monitora.getUmidadesSensores();
			int metano[] = monitora.getMetanoSensores();
			Sensores sensores;
			for (int i = 0; i < monitora.getN(); i++) {
				coordenadasSoma[i] = coordenadasX[i] + coordenadasY[i];				
				sensores = new Sensores(ph[i], umidade[i], metano[i], coordenadasX[i], coordenadasY[i], coordenadasSoma[i]);
				listaIntSensores.insere(sensores);
			}
			showMessageDialog(null, "Sensores adicionados com sucesso. ");
		} else {
			showMessageDialog(null, "Sensores mocados ja adicionados na lista. ");
		}
		adicionou = true;
	}

	private void removerSensor() {
		if (listaIntSensores.getQtdInserida() == 0) {
			showMessageDialog(null, "Nao existem sensores na lista no momento, adicione primeiro e tente novamente. ");
		} else {			
			double coordenadasX;
			double coordenadasY;
			coordenadasX = parseDouble(showInputDialog("Coordenada X do sensor. (somente os numeros)"));
			coordenadasY = parseDouble(showInputDialog("Coordenada Y do sensor. (somente os numeros)"));
			double coordenadasSoma = coordenadasX + coordenadasY;
			double aux = 0;
			double aux2;
			for (int i = 0; i < listaIntSensores.getQtdInserida(); i++) {
				aux2 = listaIntSensores.pegarCoordenadasSoma(i);
				if (aux2 == coordenadasSoma) {
					aux = aux2;
				}
			}
			if (aux == 0) {
				showMessageDialog(null, "Sensor com essas coordenadas nao encontrado, digite novamente. ");
			} else {				
				int resposta;
				resposta = parseInt(showInputDialog(listaIntSensores.apresentaUm(aux) + "\nEste e o sensor que deseja remover? (1-sim / 2-nao)"));
				while (resposta < 1 || resposta > 2) {
					showMessageDialog(null, "Opcao invalida. Digite novamente. ");
					resposta = parseInt(showInputDialog(listaIntSensores.apresentaUm(aux) + "\nEste e o sensor que deseja remover? (1-sim / 2-nao)"));
				}
				if (resposta == 2) {
					showMessageDialog(null, "Sensor nao removido, tente novamente trocando as coordenadas. ");
				} else {				
					listaIntSensores.remove(aux);
					showMessageDialog(null, "Removido com sucesso. ");
				}
			}
		}
	}

	private void visualizarSensores() {
		if (listaIntSensores.getQtdInserida() == 0) {
			showMessageDialog(null, "Nao existem sensores na lista no momento, adicione primeiro e tente novamente. ");
		} else {			
			String auxSensor = listaIntSensores.apresenta();
			showMessageDialog(null, auxSensor);
		}
	}

	private void pesquisarSensor() {
		if (listaIntSensores.getQtdInserida() == 0) {
			showMessageDialog(null, "Nao existem sensores na lista no momento, adicione primeiro e tente novamente. ");
		} else {			
			double coordenadasX;
			double coordenadasY;
			coordenadasX = parseDouble(showInputDialog("Coordenada X do sensor. (somente os numeros)"));
			coordenadasY = parseDouble(showInputDialog("Coordenada Y do sensor. (somente os numeros)"));
			double coordenadasSoma = coordenadasX + coordenadasY;
			double aux = 0;
			double aux2;
			for (int i = 0; i < listaIntSensores.getQtdInserida(); i++) {
				aux2 = listaIntSensores.pegarCoordenadasSoma(i);
				if (aux2 == coordenadasSoma) {
					aux = aux2;
					showMessageDialog(null, listaIntSensores.apresentaUm(aux));
				}
			}
			if (aux == 0) {
				showMessageDialog(null, "Sensor com essas coordenadas nao encontrado, digite novamente. ");
			}
		}
	}

	private void inserirSensor() {
		double coordenadasX;
		double coordenadasY;
		float ph;
		int umidade;
		int metano;
		
		coordenadasX = parseDouble(showInputDialog("Coordenada X do sensor. (somente os numeros)"));
		coordenadasY = parseDouble(showInputDialog("Coordenada Y do sensor. (somente os numeros)"));
		double coordenadasSoma = coordenadasX + coordenadasY;
		double aux = 0;
		double aux2;
		for (int i = 0; i < listaIntSensores.getQtdInserida(); i++) {
			aux2 = listaIntSensores.pegarCoordenadasSoma(i);
			if (aux2 == coordenadasSoma) {
				aux = aux2;
			}
		}
		if (aux != 0) {
			showMessageDialog(null, "Sensor com essas coordenadas ja existe, digite novamente. ");
		} else {			
			ph = parseInt(showInputDialog("PH do solo. (valores adequados em 0-14)"));
			umidade = parseInt(showInputDialog("Umidade do solo. (valores adequados em 30-70)"));
			metano = parseInt(showInputDialog("Umidade do solo. (valores adequados em 100-5000)"));
			
			Sensores sensores = new Sensores(ph, umidade, metano, coordenadasX, coordenadasY, coordenadasSoma);
			
			listaIntSensores.insere(sensores);
			
			showMessageDialog(null, "Sensor adicionado com sucesso. ");
		}
	}

	private String gerarMenu() {
		String aux = "Programa de Inserir sensores e verificar parametros\n";
		aux += "1. Inserir sensor\n";
		aux += "2. Pesquisar sensor\n";   
		aux += "3. Visualizar sensores\n";   
		aux += "4. Remover sensor\n";
		aux += "5. Adicionar sensores mocados\n";
		aux += "6. Verificar sensores inadequados\n";
		aux += "7. Pesquisar sensor inadequado\n";
		aux += "8. Visualizar sensores inadequados\n";
		aux += "9. Remover sensor inadequado\n";
		aux += "10. Encerrar programa\n";
		return aux;
	}
}
