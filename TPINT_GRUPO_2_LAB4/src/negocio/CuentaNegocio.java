package negocio;

import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaNegocio {
	public boolean insert(Cuenta cuenta);
	public boolean delete(Cuenta cuenta);
	public boolean update(Cuenta cuenta);
	public Cuenta getCuentaByID(int numeroCuenta);
	public ArrayList<Cuenta> getCuentaByUser(int dni);
	public ArrayList<Cuenta> readAll();
}
