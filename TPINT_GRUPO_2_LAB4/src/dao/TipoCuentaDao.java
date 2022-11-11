package dao;

import java.util.ArrayList;

import entidad.TipoCuenta;

public interface TipoCuentaDao {
	public boolean insert(TipoCuenta tp);
	public boolean delete(TipoCuenta tp);
	public boolean update(TipoCuenta tp);
	public ArrayList<TipoCuenta> filter(int id);
	public ArrayList<TipoCuenta> readAll();
}
