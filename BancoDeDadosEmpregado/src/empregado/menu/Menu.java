package empregado.menu;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;

import java.util.List;

import empregado.dao.DepartamentoDAO;
import empregado.dao.EmpregadoDAO;
import empregado.entidade.Departamento;
import empregado.entidade.Empregado;
import empregado.excecao.OpcaoInvalidaException;

public class Menu {

	// método para exibir  o menu da aplicação
	public void menu() {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenu()));
				if(opcao < 1 || opcao > 7) {
					throw new OpcaoInvalidaException("Opção inválida");
				} else {
					switch(opcao) {
						case 1:
							cadastrarDepartamento();
							break;
						case 2:
							cadastrarEmpregado();
							break;
						case 3:
							pesquisarEmpregado();
							break;
						case 4:
							listarEmpregado();
							break;
						case 5:
							atualizarEmpregado();
							break;
						case 6:
							excluirEmpregado();
							break;							
					}
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "Você deve digitar um número\n" + e);
			}
			catch(OpcaoInvalidaException e) {
				showMessageDialog(null, e.getMessage());
			}
		} while(opcao != 7);		
	}
	
	private void excluirEmpregado() {
		DepartamentoDAO depDaoteste = new DepartamentoDAO();
		List<Departamento> listateste = depDaoteste.listar();
		if (listateste.size() == 0) {
			showMessageDialog(null, "Nenhum departamento criado no momento, "
					+ "nao e possivel listar empregados. ");
		} else {
			int idEmp = parseInt(showInputDialog("ID do Empregado"));
			EmpregadoDAO empDao = new EmpregadoDAO();
			String aux = empDao.pesquisar(idEmp);
			if (aux != null) {
				showMessageDialog(null, "Empregado com esse ID nao cadastrado. ");
			} else {				
				empDao.remover(idEmp);
				showMessageDialog(null, "Empregado removido com sucesso. ");
			}
		}
	}

	private void atualizarEmpregado() {
		DepartamentoDAO depDaoteste = new DepartamentoDAO();
		List<Departamento> listateste = depDaoteste.listar();
		if (listateste.size() == 0) {
			showMessageDialog(null, "Nenhum departamento criado no momento, "
					+ "nao e possivel listar empregados. ");
		} else {
			EmpregadoDAO empDAO = new EmpregadoDAO();
			int id = parseInt(showInputDialog("ID"));
			String aux = empDAO.pesquisar(id);
			if(aux == null) {
				showMessageDialog(null, "Empregado não encontrado");
			} else {
				String novoNome = showInputDialog("Novo nome");
				double novoSalario = parseDouble(showInputDialog("Novo salário"));
				Empregado empregado = new Empregado(id, novoNome, novoSalario);
				empregado.setId(id);
				empregado.setNome(novoNome);
				empregado.setSalario(novoSalario);
				empDAO.atualizar(empregado);
			}	
		}
	}

	private void listarEmpregado() {
		DepartamentoDAO depDaoteste = new DepartamentoDAO();
		List<Departamento> listateste = depDaoteste.listar();
		if (listateste.size() == 0) {
			showMessageDialog(null, "Nenhum departamento criado no momento, "
					+ "nao e possivel listar empregados. ");
		} else {
			EmpregadoDAO empDAO = new EmpregadoDAO();
			List<Empregado> lista = empDAO.listar();
			String aux = "";
			for (Empregado e : lista) {
				aux += "ID: " + e.getId() + "\nNome: " + e.getNome() + "\nSalario: " + e.getSalario() + "\nID do departamento: " 
				+ e.getDepartamento().getId() + "\n";
			}
			showMessageDialog(null, aux);
		}
	}

	private void pesquisarEmpregado() {
		DepartamentoDAO depDaoteste = new DepartamentoDAO();
		List<Departamento> listateste = depDaoteste.listar();
		if (listateste.size() == 0) {
			showMessageDialog(null, "Nenhum departamento criado no momento, "
					+ "nao e possivel pesquisar empregados. ");
		} else {
			int idEmp = parseInt(showInputDialog("ID do Empregado"));
			EmpregadoDAO empDao = new EmpregadoDAO();
			String aux = empDao.pesquisar(idEmp);
			if (aux != null) {
				showMessageDialog(null, "Empregado com esse ID nao cadastrado. ");
			} else {				
				showMessageDialog(null, aux);
			}
		}
	}

	private void cadastrarEmpregado() {
		DepartamentoDAO depDaoteste = new DepartamentoDAO();
		List<Departamento> listateste = depDaoteste.listar();
		if (listateste.size() == 0) {
			showMessageDialog(null, "Nenhum departamento criado no momento, "
					+ "nao e possivel cadastrar empregados. ");
		} else {
			int idEmp = parseInt(showInputDialog("ID do Empregado"));
			
			EmpregadoDAO empDao = new EmpregadoDAO();
			if (empDao.pesquisar(idEmp) == null) {
				showMessageDialog(null, "Empregado com esse ID ja cadastrado. ");
			} else {
				DepartamentoDAO depDao = new DepartamentoDAO();
				List<Departamento> lista = depDao.listar();
				String aux = "Selecione o departamento\n";
				for (Departamento d : lista) {
					aux += d.getId() + " " + d.getNome() + "\n";
				}
				
				String nome = showInputDialog("Nome do Empregado");
				double salario = parseDouble(showInputDialog("Salario do Empregado"));
				int idDep = parseInt(showInputDialog(aux));
				int aux2 = 0;
				for (int i = 0; i < lista.size(); i++) {
					if (idDep == lista.get(i).getId()) {
						aux2 = 1;
					}
				}
				while (aux2 == 0) {
					showMessageDialog(null, "ID de departamento invalido, tente novamente. ");
					idDep = parseInt(showInputDialog(aux));
					for (int i = 0; i < lista.size(); i++) {
						if (idDep == lista.get(i).getId()) {
							aux2 = 1;
						}
					}
				}
				
				Departamento departamento = new Departamento(idDep);
				
				Empregado empregado = new Empregado(idEmp, nome, salario, departamento);
				
				empDao.inserir(empregado);
			}
		}
	}

	private void cadastrarDepartamento() {
		int id = parseInt(showInputDialog("ID"));
		String nome = showInputDialog("Nome do Departamento");
		
		Departamento departamento = new Departamento(id, nome);
		DepartamentoDAO dao = new DepartamentoDAO();
		
		if(dao.pesquisar(departamento)) {
			showMessageDialog(null, "Departamento com esse ID ja existente. ");
		} else {
			dao.inserir(departamento);
		}
	}

	// metodo para gerar o menu da aplicacao
	private String gerarMenu() {
		String menu = "CONTROLE DE EMPREGADOS - ESCOLHA UMA OPÇÃO\n";
		menu += "1. Cadastrar departamento\n";
		menu += "2. Cadastrar empregado\n";
		menu += "3. Pesquisar empregado\n";
		menu += "4. Listar empregado\n";
		menu += "5. Atualizar empregado\n";
		menu += "6. Excluir empregado\n";
		menu += "7. Finalizar\n";
		return menu;
	}
}
