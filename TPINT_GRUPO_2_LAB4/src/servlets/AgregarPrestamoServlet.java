package servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Persona;
import entidad.Prestamo;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class AgregarPrestamo
 */
@WebServlet("/AgregarPrestamoServlet")
public class AgregarPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarPrestamoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OK");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Double vp = Double.parseDouble(req.getParameter("montoPrestamo")); // Importe solicitado
		int nper = Integer.parseInt(req.getParameter("plazo")); // Periodo en meses
		// TODO Crear tabla singleton en db para guardar TNA
		Double tna = 0.9168; // Tasa Nominal Anual
		
		PrestamoNegocio pn = new PrestamoNegocioImpl();
		
		try {
			if (vp < 0) throw new IllegalArgumentException();
			Prestamo p = new Prestamo();
			Persona persona = new Persona();
			persona.setDNI(Integer.parseInt(req.getParameter("dni")));
			p.setPersona(persona);
			p.setTna(tna);
			p.setFecha(LocalDate.now());
			p.setImporteSolicitado(vp);
			p.setPlazoPagoMeses(nper);
			p.setCuotasRestantes(nper);
			p.setAprobado(false);
			p.setEstado(true);
			
			// Devolver codigo 201
			if(pn.insert(p)) res.setStatus(HttpServletResponse.SC_CREATED);
			else res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
		} catch (Exception e) {

			req.setAttribute("estado", "Error al solicitar prestamo");
		}
		
	}
}
