package br.fiap.util;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.fiap.atmosferas.atmosferasControladas;
import br.fiap.conservados.conservados;
import br.fiap.desidratacao.desidratacao;
import br.fiap.embalagens.embalagensInovadoras;
import br.fiap.processamento.processamentoMinimo;
import br.fiap.produtos.produtos;

public class Util {
	private List<produtos> listaProdutos = new LinkedList<>();
	private List<conservados> listaProdutosConservados = new LinkedList<>();
	public void menu () {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenu()));
				if (opcao < 1 || opcao > 9) {
					showMessageDialog(null, "Opcao invalida. ");
				} else {
					switch (opcao) {
						case 1:
							inserirProduto();
							break;
						case 2:
							pesquisarProduto();
							break;
						case 3:
							visualizarProduto();
							break;
						case 4:
							removerProduto();
							break;
						case 5:
							adicionarConservacao();
							break;
						case 6:
							pesquisarConservacao();
							break;
						case 7:
							visualizarConservacao();
							break;
						case 8:
							removerConservacao();
							break;
						case 9:
							break;
					}
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "Voce deve digitar um numero. \n" + "Erro: " + e);
			} catch(Exception e) {
				showMessageDialog(null, "Erro inesperado. \n" + "Erro: " + e);
			}
		} while(opcao!=9);
	}

	private void removerConservacao() {
		if (listaProdutosConservados.isEmpty()) {
			showMessageDialog(null, "Nao existem produtos na lista dos conservados, adicione primeiro e tente novamente. ");
		} else {			
			int id_produto;
			id_produto = parseInt(showInputDialog("ID do Produto"));
			conservados aux = null;
			for (conservados c : listaProdutosConservados) {
				if (c.getId_produto() == id_produto) {
					aux = c;
				}
			}
			if (aux == null) {
				showMessageDialog(null, "Produto com esse ID nao encontrado, tente novamente. ");
			} else {				
				int resposta;
				resposta = parseInt(showInputDialog(aux + "\nEste e o produto que deseja remover? (1-sim / 2-nao)"));
				while (resposta < 1 || resposta > 2) {
					showMessageDialog(null, "Opcao invalida. Digite novamente. ");
					resposta = parseInt(showInputDialog(aux + "\nEste e o produto que deseja remover? (1-sim / 2-nao)"));
				}
				if (resposta == 2) {
					showMessageDialog(null, "Produto nao removido, tente novamente trocando o ID. ");
				} else {				
					listaProdutosConservados.remove(aux);
					resposta = parseInt(showInputDialog("Deseja remover da lista dos produtos tambem? (1-sim / 2-nao)"));
					while (resposta < 1 || resposta > 2) {
						showMessageDialog(null, "Numero invalido. Tente novamente. ");
						resposta = parseInt(showInputDialog("Deseja remover da lista dos produtos tambem? (1-sim / 2-nao)"));
					}
					if (resposta == 1) {
						produtos aux2 = null;
						for (produtos p : listaProdutos) {
							if (p.getId_produto() == id_produto) {
								aux2 = p;
							}
						}
						listaProdutos.remove(aux2);
						showMessageDialog(null, "Removido com sucesso de ambas listas. ");
					} else {
						showMessageDialog(null, "Removido com sucesso da listas dos conservados. ");
					}
				}
			}
			}
	}

	private void visualizarConservacao() {
		if (listaProdutosConservados.isEmpty()) {
			showMessageDialog(null, "Nao existem produtos na lista dos conservados, adicione primeiro e tente novamente. ");
		} else {			
			String auxConservados = "Lista dos conservados: \n";
			for (conservados c : listaProdutosConservados) {
				auxConservados += c + "\n";
			}
			showMessageDialog(null, auxConservados);
		}
	}

	private void pesquisarConservacao() {
		if (listaProdutosConservados.isEmpty()) {
			showMessageDialog(null, "Nao existem produtos na lista dos conservados, adicione primeiro e tente novamente. ");
		} else {			
			int id_produto;
			id_produto = parseInt(showInputDialog("ID do Produto"));
			conservados aux = null;
			for (conservados c : listaProdutosConservados) {
				if (c.getId_produto() == id_produto) {
					aux = c;
					showMessageDialog(null, aux);
				}
			}
			if (aux == null) {
				showMessageDialog(null, "Produto nao encontrado com este ID, tente novamente. ");
			}
		}
	}

	private void adicionarConservacao() {
		if (listaProdutos.isEmpty()) {
			showMessageDialog(null, "Nao existem produtos na lista no momento para conservar, adicione primeiro e tente novamente. ");
		} else {
			conservados conservados = null;
			int id_produto;
			id_produto = parseInt(showInputDialog("ID do Produto"));
			produtos auxProduto = null;
			for (produtos p : listaProdutos) {
				if (p.getId_produto() == id_produto) {
					auxProduto = p;
				}
			}
			
			if (auxProduto == null) {
				showMessageDialog(null, "Nao encontrado um produto com esse ID, tente novamente. ");
			} else {
				conservados aux2 = null;
				for (conservados c : listaProdutosConservados) {
					if (c.getId_produto() == auxProduto.getId_produto()) {
						aux2 = c;
					}
				}
				int resposta2 = 0;
				if (aux2 != null) {
					resposta2 = parseInt(showInputDialog("Esse produto ja foi conservado uma vez, deseja conservar novamente? (1-sim / 2-nao)"));
					while (resposta2 < 1 || resposta2 > 2) {
						showMessageDialog(null, "Opcao invalida, tente novamente. ");
						resposta2 = parseInt(showInputDialog("Esse produto ja foi conservado uma vez, deseja conservar novamente? (1-sim / 2-nao)"));
					}
					if (resposta2 == 2) {
						showMessageDialog(null, "OK, retornando ao menu. ");
					}
				} if (resposta2 <= 1) {
					int opcao = 0;
					int tempoExtra = 0;
					
					String aux = "Qual sera a opcao de conservacao\n";
					aux += "1. Desidratacao\n";
					aux += "2. Embalagens Inovadoras\n";   
					aux += "3. Atmosferas Controladas\n";   
					aux += "4. Processamento Minimo\n";
					
					opcao = parseInt(showInputDialog(aux));
					
					while (opcao < 1 || opcao > 4) {
						showMessageDialog(null, "Opcao invalida. Digite novamente. ");
						opcao = parseInt(showInputDialog(aux));
					}
					
					int resposta = 0;
					
					switch (opcao) {
					case 1:
						double temperatura;
						double tempoDesidratado;
						boolean usoMicroondas = false;
						boolean usoEstufa = false;
						boolean secagemAoArLivre = false;
						boolean liofilizacao = false;
						
						temperatura = parseDouble(showInputDialog("Temperatura (graus celsius, aconselhavel ate 100)"));
						tempoDesidratado = parseDouble(showInputDialog("Tempo de desidratacao (minutos, aconselhavel ate 60)"));
						
						resposta = 0;
						resposta = parseInt(showInputDialog("Qual foi o metodo usado? Microondas - 1 / Estufa - 2"));
						
						while (resposta < 1 || resposta > 2) {
							showMessageDialog(null, "Opcao invalida. Digite novamente. ");
							resposta = parseInt(showInputDialog("Qual foi o metodo usado? Microondas - 1 / Estufa - 2"));
						}
						
						if (resposta == 1) {
							usoMicroondas = true;
						} else {
							usoEstufa = true;
						}
						
						resposta = 0;
						resposta = parseInt(showInputDialog("Qual foi o metodo usado? Secagem ao ar livre - 1 / Liofilizacao - 2"));
						
						while (resposta < 1 || resposta > 2) {
							showMessageDialog(null, "Opcao invalida. Digite novamente. ");
							resposta = parseInt(showInputDialog("Qual foi o metodo usado? Secagem ao ar livre - 1 / Liofilizacao - 2"));
						}
						
						if (resposta == 1) {
							secagemAoArLivre = true;
						} else {
							liofilizacao = true;
						}
						
						desidratacao desidratacao = new desidratacao(temperatura, tempoDesidratado, usoMicroondas, usoEstufa, secagemAoArLivre, 
								liofilizacao);
						
						tempoExtra = 365;
						
						conservados = new conservados(id_produto, auxProduto.getNome(), auxProduto.getPeso(), null, desidratacao, null, null, tempoExtra);
						
						break;
					case 2:
						String tipoEmbalagem;
						double temperaturaArmazenamento;
						double umidadeArmazenamento;
						boolean embalagemAtiva = false;
						boolean embalagemInteligente = false;
						
						tipoEmbalagem = showInputDialog("Tipo de Embalagem (plastica/metalica/etc)");
						temperaturaArmazenamento = parseDouble(showInputDialog("Temperatura de Armazenamento (graus celsius, aconselhavel ate 20)"));
						umidadeArmazenamento = parseDouble(showInputDialog("Umidade de Armazenamento (gramas por metro cubico, aconselhavel ate 50)"));
						
						resposta = 0;
						resposta = parseInt(showInputDialog("Qual foi o metodo usado? Ativa - 1 / Inteligente - 2"));
						
						while (resposta < 1 || resposta > 2) {
							showMessageDialog(null, "Opcao invalida. Digite novamente. ");
							resposta = parseInt(showInputDialog("Qual foi o metodo usado? Ativa - 1 / Inteligente - 2"));
						}
						
						if (resposta == 1) {
							embalagemAtiva = true;
						} else {
							embalagemInteligente = true;
						}
						
						embalagensInovadoras embalagensInovadoras = new embalagensInovadoras(tipoEmbalagem, temperaturaArmazenamento, umidadeArmazenamento,
								embalagemAtiva, embalagemInteligente);
						
						tempoExtra = 13;
						
						conservados = new conservados(id_produto, auxProduto.getNome(), auxProduto.getPeso(), null, null, embalagensInovadoras, null, tempoExtra);
						
						break;
					case 3:
						double nivelOxigenio;
						double nivelDioxidoCarbono;
						double nivelNitrogenio;
						
						nivelOxigenio = parseDouble(showInputDialog("Nivel de Oxigenio (porcentagem, aconselhavel ate 100)"));
						nivelDioxidoCarbono = parseDouble(showInputDialog("Nivel de Dioxido de Carbono (porcentagem, aconselhavel ate 100)"));
						nivelNitrogenio = parseDouble(showInputDialog("Nivel de Nitrogenio (porcentagem, aconselhavel ate 100)"));
						
						atmosferasControladas atmosferasControladas = new atmosferasControladas(nivelOxigenio, nivelDioxidoCarbono, nivelNitrogenio);
						
						tempoExtra = 56;
						
						conservados = new conservados(id_produto, auxProduto.getNome(), auxProduto.getPeso(), atmosferasControladas, null, null, null, tempoExtra);
						
						break;
					case 4:
						boolean cortar = false;
						boolean lavar = false;
						boolean embalarPorcoesSeparadas = false;
						
						resposta = 0;
						resposta = parseInt(showInputDialog("Qual foi o metodo usado? Cortar - 1 / Lavar - 2 / Embalar Porcoes - 3"));
						
						while (resposta < 1 || resposta > 3) {
							showMessageDialog(null, "Opcao invalida. Digite novamente. ");
							resposta = parseInt(showInputDialog("Qual foi o metodo usado? Cortar - 1 / Lavar - 2 / Embalar Porcoes - 3"));
						}
						
						if (resposta == 1) {
							cortar = true;
						} else if (resposta == 2) {
							lavar = true;
						} else {
							embalarPorcoesSeparadas = true;
						}
						
						processamentoMinimo processamentoMinimo = new processamentoMinimo(cortar, lavar, embalarPorcoesSeparadas);
						
						tempoExtra = 13;
						
						conservados = new conservados(id_produto, auxProduto.getNome(), auxProduto.getPeso(), null, null, null, processamentoMinimo, tempoExtra);
						
						break;
					}
					
					listaProdutosConservados.add(conservados);
					
					showMessageDialog(null, "Seu produto com essa conservacao, agora dura cerca de " + tempoExtra + " dias a mais. ");
				}
			}
				
		}
			
	}

	private void removerProduto() {
		if (listaProdutos.isEmpty()) {
			showMessageDialog(null, "Nao existem produtos na lista no momento, adicione primeiro e tente novamente. ");
		} else {			
			int id_produto;
			id_produto = parseInt(showInputDialog("ID do Produto"));
			produtos aux = null;
			for (produtos p : listaProdutos) {
				if (p.getId_produto() == id_produto) {
					aux = p;
				}
			}
			if (aux == null) {
				showMessageDialog(null, "Produto com esse ID nao encontrado, tente novamente. ");
			} else {				
				int resposta;
				resposta = parseInt(showInputDialog(aux + "\nEste e o produto que deseja remover? (1-sim / 2-nao)"));
				while (resposta < 1 || resposta > 2) {
					showMessageDialog(null, "Opcao invalida. Digite novamente. ");
					resposta = parseInt(showInputDialog(aux + "\nEste e o produto que deseja remover? (1-sim / 2-nao)"));
				}
				if (resposta == 2) {
					showMessageDialog(null, "Produto nao removido, tente novamente trocando o ID. ");
				} else {				
					listaProdutos.remove(aux);
					showMessageDialog(null, "Removido com sucesso. ");
				}
			}
		}
	}

	private void visualizarProduto() {
		if (listaProdutos.isEmpty()) {
			showMessageDialog(null, "Nao existem produtos na lista no momento, adicione primeiro e tente novamente. ");
		} else {			
			String auxProduto = "Lista dos produtos: \n";
			for (produtos p : listaProdutos) {
				auxProduto += p + "\n";
			}
			showMessageDialog(null, auxProduto);
		}
	}

	private void pesquisarProduto() {
		if (listaProdutos.isEmpty()) {
			showMessageDialog(null, "Nao existem produtos na lista no momento, adicione primeiro e tente novamente. ");
		} else {			
			int id_produto;
			id_produto = parseInt(showInputDialog("ID do Produto"));
			produtos aux = null;
			for (produtos p : listaProdutos) {
				if (p.getId_produto() == id_produto) {
					aux = p;
					showMessageDialog(null, aux);
				}
			}
			if (aux == null) {
				showMessageDialog(null, "Produto nao encontrado com este ID, tente novamente. ");
			}
		}
	}

	private void inserirProduto() {
		int id_produto;
		String nome;
		double peso;
		
		id_produto = parseInt(showInputDialog("ID do Produto"));
		produtos aux = null;
		for (produtos p : listaProdutos) {
			if (p.getId_produto() == id_produto) {
				aux = p;
			}
		}
		if (aux != null) {
			showMessageDialog(null, "Produto com esse ID ja existe, digite outro. ");
		} else {			
			nome = showInputDialog("Nome do Produto");
			peso = parseDouble(showInputDialog("Peso do produto (kg e só o número)"));
			
			produtos produto = new produtos(id_produto, nome, peso);
			
			listaProdutos.add(produto);
			
			showMessageDialog(null, "Produto adicionado com sucesso. ");
		}
	}

	private String gerarMenu() {
		String aux = "Programa de Inserir Produtos e Conservar\n";
		aux += "1. Inserir produtos\n";
		aux += "2. Pesquisar produtos\n";   
		aux += "3. Visualizar produtos\n";   
		aux += "4. Remover produtos\n";
		aux += "5. Adicionar conservacao\n";
		aux += "6. Pesquisar produtos com conservacao\n";
		aux += "7. Visualizar produtos com conservacao\n";
		aux += "8. Remover produtos com conservacao\n";
		aux += "9. Encerrar programa\n";
		return aux;
	}
}
