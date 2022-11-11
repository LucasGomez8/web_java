package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.PersonaDaoImpl;
import entidad.Cuenta;
import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Rol;
import entidad.TipoCuenta;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.PersonaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PersonaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ClientesServlet
 */
@WebServlet("/ClientesServlet")
public class ClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("crearUsuario") != null) {
			insertar(request, response);
		}
		
		if(request.getParameter("eliminarUsuario") != null) {
			eliminar(request, response);
		}
		
		if(request.getParameter("Modificar") != null) {
			update(request, response);
		}
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioNegocio uNegocio = new UsuarioNegocioImpl();
		Usuario user = new Usuario();
		Persona p = new Persona();
		try {
			
			p.setDNI(Integer.parseInt(request.getParameter("dni")));
			user.setPersona(p);
			user.setEstado(false);
			
			if(uNegocio.delete(user)) {
				request.setAttribute("eliminarUsuario", "Se ha dado de baja con exito");
			}
			
			
		} catch (Exception e) {

			request.setAttribute("eliminarUsuario", "Ocurrio un erorr al eliminar");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
		rd.forward(request, response);

	}
	
	
	protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonaNegocio pNegocio = new PersonaNegocioImpl();
		UsuarioNegocio uNegocio = new UsuarioNegocioImpl();
		Usuario user = new Usuario();
		Persona pPersona = new Persona();
		try {
			
			pPersona.setApellido(request.getParameter("txtApellidos"));
			pPersona.setNombre(request.getParameter("txtNombres"));
			pPersona.setDNI(Integer.parseInt(request.getParameter("txtDNI")));
			pPersona.setSexo(request.getParameter("sexo"));
			pPersona.setFechaNac(LocalDate.parse(request.getParameter("fecnac"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			pPersona.setCorreo(request.getParameter("email"));
			pPersona.setPais(new Pais());
			pPersona.getPais().setIdPais(Integer.parseInt(request.getParameter("nacionalidad")));
			pPersona.setCUIL(request.getParameter("cuil"));
			pPersona.setDireccion(new Direccion());
			pPersona.getDireccion().setIdDireccion(Integer.parseInt(request.getParameter("direccion")));
			pPersona.getDireccion().setLocalidad(new Localidad());
			pPersona.getDireccion().getLocalidad().setCP(request.getParameter("localidad"));
			pPersona.setEstado(true);
			
			if(pNegocio.insert(pPersona)) {
				
				user.setEstado(true);
				user.setPersona(pPersona);
				user.setRol(new Rol());
				user.getRol().setIDRol(Integer.parseInt(request.getParameter("tiporol")));
				user.setUser(request.getParameter("username"));
				user.setPass(request.getParameter("password"));
				
				if(uNegocio.insert(user)) {
					request.setAttribute("usuarioCreado", "Se ha Creado el usuario con exito");
				}
			}
			
			
		} catch (Exception e) {
			
			request.setAttribute("usuarioCreado", "Ocurrio un error al crear la cuenta");
			throw e;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
		rd.forward(request, response);

		
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PersonaNegocio pNegocio = new PersonaNegocioImpl();
		UsuarioNegocio uNegocio = new UsuarioNegocioImpl();
		Usuario user = new Usuario();
		Persona pPersona = new Persona();
		try {
			
			pPersona.setApellido(request.getParameter("txtApellido"));
			pPersona.setNombre(request.getParameter("txtNombre"));
			pPersona.setDNI(Integer.parseInt(request.getParameter("txtDNI")));
			pPersona.setSexo(request.getParameter("sexo"));
			pPersona.setFechaNac(LocalDate.parse(request.getParameter("fecnac"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			pPersona.setCorreo(request.getParameter("email"));
			pPersona.setPais(new Pais());
			pPersona.getPais().setIdPais(Integer.parseInt(request.getParameter("nacionalidad")));
			pPersona.setDireccion(new Direccion());
			pPersona.getDireccion().setIdDireccion(Integer.parseInt(request.getParameter("direccion")));
			pPersona.getDireccion().setLocalidad(new Localidad());
			pPersona.getDireccion().getLocalidad().setCP(request.getParameter("localidad"));
			pPersona.setEstado(true);
			
			if(pNegocio.update(pPersona)) {
				
				user.setEstado(true);
				user.setPersona(pPersona);
				user.setRol(new Rol());
				user.getRol().setIDRol(Integer.parseInt(request.getParameter("tiporol")));
				user.setUser(request.getParameter("username"));
				user.setPass(request.getParameter("password"));
				
				if(uNegocio.update(user)) {
					request.setAttribute("modificado", "Se ha Modificado el usuario con exito");
				}
			}
			
			
		} catch (Exception e) {
			
			request.setAttribute("modificado", "Ocurrio un error al crear la cuenta");
			throw e;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
		rd.forward(request, response);
	}

}
