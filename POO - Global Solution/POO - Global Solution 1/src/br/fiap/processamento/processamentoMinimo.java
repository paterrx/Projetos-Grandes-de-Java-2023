package br.fiap.processamento;

public class processamentoMinimo {
	private boolean cortar;
    private boolean lavar;
    private boolean embalarPorcoesSeparadas;
    
	public processamentoMinimo(boolean cortar, boolean lavar, boolean embalarPorcoesSeparadas) {
		this.cortar = cortar;
		this.lavar = lavar;
		this.embalarPorcoesSeparadas = embalarPorcoesSeparadas;
	}
	
	public boolean isCortar() {
		return cortar;
	}

	public boolean isLavar() {
		return lavar;
	}

	public boolean isEmbalarPorcoesSeparadas() {
		return embalarPorcoesSeparadas;
	}

	@Override
	public String toString() {
		return "processamentoMinimo [cortar=" + cortar + ", lavar=" + lavar
				+ ", embalarPorcoesSeparadas=" + embalarPorcoesSeparadas + "]";
	}
    
}
