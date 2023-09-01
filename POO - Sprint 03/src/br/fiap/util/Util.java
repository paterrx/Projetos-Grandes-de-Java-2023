package br.fiap.util;

import static javax.swing.JOptionPane.*;

import java.util.List;

import br.fiap.departamento.Departamento;
import br.fiap.cidade.Cidade;
import br.fiap.classesDAO.FuncionarioDAO;
import br.fiap.endereco.Endereco;
import br.fiap.funcionario.Funcionario;
import br.fiap.excecao.OpcaoInvalidaException;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class Util {
	public void menu() {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenu()));
				if (opcao < 1 || opcao > 6) {
					throw new OpcaoInvalidaException("Opção inválida");
				} else {
					switch (opcao) {
						case 1:
							inserirFuncionario();
							break;
						case 2:
							pesquisarFuncionario();
							break;
						case 3:
							alterarFuncionario();
							break;
						case 4:
							excluirFuncionario();
							break;
						case 5:
							listarFuncionarios();
							break;
					}
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "Voce deve digitar um numero. \n" + "Erro: " + e);
			} catch(OpcaoInvalidaException e) {
				showMessageDialog(null, e.getMessage());
			} catch(Exception e) {
				showMessageDialog(null, "Erro inesperado. \n" + "Erro: " + e);
			}
		} while(opcao!=6);
	}

	private void listarFuncionarios() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> lista = funcionarioDAO.listar();
		if (lista.size() == 0) {
			showMessageDialog(null, "Nao existem funcionarios cadastrados para listar. ");
		} else {			
			String aux = "";
			for (Funcionario f : lista) {
				aux += "ID: " + f.getId() + "\nCodigo: " + f.getCodigo() + "\nNome: " + f.getNome() + "\nEmail: " 
						+ f.getEmail() + "\nTelefone: " + f.getTelefone() + "\nSalario: " + f.getSalario() 
						+ "\nSexo: " + f.getSexo() + "\nNome da Endereco: " + f.getNomeDoEndereco() + "\nNome do Departamento: " 
						+ f.getNomeDoDepartamento() + "\n";
			}
			showMessageDialog(null, aux);
		}
	}

	private void excluirFuncionario() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> lista = funcionarioDAO.listar();
		if (lista.size() == 0) {
			showMessageDialog(null, "Nao existem funcionarios cadastrados para excluir. ");
		} else {			
			int idEmp = parseInt(showInputDialog("ID do Funcionario"));
			String aux = funcionarioDAO.pesquisar(idEmp);
			if (aux == null) {
				showMessageDialog(null, "Funcionario com esse ID nao cadastrado. ");
			} else {				
				funcionarioDAO.excluir(idEmp);
				showMessageDialog(null, "Funcionario removido com sucesso. ");
			}
		}
	}

	private void alterarFuncionario() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> lista = funcionarioDAO.listar();
		if (lista.size() == 0) {
			showMessageDialog(null, "Nao existem funcionarios cadastrados para alterar. ");
		} else {			
			int idEmp = parseInt(showInputDialog("ID do Funcionario"));
			String aux = funcionarioDAO.pesquisar(idEmp);
			if (aux == null) {
				showMessageDialog(null, "Funcionario com esse ID nao cadastrado. ");
			} else {				
				//complementar com update
			}
		}
	}

	private void pesquisarFuncionario() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> lista = funcionarioDAO.listar();
		if (lista.size() == 0) {
			showMessageDialog(null, "Nao existem funcionarios cadastrados para pesquisar. ");
		} else {			
			int idEmp = parseInt(showInputDialog("ID do Funcionario"));
			String aux = funcionarioDAO.pesquisar(idEmp);
			if (aux == null) {
				showMessageDialog(null, "Funcionario com esse ID nao cadastrado. ");
			} else {				
				showMessageDialog(null, aux);
			}
		}
	}

	private void inserirFuncionario() {
		int id = parseInt(showInputDialog("ID do Funcionario"));
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		if(funcionarioDAO.pesquisar(id) != null) {	
			showMessageDialog(null, "Funcionario com esse ID ja existe. ");
		} else {
			int codigo = parseInt(showInputDialog("Codigo do Funcionario"));
			String nome = showInputDialog("Nome do Funcionario");
			String email = showInputDialog("Email do Funcionario");
			int telefone = parseInt(showInputDialog("Telefone do Funcionario (apenas numeros)"));
			double salario = parseDouble(showInputDialog("Salario do Funcionario (apenas numeros)"));
			
			String sexo = showInputDialog("Sexo do Funcionario");
			
			int id_cidade = parseInt(showInputDialog("ID da Cidade"));
			String nome_cidade = showInputDialog("Nome da Cidade");
			String sigla = showInputDialog("Sigla do Estado");
			Cidade cidade = new Cidade(id_cidade, nome_cidade, sigla);
			int id_endereco = parseInt(showInputDialog("ID do Endereco"));
			Endereco endereco = new Endereco(id_endereco, cidade);
			int id_departamento = parseInt(showInputDialog("ID do Departamento"));
			int codigo_departamento = parseInt(showInputDialog("Codigo do Departamento"));
			String nome_departamento = showInputDialog("Nome do Departamento");
			int qtd_funcionarios = parseInt(showInputDialog("Quantidade de Funcionarios"));
			Departamento departamento = new Departamento(id_departamento, codigo_departamento, nome_departamento, qtd_funcionarios);
			
			Funcionario funcionario = new Funcionario(id, codigo, nome, email, telefone, salario, sexo, endereco, departamento);
			
			funcionarioDAO.inserir(funcionario);
			showMessageDialog(null, "Funcionario adicionado com sucesso. ");
		}
	}

	private String gerarMenu() {
		String aux = "Controle de Funcionarios\n";
		aux += "1. Inserir funcionario\n";
		aux += "2. Pesquisar funcionario\n";   
		aux += "3. Alterar funcionario\n";   
		aux += "4. Excluir funcionario\n";   
		aux += "5. Listar funcionarios\n";
		aux += "6. Finalizar\n";
		return aux;
	}
}
