package sisme.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sisme.model.entities.SegRole;
import sisme.model.entities.SegUsuario;

/**
 * Session Bean implementation class ManagerUsuarios
 */
@Stateless
@LocalBean
public class ManagerUsuarios {
	@PersistenceContext(unitName="SismeDS")
	private EntityManager em;
	
    public ManagerUsuarios() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Método para comprobar si un usuario puede acceder al sistema
     * @param cedulaUsu
     * @param claveUsu
     * @return Acceso o Denegación al sistema
     * @throws Acceso Incorrecto
     */
    public boolean comprobarUsuario(String cedulaUsu, String claveUsu) throws Exception {
		SegUsuario usuario = em.find(SegUsuario.class, cedulaUsu);
		if (usuario == null)
			throw new Exception("No existe el usuario " + cedulaUsu);
		if (!usuario.getEstadoUsu())
			throw new Exception("El usuario no está activo.");
		if (usuario.getClaveUsu().equals(claveUsu))
			return true;
		throw new Exception("Contraseña no válida.");
	}
    
    public String obtenerRolUsuario(String cedulaUsu) {
    	SegUsuario usuario;
    	usuario=em.find(SegUsuario.class, cedulaUsu);
    	return usuario.getSegRole().getDescripcionRol();
    }
    
    //Listar Administradores
    public List<SegUsuario> findAllAdministradores() {
		Query q;
		List<SegUsuario> listadoAdmin;
		String sentenciaSQL;
		sentenciaSQL = "SELECT u FROM SegUsuario u where u.segRole.idRol=4 ORDER BY u.cedulaUsu";
		q = em.createQuery(sentenciaSQL);
		listadoAdmin = q.getResultList();
		return listadoAdmin;
	}
    
  //Listar Bodegueros
    public List<SegUsuario> findAllBodegueros() {
		Query q;
		List<SegUsuario> listadoBod;
		String sentenciaSQL;
		sentenciaSQL = "SELECT u FROM SegUsuario u where u.segRole=6 ORDER BY u.cedulaUsu";
		q = em.createQuery(sentenciaSQL);
		listadoBod = q.getResultList();
		return listadoBod;
	}
    
  //Listar Recepcionistas
    public List<SegUsuario> findAllRecepcionistas() {
		Query q;
		List<SegUsuario> listadoRecep;
		String sentenciaSQL;
		sentenciaSQL = "SELECT u FROM SegUsuario u where u.segRole=5 ORDER BY u.cedulaUsu";
		q = em.createQuery(sentenciaSQL);
		listadoRecep = q.getResultList();
		return listadoRecep;
	}
    
    // Agregar Usuario (Administrador, Bodeguero, Recepcionista)
    public void agregarUsuario(String cedulaUsu, int idRol, String apellidosUsu, String nombresUsu, String sexoUsu, String direccionUsu, String telefonoUsu, String emailUsu, String claveUsu, boolean estadoUsu) throws Exception {
		SegRole rol;
		rol= em.find(SegRole.class, idRol);	
    	SegUsuario u= new SegUsuario();
		u.setCedulaUsu(cedulaUsu);
		u.setSegRole(rol);
    	u.setApellidosUsu(apellidosUsu);
    	u.setNombresUsu(nombresUsu);
    	u.setSexoUsu(sexoUsu);
    	u.setDireccionUsu(direccionUsu);
    	u.setTelefonoUsu(telefonoUsu);
    	u.setEmailUsu(emailUsu);
    	u.setClaveUsu(claveUsu);
    	u.setEstadoUsu(estadoUsu);
		em.persist(u);
	}
    
    //Buscar Usuario
    public SegUsuario findUsuario(String cedulaUsu) throws Exception {
    	SegUsuario u = em.find(SegUsuario.class, cedulaUsu);  //Busca en la BDD un objeto a traves de la PK
		return u;
	}
    
    // Editar Usuario
    public void editarUsuario(String cedulaUsu, String apellidosUsu, String nombresUsu, String sexoUsu, String direccionUsu, String telefonoUsu, String emailUsu, String claveUsu, boolean estadoUsu) throws Exception {
    	SegUsuario u= findUsuario(cedulaUsu);
    	if (u == null)
			throw new Exception("No existe el usuario especificado.");
    	u.setApellidosUsu(apellidosUsu);
    	u.setNombresUsu(nombresUsu);
    	u.setSexoUsu(sexoUsu);
    	u.setDireccionUsu(direccionUsu);
    	u.setTelefonoUsu(telefonoUsu);
    	u.setEmailUsu(emailUsu);
    	u.setClaveUsu(claveUsu);
    	u.setEstadoUsu(estadoUsu);
		em.merge(u);
	}
    
    // Inactivar Usuario
    public void inactivarUsuario(String cedulaUsu, boolean estadoUsu) throws Exception {
    	SegUsuario u= findUsuario(cedulaUsu);
    	if (u == null)
			throw new Exception("No existe el usuario especificado.");
    	u.setEstadoUsu(estadoUsu);
		em.merge(u);
	}
}
