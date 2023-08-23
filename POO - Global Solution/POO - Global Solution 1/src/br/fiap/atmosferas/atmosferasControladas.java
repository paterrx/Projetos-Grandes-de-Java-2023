package br.fiap.atmosferas;

public class atmosferasControladas {
	private double nivelOxigenio;
    private double nivelDioxidoCarbono;
    private double nivelNitrogenio;
    
	public atmosferasControladas(double nivelOxigenio, double nivelDioxidoCarbono,
			double nivelNitrogenio) {
		this.nivelOxigenio = nivelOxigenio;
		this.nivelDioxidoCarbono = nivelDioxidoCarbono;
		this.nivelNitrogenio = nivelNitrogenio;
	}
	
	public double getNivelOxigenio() {
		return nivelOxigenio;
	}

	public double getNivelDioxidoCarbono() {
		return nivelDioxidoCarbono;
	}

	public double getNivelNitrogenio() {
		return nivelNitrogenio;
	}

	@Override
	public String toString() {
		return "atmosferasControladas [nivelOxigenio=" + nivelOxigenio
				+ ", nivelDioxidoCarbono=" + nivelDioxidoCarbono + ", nivelNitrogenio=" + nivelNitrogenio
				+ ", tipoAlimentoArmazenado=" + "]";
	}
    
}
