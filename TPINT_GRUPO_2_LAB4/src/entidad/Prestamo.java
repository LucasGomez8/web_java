package entidad;

import java.time.LocalDate;

public class Prestamo {
	private Integer IDPrestamo;
	private Persona Persona;
	private LocalDate Fecha;
	// private double ImporteAPagar;
	private double ImporteSolicitado;
	private int PlazoPagoMeses;
	// private double CuotaMensual;
	private double tna;
	private int CuotasRestantes;
	private boolean Aprobado;
	private boolean Estado;

	public Prestamo() {

	}

	public Prestamo(Integer iDPrestamo, entidad.Persona persona, LocalDate fecha, /* double importeAPagar, */
			double importeSolicitado, int plazoPagoMeses, /* double cuotaMensual, */ double tna, int cuotasRestantes,
			boolean aprobado) {
		this.IDPrestamo = iDPrestamo;
		this.Persona = persona;
		this.Fecha = fecha;
		// this.ImporteAPagar = importeAPagar;
		this.ImporteSolicitado = importeSolicitado;
		this.PlazoPagoMeses = plazoPagoMeses;
		// this.CuotaMensual = cuotaMensual;
		this.tna = tna;
		this.CuotasRestantes = cuotasRestantes;
		this.Aprobado = aprobado;
	}

	public Integer getIDPrestamo() {
		return IDPrestamo;
	}

	public void setIDPrestamo(Integer iDPrestamo) {
		IDPrestamo = iDPrestamo;
	}

	public Persona getPersona() {
		return Persona;
	}

	public void setPersona(Persona persona) {
		Persona = persona;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}

	/*
	 * public double getImporteAPagar() { return ImporteAPagar; }
	 * 
	 * public void setImporteAPagar(double importeAPagar) { ImporteAPagar =
	 * importeAPagar; }
	 */

	public double getImporteSolicitado() {
		return ImporteSolicitado;
	}

	public void setImporteSolicitado(double importeSolicitado) {
		ImporteSolicitado = importeSolicitado;
	}

	public int getPlazoPagoMeses() {
		return PlazoPagoMeses;
	}

	public void setPlazoPagoMeses(int plazoPagoMeses) {
		PlazoPagoMeses = plazoPagoMeses;
	}

	/*
	 * public double getCuotaMensual() { return CuotaMensual; }
	 * 
	 * public void setCuotaMensual(double cuotaMensual) { CuotaMensual =
	 * cuotaMensual; }
	 */

	public double getTna() {
		return tna;
	}

	public void setTna(double tna) {
		this.tna = tna;
	}

	public int getCuotasRestantes() {
		return CuotasRestantes;
	}

	public void setCuotasRestantes(int cuotasRestantes) {
		CuotasRestantes = cuotasRestantes;
	}

	public boolean isAprobado() {
		return Aprobado;
	}

	public void setAprobado(boolean aprobado) {
		Aprobado = aprobado;
	}

	public boolean getEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Prestamo [IDPrestamo=" + IDPrestamo + ", Fecha=" + Fecha + /*
																			 * ", ImporteAPagar=" + ImporteAPagar +
																			 */ ", ImporteSolicitado="
				+ ImporteSolicitado + ", PlazoPagoMeses=" + PlazoPagoMeses + /*
																				 * ", CuotaMensual=" + CuotaMensual +
																				 */ ", CuotasRestantes="
				+ CuotasRestantes + ", Aprobado=" + Aprobado + "]";
	}

}
