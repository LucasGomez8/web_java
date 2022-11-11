package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.CuentaDao;
import entidad.Cuenta;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Rol;
import entidad.TipoCuenta;
import entidad.Usuario;


public class CuentaDaoImpl implements CuentaDao {
	private Cuenta cuenta;
	private Usuario usuario;
	private Persona persona;
	private Pais pais;
	private Localidad localidad;
	private Direccion direccion;
	private Rol rol;
	private TipoCuenta tipoCuenta;
	
	@Override
	public boolean insert(Cuenta cuenta) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT into cuentas values(?,?,?,?,?,?,?)");
			statement.setInt(1, cuenta.getNumeroCuenta());
			statement.setInt(2, cuenta.getUsuario().getPersona().getDNI());
			statement.setString(3, cuenta.getCBU());
			statement.setInt(4, cuenta.getTipoCuenta().getIDTipoCuenta());
			statement.setString(5, cuenta.getFechaCreacion().toString());
			statement.setDouble(6, cuenta.getSaldo());
			statement.setBoolean(7, cuenta.isEstado());
			
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
	public boolean delete(Cuenta cuenta) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Cuentas Set Estado=? where NumeroCuenta=?");
			statement.setBoolean(1, false);
			statement.setInt(2, cuenta.getNumeroCuenta());
			
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
	public boolean update(Cuenta cuenta) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Cuentas SET DNI=?, TipoCuenta=?, Saldo=?, Estado=? Where NumeroCuenta=?");
			statement.setInt(1, cuenta.getUsuario().getPersona().getDNI());
			statement.setInt(2, cuenta.getTipoCuenta().getIDTipoCuenta());
			statement.setDouble(3, cuenta.getSaldo());
			statement.setBoolean(4, cuenta.isEstado());
			statement.setInt(5, cuenta.getNumeroCuenta());
			
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
	public Cuenta getCuentaByID(int numeroCuenta) {
		//cuenta = new Cuenta();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select c.NumeroCuenta, c.DNI, p.IDPais, p.Descripcion DescripcionPais, c.Sexo, l.CP, l.Descripcion DescripcionLocalidad, \r\n" + 
					"d.IDDireccion, d.Descripcion DescripcionDireccion, pe.CUIL, pe.Nombre, pe.Apellido, pe.FechaNac, pe.Correo, pe.Estado EstadoPersona, \r\n" + 
					"u.Usuario, u.Contrasena Pass, r.IDRol, r.Descripcion DescripcionRol, u.Estado EstadoUsuario, c.CBU, tc.TipoCuenta IDTipoCuenta, \r\n" + 
					"tc.Descripcion DescripcionTipoCuenta, c.FechaCreacion FechaCreacionCuenta, c.Saldo, c.Estado EstadoCuenta \r\n" + 
					"From Cuentas c Inner Join Usuarios u on c.DNI = u.DNI and c.IDPais = u.IDPais and c.Sexo = u.Sexo\r\n" + 
					"Inner Join Personas pe on u.DNI = pe.DNI and u.IDPais = pe.IDPais and u.Sexo = pe.Sexo\r\n" + 
					"Inner Join Paises p on pe.IDPais = p.IDPais\r\n" + 
					"Inner Join Localidades l on p.IDPais = l.IDPais\r\n" + 
					"Inner Join Direcciones d on l.CP = d.CP\r\n" + 
					"Inner Join Roles r on u.IDRol = r.IDRol\r\n" + 
					"Inner Join TipoCuentas tc on c.TipoCuenta = tc.TipoCuenta\r\n" + 
					"Where c.NumeroCuenta = "+numeroCuenta);
			if(rs.next()) {
				inicializar();
				cuenta.setNumeroCuenta(rs.getInt("NumeroCuenta"));
				persona.setDNI(rs.getInt("DNI"));
				pais.setIdPais(rs.getInt("IDPais")); 
				pais.setDescripcion(rs.getString("DescripcionPais"));
				persona.setPais(pais);
				persona.setSexo(rs.getString("Sexo"));
				localidad.setCP(rs.getString("CP"));
				localidad.setPais(pais);
				localidad.setDescripcion(rs.getString("DescripcionLocalidad"));
				direccion.setIdDireccion(rs.getInt("IDDireccion"));
				direccion.setLocalidad(localidad);
				direccion.setDescripicion(rs.getString("DescripcionDireccion"));
				persona.setDireccion(direccion);
				persona.setCUIL(rs.getString("CUIL"));
				persona.setNombre(rs.getString("Nombre"));
				persona.setApellido(rs.getString("Apellido"));
				persona.setFechaNac(LocalDate.parse(rs.getString("FechaNac")));
				persona.setCorreo(rs.getString("Correo"));
				persona.setEstado(rs.getBoolean("EstadoPersona"));
				usuario.setPersona(persona);
				usuario.setUser(rs.getString("Usuario"));
				usuario.setPass(rs.getString("Pass"));
				rol.setIDRol(rs.getInt("IDRol"));
				rol.setDescripcion(rs.getString("DescripcionRol"));
				usuario.setRol(rol);
				usuario.setEstado(rs.getBoolean("EstadoUsuario"));
				cuenta.setUsuario(usuario);
				cuenta.setCBU(rs.getString("CBU"));
				tipoCuenta.setIDTipoCuenta(rs.getInt("IDTipoCuenta"));
				tipoCuenta.setDescripcion(rs.getString("DescripcionTipoCuenta"));
				cuenta.setTipoCuenta(tipoCuenta);
				cuenta.setFechaCreacion(LocalDateTime.parse(rs.getString("FechaCreacionCuenta"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				cuenta.setSaldo(rs.getDouble("Saldo"));
				cuenta.setEstado(rs.getBoolean("EstadoCuenta"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return cuenta;
	}
	

	@Override
	public ArrayList<Cuenta> readAll() {
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select c.NumeroCuenta, c.DNI, p.IDPais, p.Descripcion DescripcionPais, pe.Sexo, l.CP, l.Descripcion DescripcionLocalidad, \r\n" + 
					"d.IDDireccion, d.Descripcion DescripcionDireccion, pe.CUIL, pe.Nombre, pe.Apellido, pe.FechaNac, pe.Correo, pe.Estado EstadoPersona, \r\n" + 
					"u.Usuario, u.Contrasena Pass, r.IDRol, r.Descripcion DescripcionRol, u.Estado EstadoUsuario, c.CBU, tc.TipoCuenta IDTipoCuenta, \r\n" + 
					"tc.Descripcion DescripcionTipoCuenta, c.FechaCreacion FechaCreacionCuenta, c.Saldo, c.Estado EstadoCuenta \r\n" + 
					"From Cuentas c Inner Join Usuarios u on c.DNI = u.DNI\r\n" + 
					"Inner Join Personas pe on u.DNI = pe.DNI\r\n" + 
					"Inner Join Paises p on pe.IDPais = p.IDPais\r\n" + 
					"Inner Join Localidades l on p.IDPais = l.IDPais\r\n" + 
					"Inner Join Direcciones d on l.CP = d.CP\r\n" + 
					"Inner Join Roles r on u.IDRol = r.IDRol\r\n" + 
					"Inner Join TipoCuentas tc on c.TipoCuenta = tc.TipoCuenta");
			while(rs.next()) {
				inicializar();
				
				cuenta.setNumeroCuenta(rs.getInt("NumeroCuenta"));
				persona.setDNI(rs.getInt("DNI"));
				pais.setIdPais(rs.getInt("IDPais")); 
				pais.setDescripcion(rs.getString("DescripcionPais"));
				persona.setPais(pais);
				persona.setSexo(rs.getString("Sexo"));
				localidad.setCP(rs.getString("CP"));
				localidad.setPais(pais);
				localidad.setDescripcion(rs.getString("DescripcionLocalidad"));
				direccion.setIdDireccion(rs.getInt("IDDireccion"));
				direccion.setLocalidad(localidad);
				direccion.setDescripicion(rs.getString("DescripcionDireccion"));
				persona.setDireccion(direccion);
				persona.setCUIL(rs.getString("CUIL"));
				persona.setNombre(rs.getString("Nombre"));
				persona.setApellido(rs.getString("Apellido"));
				persona.setFechaNac(LocalDate.parse(rs.getString("FechaNac")));
				persona.setCorreo(rs.getString("Correo"));
				persona.setEstado(rs.getBoolean("EstadoPersona"));
				usuario.setPersona(persona);
				usuario.setUser(rs.getString("Usuario"));
				usuario.setPass(rs.getString("Pass"));
				rol.setIDRol(rs.getInt("IDRol"));
				rol.setDescripcion(rs.getString("DescripcionRol"));
				usuario.setRol(rol);
				usuario.setEstado(rs.getBoolean("EstadoUsuario"));
				cuenta.setUsuario(usuario);
				cuenta.setCBU(rs.getString("CBU"));
				tipoCuenta.setIDTipoCuenta(rs.getInt("IDTipoCuenta"));
				tipoCuenta.setDescripcion(rs.getString("DescripcionTipoCuenta"));
				cuenta.setTipoCuenta(tipoCuenta);
				cuenta.setFechaCreacion(LocalDateTime.parse(rs.getString("FechaCreacionCuenta"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				//cuenta.setFechaCreacion(LocalDateTime.parse(rs.getString("FechaCreacionCuenta")));
				cuenta.setSaldo(rs.getDouble("Saldo"));
				cuenta.setEstado(rs.getBoolean("EstadoCuenta"));
				cuentas.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
	}
	
	
	
	
	
	public void inicializar() {
		usuario = new Usuario();
		persona = new Persona();
		pais = new Pais();
		localidad = new Localidad();
		direccion = new Direccion();
		rol = new Rol();
		tipoCuenta = new TipoCuenta();
		cuenta = new Cuenta();
	}

	@Override
	public ArrayList<Cuenta> getCuentaByUser(int dni) {
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select c.NumeroCuenta, c.DNI, p.IDPais, p.Descripcion DescripcionPais, pe.Sexo, l.CP, l.Descripcion DescripcionLocalidad, \r\n" + 
					"d.IDDireccion, d.Descripcion DescripcionDireccion, pe.CUIL, pe.Nombre, pe.Apellido, pe.FechaNac, pe.Correo, pe.Estado EstadoPersona, \r\n" + 
					"u.Usuario, u.Contrasena Pass, r.IDRol, r.Descripcion DescripcionRol, u.Estado EstadoUsuario, c.CBU, tc.TipoCuenta IDTipoCuenta, \r\n" + 
					"tc.Descripcion DescripcionTipoCuenta, c.FechaCreacion FechaCreacionCuenta, c.Saldo, c.Estado EstadoCuenta \r\n" + 
					"From Cuentas c Inner Join Usuarios u on c.DNI = u.DNI\r\n" + 
					"Inner Join Personas pe on u.DNI = pe.DNI\r\n" + 
					"Inner Join Paises p on pe.IDPais = p.IDPais\r\n" + 
					"Inner Join Localidades l on p.IDPais = l.IDPais\r\n" + 
					"Inner Join Direcciones d on l.CP = d.CP\r\n" + 
					"Inner Join Roles r on u.IDRol = r.IDRol\r\n" + 
					"Inner Join TipoCuentas tc on c.TipoCuenta = tc.TipoCuenta\r\n" + 
					"Where c.DNI = "+dni);
			while(rs.next()) {
				inicializar();
				
				cuenta.setNumeroCuenta(rs.getInt("NumeroCuenta"));
				persona.setDNI(rs.getInt("DNI"));
				pais.setIdPais(rs.getInt("IDPais")); 
				pais.setDescripcion(rs.getString("DescripcionPais"));
				persona.setPais(pais);
				persona.setSexo(rs.getString("Sexo"));
				localidad.setCP(rs.getString("CP"));
				localidad.setPais(pais);
				localidad.setDescripcion(rs.getString("DescripcionLocalidad"));
				direccion.setIdDireccion(rs.getInt("IDDireccion"));
				direccion.setLocalidad(localidad);
				direccion.setDescripicion(rs.getString("DescripcionDireccion"));
				persona.setDireccion(direccion);
				persona.setCUIL(rs.getString("CUIL"));
				persona.setNombre(rs.getString("Nombre"));
				persona.setApellido(rs.getString("Apellido"));
				persona.setFechaNac(LocalDate.parse(rs.getString("FechaNac")));
				persona.setCorreo(rs.getString("Correo"));
				persona.setEstado(rs.getBoolean("EstadoPersona"));
				usuario.setPersona(persona);
				usuario.setUser(rs.getString("Usuario"));
				usuario.setPass(rs.getString("Pass"));
				rol.setIDRol(rs.getInt("IDRol"));
				rol.setDescripcion(rs.getString("DescripcionRol"));
				usuario.setRol(rol);
				usuario.setEstado(rs.getBoolean("EstadoUsuario"));
				cuenta.setUsuario(usuario);
				cuenta.setCBU(rs.getString("CBU"));
				tipoCuenta.setIDTipoCuenta(rs.getInt("IDTipoCuenta"));
				tipoCuenta.setDescripcion(rs.getString("DescripcionTipoCuenta"));
				cuenta.setTipoCuenta(tipoCuenta);
				cuenta.setFechaCreacion(LocalDateTime.parse(rs.getString("FechaCreacionCuenta"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				//cuenta.setFechaCreacion(LocalDateTime.parse(rs.getString("FechaCreacionCuenta")));
				cuenta.setSaldo(rs.getDouble("Saldo"));
				cuenta.setEstado(rs.getBoolean("EstadoCuenta"));
				cuentas.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
	}
	
}
