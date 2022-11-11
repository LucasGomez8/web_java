package entidad;

import java.time.LocalDate;

public class Movimiento {
	
	private int IdMovimiento;
	private TipoMovimiento Tipo;
	private Cuenta Cuenta;
	private LocalDate Fecha;
	private double Importe;
	
	public Movimiento() {
		
	}
	
	public Movimiento(int idMovimiento, TipoMovimiento tipo, Cuenta cuenta, LocalDate fecha, double importe) {
		this.IdMovimiento = idMovimiento;
		this.Tipo = tipo;
		this.Cuenta = cuenta;
		this.Fecha = fecha;
		this.Importe = importe;
	}

	public int getIdMovimiento() {
		return IdMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		IdMovimiento = idMovimiento;
	}

	public TipoMovimiento getTipo() {
		return Tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		Tipo = tipo;
	}

	public Cuenta getCuenta() {
		return Cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		Cuenta = cuenta;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}

	public double getImporte() {
		return Importe;
	}

	public void setImporte(double importe) {
		Importe = importe;
	}

	@Override
	public String toString() {
		return "Movimiento [IdMovimiento=" + IdMovimiento + ", Tipo=" + Tipo + ", Cuenta=" + Cuenta + ", Fecha=" + Fecha
				+ ", Importe=" + Importe + "]";
	}
	
}
