package br.fiap.conservados;

import br.fiap.atmosferas.atmosferasControladas;
import br.fiap.desidratacao.desidratacao;
import br.fiap.embalagens.embalagensInovadoras;
import br.fiap.processamento.processamentoMinimo;
import br.fiap.produtos.produtos;

public class conservados extends produtos{
	private atmosferasControladas atmosferasControladas;
	private desidratacao desidratacao;
	private embalagensInovadoras embalagensInovadoras;
	private processamentoMinimo processamentoMinimo;
	private int tempoExtra;

	public conservados(int id_produto, String nome, double peso,
			br.fiap.atmosferas.atmosferasControladas atmosferasControladas,
			br.fiap.desidratacao.desidratacao desidratacao,
			br.fiap.embalagens.embalagensInovadoras embalagensInovadoras,
			br.fiap.processamento.processamentoMinimo processamentoMinimo, int tempoExtra) {
		super(id_produto, nome, peso);
		this.atmosferasControladas = atmosferasControladas;
		this.desidratacao = desidratacao;
		this.embalagensInovadoras = embalagensInovadoras;
		this.processamentoMinimo = processamentoMinimo;
		this.tempoExtra = tempoExtra;
	}

	public atmosferasControladas getAtmosferasControladas() {
		return atmosferasControladas;
	}

	public desidratacao getDesidratacao() {
		return desidratacao;
	}

	public embalagensInovadoras getEmbalagensInovadoras() {
		return embalagensInovadoras;
	}

	public processamentoMinimo getProcessamentoMinimo() {
		return processamentoMinimo;
	}
	
	public int getTempoExtra() {
		return tempoExtra;
	}

	@Override
	public String toString() {
		String aux = "";
		aux += "ID do Produto: " + getId_produto() + "\n";
		aux += "Nome do produto: " + getNome() + "\n";
		aux += "Peso do Produto: " + getPeso() + " kg \n";
		aux += "\n";
		aux += "Tecnologia utilizada -> ";
		if (atmosferasControladas != null) {			
			aux += "Atmosferas Controladas \n";
			aux += "Nivel de Oxigenio: " + atmosferasControladas.getNivelOxigenio() + "\n";
			aux += "Nivel de Dioxido de Carbono: " + atmosferasControladas.getNivelDioxidoCarbono() + "\n";
			aux += "Nivel de Nitrogenio: " + atmosferasControladas.getNivelNitrogenio() + "\n";
		} else if (desidratacao != null) {
			aux += "Desidratacao \n";
			aux += "Temperatura: " + desidratacao.getTemperatura() + "\n";
			aux += "Tempo Desidratado: " + desidratacao.getTempoDesidratado() + "\n";
			if (desidratacao.isUsoMicroondas()) {
				aux += "Uso de Microondas " + "\n";
			} else {
				aux += "Uso de Estufa " + "\n";
			}
			if (desidratacao.isSecagemAoArLivre()) {
				aux += "Uso de Secagem ao Ar Livre " + "\n";
			} else {
				aux += "Uso de Liofilizacao " + "\n";				
			}
		} else if (embalagensInovadoras != null) {
			aux += "Embalagens Inovadoras \n";
			aux += "Tipo de Embalagem: " + embalagensInovadoras.getTipoEmbalagem() + "\n";
			aux += "Temperatura de Armazenamento: " + embalagensInovadoras.getTemperaturaArmazenamento() + "\n";
			aux += "Umidade de Armazenamento: " + embalagensInovadoras.getUmidadeArmazenamento() + "\n";
			aux += "Umidade de Armazenamento: " + embalagensInovadoras.getUmidadeArmazenamento() + "\n";
			if (embalagensInovadoras.isEmbalagemAtiva()) {
				aux += "Uso de Embalagem Ativa " + "\n";
			} else {
				aux += "Uso de Embalagem Inteligente " + "\n";
			}
		} else {
			aux += "Processamento Minimo: \n";
			if (processamentoMinimo.isCortar()) {
				aux += "Processo de cortar " + "\n";
			} else if (processamentoMinimo.isLavar()){
				aux += "Processo de lavagem " + "\n";
			} else {
				aux += "Processo de embalagens em porcoes diferentes " + "\n";
			}
		}
		aux += "Tempo extra com a tecnologia: " + tempoExtra + " dias \n";
		return aux;
	}
	
}
