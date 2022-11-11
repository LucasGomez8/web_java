package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import dao.UsuarioDao;
import entidad.Cuenta;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Rol;
import entidad.TipoCuenta;
import entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	
	private Cuenta cuenta;
	private Usuario usuario;
	private Persona persona;
	private Pais pais;
	private Localidad localidad;
	private Direccion direccion;
	private Rol rol;
	private TipoCuenta tipoCuenta;

	@Override
	public boolean insert(Usuario usuario) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("INSERT into Usuarios values(?,?,?,?,?)");
			
			statement.setInt(1, usuario.getPersona().getDNI());
			statement.setString(2, usuario.getUser());
			statement.setString(3, usuario.getPass());
			statement.setInt(4, usuario.getRol().getIDRol());
			statement.setBoolean(5, usuario.isEstado());
			
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
	public boolean delete(Usuario user) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Usuarios Set Estado=? where DNI=?");
			statement.setBoolean(1, false);
			statement.setInt(2, user.getPersona().getDNI());
			
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
	public boolean update(Usuario user) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Usuarios SET Usuario=?, Contrasena=?, IDRol=?, Estado=? Where DNI=?");
			statement.setString(1, user.getUser());
			statement.setString(2, user.getPass());
			statement.setInt(3, user.getRol().getIDRol());
			statement.setBoolean(4, user.isEstado());
			statement.setInt(5, user.getPersona().getDNI());
			
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
	public Usuario getUserByID(int dni) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select pe.DNI, u.Usuario, u.Contrasena, u.IDRol, r.Descripcion DescripcionRol, u.Estado EstadoUsuario ,pe.IDPais, pa.Descripcion DescripcionPais, pe.Sexo,\r\n" + 
					"d.IDDireccion, d.CP, d.Descripcion DescripcionDireccion, pe.CUIL, pe.Nombre, pe.Apellido, pe.FechaNac, pe.Correo, pe.Estado EstadoPersona\r\n" + 
					"From Personas pe Inner Join Usuarios u on pe.DNI = u.DNI\r\n" + 
					"Inner Join Roles r on u.IDRol = r.IDRol\r\n" + 
					"Inner Join Paises pa on pe.IDPais = pa.IDPais\r\n" + 
					"Inner Join Direcciones d on pe.IDDireccion = d.IDDireccion AND pe.CP = d.CP\r\n" + 
					"Where pe.DNI = " + dni);
			if(rs.next()) {
				inicializar();
				
				persona.setDNI(rs.getInt("DNI"));
				usuario.setUser(rs.getString("Usuario"));
				usuario.setPass(rs.getString("Contrasena"));
				rol.setIDRol(rs.getInt("IDRol"));
				rol.setDescripcion(rs.getString("DescripcionRol"));
				usuario.setRol(rol);
				usuario.setEstado(rs.getBoolean("EstadoUsuario"));
				pais.setIdPais(rs.getInt("IDPais"));
				pais.setDescripcion(rs.getString("DescripcionPais"));
				persona.setPais(pais);
				persona.setSexo(rs.getString("Sexo"));
				direccion.setIdDireccion(rs.getInt("IDDireccion"));
				direccion.setLocalidad(new Localidad(rs.getString("CP"), pais, ""));
				direccion.setDescripicion(rs.getString("DescripcionDireccion"));
				persona.setDireccion(direccion);
				persona.setCUIL(rs.getString("CUIL"));
				persona.setNombre(rs.getString("Nombre"));
				persona.setApellido(rs.getString("Apellido"));
				persona.setFechaNac(LocalDate.parse(rs.getString("FechaNac")));
				persona.setCorreo(rs.getString("Correo"));
				persona.setEstado(rs.getBoolean("EstadoPersona"));
				usuario.setPersona(persona);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return usuario;
	}
	
	//COMBO BOXES
	public ArrayList<Usuario> ComboClientes(){
		ArrayList<Usuario> tClientes = new ArrayList<Usuario>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select u.Dni udni, u.usuario, u.contrasena, pe.nombre, pe.apellido, pe.sexo, pe.correo, pe.CP, pe.iddireccion, pe.idpais, pe.fechanac, pe.cuil, u.estado "
					+ "from Usuarios u "
					+ "inner join personas pe on pe.dni = u.dni "
					+ " where u.idrol = 2 ");
			while(rs.next()) {
				inicializar();
				persona.setDNI(rs.getInt("udni"));
				persona.setSexo(rs.getString("Sexo"));
				pais.setIdPais(rs.getInt("idpais"));
				direccion.setIdDireccion(rs.getInt("iddireccion"));
				localidad.setCP(rs.getString("CP"));
				direccion.setLocalidad(localidad);
				
				persona.setLoca(localidad);
				persona.setDireccion(direccion);
				persona.setNombre(rs.getString("nombre"));
				persona.setApellido(rs.getString("apellido"));
				persona.setCorreo(rs.getString("correo"));
				persona.setFechaNacimientoPrueba(rs.getDate("fechanac"));
				persona.setCUIL(rs.getString("cuil"));
				usuario.setPersona(persona);
				usuario.setUser(rs.getString("usuario"));
				usuario.setPass(rs.getString("contrasena"));
				usuario.setEstado(rs.getBoolean("estado"));
				
				
				tClientes.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tClientes;
		
	}
	
	@Override
	public ArrayList<Usuario> readAll() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select pe.DNI, u.Usuario, u.Contrasena, u.IDRol, r.Descripcion DescripcionRol, u.Estado EstadoUsuario ,pe.IDPais, pa.Descripcion DescripcionPais, pe.Sexo,\r\n" + 
					"d.IDDireccion, d.CP, d.Descripcion DescripcionDireccion, pe.CUIL, pe.Nombre, pe.Apellido, pe.FechaNac, pe.Correo, pe.Estado EstadoPersona\r\n" + 
					"From Personas pe Inner Join Usuarios u on pe.DNI = u.DNI\r\n" + 
					"Inner Join Roles r on u.IDRol = r.IDRol\r\n" + 
					"Inner Join Paises pa on pe.IDPais = pa.IDPais\r\n" + 
					"Inner Join Direcciones d on pe.IDDireccion = d.IDDireccion AND pe.CP = d.CP");
			while(rs.next()) {
				inicializar();
				
				persona.setDNI(rs.getInt("DNI"));
				usuario.setUser(rs.getString("Usuario"));
				usuario.setPass(rs.getString("Contrasena"));
				rol.setIDRol(rs.getInt("IDRol"));
				rol.setDescripcion(rs.getString("DescripcionRol"));
				usuario.setRol(rol);
				usuario.setEstado(rs.getBoolean("EstadoUsuario"));
				pais.setIdPais(rs.getInt("IDPais"));
				pais.setDescripcion(rs.getString("DescripcionPais"));
				persona.setPais(pais);
				persona.setSexo(rs.getString("Sexo"));
				direccion.setIdDireccion(rs.getInt("IDDireccion"));
				direccion.setLocalidad(new Localidad(rs.getString("CP"), pais, ""));
				direccion.setDescripicion(rs.getString("DescripcionDireccion"));
				persona.setDireccion(direccion);
				persona.setCUIL(rs.getString("CUIL"));
				persona.setNombre(rs.getString("Nombre"));
				persona.setApellido(rs.getString("Apellido"));
				persona.setFechaNac(LocalDate.parse(rs.getString("FechaNac")));
				persona.setCorreo(rs.getString("Correo"));
				persona.setEstado(rs.getBoolean("EstadoPersona"));
				usuario.setPersona(persona);
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
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
	public int reporteCantidadUsuarios(String desde, String hasta) {
		return 0;
	}
	
}
