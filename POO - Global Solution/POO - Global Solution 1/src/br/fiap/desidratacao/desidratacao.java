package br.fiap.desidratacao;

public class desidratacao {
	private double temperatura;
	private double tempoDesidratado;
	private boolean usoMicroondas;
	private boolean usoEstufa;
    private boolean secagemAoArLivre;
    private boolean liofilizacao;
    
	public desidratacao(double temperatura, double tempoDesidratado, boolean usoMicroondas,
			boolean usoEstufa, boolean secagemAoArLivre, boolean liofilizacao) {
		this.temperatura = temperatura;
		this.tempoDesidratado = tempoDesidratado;
		this.usoMicroondas = usoMicroondas;
		this.usoEstufa = usoEstufa;
		this.secagemAoArLivre = secagemAoArLivre;
		this.liofilizacao = liofilizacao;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public double getTempoDesidratado() {
		return tempoDesidratado;
	}

	public boolean isUsoMicroondas() {
		return usoMicroondas;
	}

	public boolean isUsoEstufa() {
		return usoEstufa;
	}

	public boolean isSecagemAoArLivre() {
		return secagemAoArLivre;
	}

	public boolean isLiofilizacao() {
		return liofilizacao;
	}

	@Override
	public String toString() {
		return "desidratacao [temperatura=" + temperatura
				+ ", tempoDesidratado=" + tempoDesidratado + ", usoMicroondas=" + usoMicroondas + ", usoEstufa="
				+ usoEstufa + ", secagemAoArLivre=" + secagemAoArLivre + ", liofilizacao=" + liofilizacao + "]";
	}
	
}
