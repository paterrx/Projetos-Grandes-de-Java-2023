package br.fiap.util;

import static javax.swing.JOptionPane.*;

import java.util.List;

import br.fiap.departamento.Departamento;
import br.fiap.cidade.Cidade;
import br.fiap.classesDAO.CidadeDAO;
import br.fiap.classesDAO.DepartamentoDAO;
import br.fiap.classesDAO.EnderecoDAO;
import br.fiap.classesDAO.FuncionarioDAO;
import br.fiap.endereco.Endereco;
import br.fiap.funcionario.Funcionario;
import br.fiap.excecao.OpcaoInvalidaException;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
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
			} catch (NumberFormatException e) {
				showMessageDialog(null, "Voce deve digitar um numero. \n" + "Erro: " + e);
			} catch (OpcaoInvalidaException e) {
				showMessageDialog(null, e.getMessage());
			} catch (Exception e) {
				showMessageDialog(null, "Erro inesperado. \n" + "Erro: " + e);
			}
		} while (opcao != 6);
	}

	private void listarFuncionarios() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> lista = funcionarioDAO.listar();
		if (lista.size() == 0) {
			showMessageDialog(null, "Nao existem funcionarios cadastrados para listar. ");
		} else {
			String aux = "Funcionarios: \n\n";
			for (Funcionario f : lista) {
				aux += "ID: " + f.getId() + "\nCodigo: " + f.getCodigo() + "\nNome: " + f.getNome() + "\nEmail: "
						+ f.getEmail() + "\nTelefone: " + f.getTelefone() + "\nSalario: " + f.getSalario()
						+ "\nData da Contratacao: " + f.getsqlDate() + "\nSexo: " + f.getSexo() + "\nID do Endereco: "
						+ f.getIdDoEndereco() + "\nID do Departamento: "
						+ f.getIdDoDepartamento() + "\n\n";
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
			int id = parseInt(showInputDialog("ID do Funcionario"));
			String aux = funcionarioDAO.pesquisar(id);
			if (aux == null) {
				showMessageDialog(null, "Funcionario com esse ID nao cadastrado. ");
			} else {
				showMessageDialog(null, "No momento e possivel apenas trocar o nome e salario do Funcionario. ");
				String nome = showInputDialog("Novo nome");
				double salario = parseDouble(showInputDialog("Novo salario"));
				Funcionario funcionario = new Funcionario(id, nome, salario);
				funcionario.setId(id);
				funcionario.setNome(nome);
				funcionario.setSalario(salario);
				funcionarioDAO.atualizar(funcionario);

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
		int id = parseInt(showInputDialog("ID do Funcionario (unico)"));
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		while (funcionarioDAO.pesquisar(id) != null) {
			showMessageDialog(null, "Funcionario com esse ID ja existe. ");
			id = parseInt(showInputDialog("ID do Funcionario (unico)"));
		}
		int codigo = parseInt(showInputDialog("Codigo do Funcionario (unico)"));
		while (funcionarioDAO.pesquisarCodigo(codigo) != null) {
			showMessageDialog(null, "Funcionario com esse codigo ja existe. ");
			codigo = parseInt(showInputDialog("Codigo do Funcionario (unico)"));
		}
		String nome = showInputDialog("Nome do Funcionario");
		String email = showInputDialog("Email do Funcionario");
		long telefone = parseLong(showInputDialog("Telefone do Funcionario (apenas numeros)"));

		double salario = parseDouble(showInputDialog("Salario do Funcionario (apenas numeros e > 0)"));
		while (salario <= 0) {
			showMessageDialog(null, "Salario invalido. ");
			salario = parseDouble(showInputDialog("Salario do Funcionario (apenas numeros e > 0)"));
		}

		String sexo = showInputDialog("Sexo do Funcionario (M ou F)");
		while (!(sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F"))) {
			showMessageDialog(null, "Caracter invalido. ");
			sexo = showInputDialog("Sexo do Funcionario (M ou F)");
		}
		if (sexo.equals("m")) {
			sexo = "M";
		} else if (sexo.equals("f")) {
			sexo = "F";
		}

		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> listaCid = cidadeDAO.listar();
		String aux = "Selecione a cidade\n";
		for (Cidade c : listaCid) {
			aux += c.getId() + " | " + c.getNome() + "\n";
		}
		int id_cidade = parseInt(showInputDialog(aux));
		while (id_cidade < 1 || id_cidade > 5) {
			showMessageDialog(null, "Cidade invalida. ");
			id_cidade = parseInt(showInputDialog(aux));
		}
		Cidade cidade = new Cidade(id_cidade);

		EnderecoDAO enderecoDAO = new EnderecoDAO();
		List<Endereco> listaEnd = enderecoDAO.listar();
		aux = "Selecione o endereco\n";
		for (Endereco e : listaEnd) {
			aux += e.getId_endereco() + " | " + e.getNome_endereco() + "\n";
		}
		int id_endereco = parseInt(showInputDialog(aux));
		while (id_endereco < 1 || id_endereco > 5) {
			showMessageDialog(null, "Endereco invalido. ");
			id_endereco = parseInt(showInputDialog(aux));
		}
		Endereco endereco = new Endereco(id_endereco, cidade);

		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		List<Departamento> listaDep = departamentoDAO.listar();
		aux = "Selecione o departamento\n";
		for (Departamento d : listaDep) {
			aux += d.getId() + " | " + d.getNome() + " | QTD funcionarios: " + d.getQtd_funcionarios() + "\n";
		}
		int id_departamento = parseInt(showInputDialog(aux));
		while (id_departamento < 1 || id_departamento > 5) {
			showMessageDialog(null, "Departamento invalido. ");
			id_departamento = parseInt(showInputDialog(aux));
		}
		Departamento departamento = new Departamento(id_departamento);

		Funcionario funcionario = new Funcionario(id, codigo, nome, email, telefone, salario, sexo, endereco,
		departamento);

		funcionarioDAO.inserir(funcionario);
		showMessageDialog(null, "Funcionario adicionado com sucesso. ");
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
