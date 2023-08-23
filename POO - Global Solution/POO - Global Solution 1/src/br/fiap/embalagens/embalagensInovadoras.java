package br.fiap.embalagens;

public class embalagensInovadoras {
	private String tipoEmbalagem;
    private double temperaturaArmazenamento;
    private double umidadeArmazenamento;
    private boolean embalagemAtiva;
    private boolean embalagemInteligente;
    
	public embalagensInovadoras(String tipoEmbalagem, double temperaturaArmazenamento,
			double umidadeArmazenamento, boolean embalagemAtiva, boolean embalagemInteligente) {
		this.tipoEmbalagem = tipoEmbalagem;
		this.temperaturaArmazenamento = temperaturaArmazenamento;
		this.umidadeArmazenamento = umidadeArmazenamento;
		this.embalagemAtiva = embalagemAtiva;
		this.embalagemInteligente = embalagemInteligente;
	}

	public String getTipoEmbalagem() {
		return tipoEmbalagem;
	}

	public double getTemperaturaArmazenamento() {
		return temperaturaArmazenamento;
	}

	public double getUmidadeArmazenamento() {
		return umidadeArmazenamento;
	}

	public boolean isEmbalagemAtiva() {
		return embalagemAtiva;
	}

	public boolean isEmbalagemInteligente() {
		return embalagemInteligente;
	}

	@Override
	public String toString() {
		return "embalagensInovadoras [tipoEmbalagem=" + tipoEmbalagem
				+ ", temperaturaArmazenamento=" + temperaturaArmazenamento + ", umidadeArmazenamento="
				+ umidadeArmazenamento + ", embalagemAtiva=" + embalagemAtiva + ", embalagemInteligente="
				+ embalagemInteligente + "]";
	}
    
}
