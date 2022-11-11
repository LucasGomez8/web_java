package servlets;

import java.io.IOException;
import java.time.LocalDateTime;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.scene.layout.region.Margins.Converter;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Pais;
import entidad.Persona;
import entidad.TipoCuenta;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class CuentasServlet
 */
@WebServlet("/CuentasServlet")
public class CuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuentasServlet() {
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
		
		if(request.getParameter("btnEditar") != null) {
			CuentaNegocio cn = new CuentaNegocioImpl();
			try {
				Cuenta cuenta = (Cuenta)request.getSession().getAttribute("cuenta");
				if(request.getParameter("Cliente") != "" && request.getParameter("Saldo") != "") {
					//Cuenta u = 
					//cuenta.setTipoCuenta(new TipoCuenta(0, request.getParameter("SelTipoCuenta")));
					cuenta.setNumeroCuenta(Integer.parseInt(request.getParameter("nroCuenta")));
					cuenta.setTipoCuenta(new TipoCuenta(Integer.parseInt(request.getParameter("SelTipoCuenta")),""));
					cuenta.setSaldo(Double.parseDouble(request.getParameter("Saldo")));
					//int i = Integer.parseInt(request.getParameter("SelEstado"));
					cuenta.setEstado(Boolean.parseBoolean(request.getParameter("SelEstado")));
					request.setAttribute("Estado", cuenta.isEstado());
					request.setAttribute("updated", cn.update(cuenta));
					request.setAttribute("edit", "Modificado con exito");
					//request.getSession().setAttribute("cuentaModificada", cuenta);
				}
			} catch (Exception e) {

				request.setAttribute("edit", "Error al modificar");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnCrear") != null) {
			
			insertarCuenta(request, response);
		}
		
		if(request.getParameter("btnEliminar")!=null) {
			eliminarCuenta(request, response);
		}
	}

	
	protected void insertarCuenta(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		CuentaNegocio cn = new CuentaNegocioImpl();
		Usuario uPrueba = new Usuario();
		Persona pPrueba = new Persona();
		TipoCuenta tc = new TipoCuenta();
		Cuenta cta = new Cuenta();
		try {
			pPrueba.setDNI(Integer.parseInt(request.getParameter("new_person")));
			uPrueba.setPersona(pPrueba);
			cta.setUsuario(uPrueba);
			cta.setCBU(request.getParameter("new_cbu"));
			cta.setNumeroCuenta(Integer.parseInt(request.getParameter("new_cuenta")));
			tc.setIDTipoCuenta(Integer.parseInt(request.getParameter("tipocuenta")));
			cta.setTipoCuenta(tc);
			cta.setFechaCreacion(LocalDateTime.now());
			cta.setEstado(true);
			cta.setSaldo(0);
			request.setAttribute("inserted", cn.insert(cta));
			request.setAttribute("creado", "creado con exito");
			
		} catch (Exception e) {

			request.setAttribute("creado", "Ocurrio un error al crear la cuenta");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
		rd.forward(request, response);

	}
	
	protected void eliminarCuenta(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		CuentaNegocio cn = new CuentaNegocioImpl();
		try {
			Cuenta cuenta = new Cuenta();
			if(request.getParameter("idCuenta") != "") {
				cuenta.setNumeroCuenta(Integer.parseInt(request.getParameter("idCuenta")));
				cuenta.setEstado(false);
				//request.setAttribute("Estado", cuenta.isEstado());
				request.setAttribute("deleted", cn.delete(cuenta));
				request.setAttribute("msgDeleted", "Eliminado con exito");
			}
		} catch (Exception e) {

			request.setAttribute("msgDeleted", "Ocurrio un error al eliminar");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
		rd.forward(request, response);
	}

}
