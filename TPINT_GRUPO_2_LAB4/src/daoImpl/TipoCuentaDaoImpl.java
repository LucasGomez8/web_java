package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.TipoCuentaDao;
import entidad.Cuenta;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Rol;
import entidad.TipoCuenta;
import entidad.Usuario;

public class TipoCuentaDaoImpl implements TipoCuentaDao{
	
	private TipoCuenta tipoCuenta;

	@Override
	public boolean insert(TipoCuenta tp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TipoCuenta tp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TipoCuenta tp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<TipoCuenta> filter(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TipoCuenta> readAll() {
		ArrayList<TipoCuenta> tList = new ArrayList<TipoCuenta>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select TipoCuenta, Descripcion from TipoCuentas");
			while(rs.next()) {
				initialiaze();
				tipoCuenta.setIDTipoCuenta(rs.getInt("TipoCuenta"));
				tipoCuenta.setDescripcion(rs.getString("Descripcion"));
				
				tList.add(tipoCuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tList;
	}
		
	
	public void initialiaze() {
		tipoCuenta = new TipoCuenta();
	}

}
