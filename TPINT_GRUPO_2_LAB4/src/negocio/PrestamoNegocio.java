package negocio;

import java.util.ArrayList;

import entidad.Prestamo;

public interface PrestamoNegocio{
	public boolean insert(Prestamo p);
	public boolean delete(Prestamo p);
	public boolean update(Prestamo p);
	public Prestamo getPrestamoByID(int id);
	public ArrayList<Prestamo> readAll();
	public int cantidad_prestamosSolicitados(String desde, String hasta);
	public int cantidad_prestamosAprobados(String desde,String hasta);
	public Double promedio_prestamos(String desde, String hasta);
}