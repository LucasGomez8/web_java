package negocioImpl;

import java.util.ArrayList;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {

	@Override
	public boolean insert(Prestamo p) {
		PrestamoDao dao = new PrestamoDaoImpl();
		if(dao.insert(p)) return true;
		return false;
	}

	@Override
	public boolean delete(Prestamo p) {
		PrestamoDao dao = new PrestamoDaoImpl();
		if(dao.delete(p)) return true;
		return false;
	}

	@Override
	public boolean update(Prestamo p) {
		PrestamoDao dao = new PrestamoDaoImpl();
		if(dao.update(p)) return true;
		return false;
	}

	@Override
	public Prestamo getPrestamoByID(int id) {
		PrestamoDao dao = new PrestamoDaoImpl();
		return dao.getPrestamoByID(id);
	}

	@Override
	public ArrayList<Prestamo> readAll() {
		PrestamoDao dao = new PrestamoDaoImpl();
		return dao.readAll();
	}

	@Override
	public int cantidad_prestamosSolicitados(String desde, String hasta) {
		PrestamoDao dao = new PrestamoDaoImpl();
		int num = dao.cantidad_prestamosSolicitados(desde, hasta);
		
		return num;
	}

	@Override
	public int cantidad_prestamosAprobados(String desde, String hasta) {
		PrestamoDao dao = new PrestamoDaoImpl();
		int n = dao.cantidad_prestamosAprobados(desde, hasta);
		return n;
	}

	@Override
	public Double promedio_prestamos(String desde, String hasta) {
		PrestamoDao dao = new PrestamoDaoImpl();
		Double n = dao.promedio_prestamos(desde, hasta);
		return n;
	}

}