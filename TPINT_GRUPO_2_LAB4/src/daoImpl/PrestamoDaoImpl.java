package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.PrestamoDao;
import entidad.Localidad;
import entidad.Persona;
import entidad.Prestamo;
import entidad.Usuario;

public class PrestamoDaoImpl implements PrestamoDao{

	@Override
	public boolean insert(Prestamo prestamo) {
		PreparedStatement st;
		Connection con = Conexion.getConexion().getSQLConexion();
		try {
			st = con.prepareStatement("INSERT into prestamos values(?,?,?,?,?,?,?,?,?)");
			
			
			if (prestamo.getIDPrestamo() == null) {
				st.setNull(1, Types.INTEGER);
			}
			else st.setLong(1, prestamo.getIDPrestamo());
			
			st.setLong(2, prestamo.getPersona().getDNI());
			
			st.setDouble(3, prestamo.getTna());
			
			st.setDate(4, Date.valueOf(prestamo.getFecha()));
			
			st.setDouble(5, prestamo.getImporteSolicitado());
			
			st.setInt(6, prestamo.getPlazoPagoMeses());
			
			st.setInt(7, prestamo.getCuotasRestantes());
			
			st.setBoolean(8, prestamo.isAprobado());
			
			st.setBoolean(9, prestamo.getEstado());
			
			System.out.println(st);
			
			if(st.executeUpdate() > 0) {
				con.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return false;
	}

	@Override
	public boolean delete(Prestamo prestamo) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update prestamos Set Estado=? where IDPrestamo=?");
			statement.setBoolean(1, false);
			statement.setInt(2, prestamo.getIDPrestamo());
			
			if(statement.executeUpdate() > 0) {
				con.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public boolean update(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Prestamo getPrestamoByID(int idPrestamo) {
		Prestamo p = new Prestamo();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prestamos WHERE IDPrestamo = " + idPrestamo);
			while (rs.next()) {
				if (rs.getBoolean("Estado")) {
					Persona persona = new Persona();
					p.setAprobado(rs.getBoolean("Aprobado"));
					p.setCuotasRestantes(rs.getInt("CuotasRestantes"));
					p.setEstado(rs.getBoolean("Estado"));
					p.setFecha(rs.getDate("Fecha").toLocalDate());
					p.setIDPrestamo((int) rs.getLong("IDPrestamo"));
					p.setImporteSolicitado(rs.getDouble("ImporteSolicitado"));
					persona.setDNI((int) rs.getLong("DNI"));
					p.setPersona(persona);
					p.setPlazoPagoMeses(rs.getInt("PlazoPagoMeses"));
					p.setTna(rs.getDouble("tna"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public ArrayList<Prestamo> readAll() {
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prestamos");
			while(rs.next()) {
				if (rs.getBoolean("Estado")) {
					Prestamo p = new Prestamo();
					Persona persona = new Persona();
					p.setAprobado(rs.getBoolean("Aprobado"));
					p.setCuotasRestantes(rs.getInt("CuotasRestantes"));
					p.setEstado(rs.getBoolean("Estado"));
					p.setFecha(rs.getDate("Fecha").toLocalDate());
					p.setIDPrestamo((int) rs.getLong("IDPrestamo"));
					p.setImporteSolicitado(rs.getDouble("ImporteSolicitado"));
					persona.setDNI((int) rs.getLong("DNI"));
					p.setPersona(persona);
					p.setPlazoPagoMeses(rs.getInt("PlazoPagoMeses"));
					p.setTna(rs.getDouble("tna"));
					prestamos.add(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prestamos;
	}

	@Override
	public int cantidad_prestamosSolicitados(String desde, String hasta) {
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		int cant = 0;
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT count(*) as cant FROM prestamos where fecha >="+desde+" and fecha <="+hasta+"");
			while(rs.next()) {
				cant = rs.getInt("cant");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cant;
	}

	@Override
	public int cantidad_prestamosAprobados(String desde, String hasta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int cant = 0;
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT count(*) as cant FROM prestamos where aprobados = true");
			while(rs.next()) {
				cant = rs.getInt("cant");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cant;
	}

	@Override
	public Double promedio_prestamos(String desde, String hasta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		Double cant = (double) 0;
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT avg(ImportaSolicitado) as cant FROM prestamos where aprobados = true");
			while(rs.next()) {
				cant = rs.getDouble("cant");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cant;
		
	}
	
}