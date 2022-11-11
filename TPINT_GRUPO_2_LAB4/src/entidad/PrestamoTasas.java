package entidad;

public class PrestamoTasas {
	private double tem;
	private double tea;
	private double cfttea;

	public PrestamoTasas(double tem, double tea, double cfttea) {
		super();
		this.tem = tem;
		this.tea = tea;
		this.cfttea = cfttea;
	}

	public double getTem() {
		return tem;
	}

	public void setTem(double tem) {
		this.tem = tem;
	}

	public double getTea() {
		return tea;
	}

	public void setTea(double tea) {
		this.tea = tea;
	}

	public double getCfttea() {
		return cfttea;
	}

	public void setCfttea(double cfttea) {
		this.cfttea = cfttea;
	}
}