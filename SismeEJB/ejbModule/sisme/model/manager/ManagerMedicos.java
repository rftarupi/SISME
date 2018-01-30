package sisme.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sisme.model.entities.SegRole;
import sisme.model.entities.SegUsuario;
import sisme.model.entities.ThuEspecialidade;
import sisme.model.entities.ThuMedico;

/**
 * Session Bean implementation class ManagerMedicos
 */
@Stateless
@LocalBean
public class ManagerMedicos {
	@PersistenceContext(unitName="SismeDS")
	private EntityManager em;
	
    public ManagerMedicos() {
        // TODO Auto-generated constructor stub
    }
    

    public String obtenerRolMédico(String cedulaUsu) {
    	return "Médico";
    }
    
    public int obtenerConsultorioMédico(String cedulaUsu) {
    	ThuMedico medico;
    	medico=em.find(ThuMedico.class, cedulaUsu);
    	return medico.getNumConsultorioMedico();
    }
    
    public String obtenerEspecialidadMédico(String cedulaUsu) {
    	ThuMedico medico;
    	medico=em.find(ThuMedico.class, cedulaUsu);
    	return medico.getThuEspecialidade().getNombreEspecialidad();
    }
    
    //Listar Médicos
    public List<SegUsuario> findAllMedicos() {
		Query q;
		List<SegUsuario> listadoMedicos;
		String sentenciaSQL;
		//sentenciaSQL = "SELECT m, u FROM ThuMedico m, SegUsuario u where m.cedulaUsu=u.cedulaUsu and u.segRole=7 ORDER BY m.cedulaUsu";
		sentenciaSQL = "SELECT u FROM SegUsuario u where u.segRole=7 ORDER BY u.cedulaUsu";
		q = em.createQuery(sentenciaSQL);
		listadoMedicos = q.getResultList();
		return listadoMedicos;
	}
    
    
    // Agregar Médico
    public void agregarUsuarioMedico(String cedulaUsu, int idRol, String apellidosUsu, String nombresUsu, String sexoUsu, String direccionUsu, String telefonoUsu, String emailUsu, String claveUsu, boolean estadoUsu, int numConsultorioMedico, int idEspecialidad) throws Exception {
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
		
		ThuMedico m= new ThuMedico();
		m.setCedulaUsu(cedulaUsu);
		ThuEspecialidade e= em.find(ThuEspecialidade.class, idEspecialidad);
		m.setThuEspecialidade(e);
		m.setNumConsultorioMedico(numConsultorioMedico);
		em.persist(m);
	}
    
    //Buscar Usuario
    public SegUsuario findUsuario(String cedulaUsu) throws Exception {
    	SegUsuario u = em.find(SegUsuario.class, cedulaUsu);
		return u;
	}
    
  //Buscar Médico
    public ThuMedico findMedico(String cedulaUsu) throws Exception {
    	ThuMedico u = em.find(ThuMedico.class, cedulaUsu);
		return u;
	}
    
    // Editar Usuario Médico
    public void editarUsuarioMedico(String cedulaUsu, String apellidosUsu, String nombresUsu, String sexoUsu, String direccionUsu, String telefonoUsu, String emailUsu, String claveUsu, boolean estadoUsu, Integer numConsultorioMedico, Integer idEspecialidad) throws Exception {
    	SegUsuario u= findUsuario(cedulaUsu);
    	if (u == null)
			throw new Exception("No existe el médico especificado.");
    	u.setApellidosUsu(apellidosUsu);
    	u.setNombresUsu(nombresUsu);
    	u.setSexoUsu(sexoUsu);
    	u.setDireccionUsu(direccionUsu);
    	u.setTelefonoUsu(telefonoUsu);
    	u.setEmailUsu(emailUsu);
    	u.setClaveUsu(claveUsu);
    	u.setEstadoUsu(estadoUsu);
		em.merge(u);
		
		ThuMedico m= new ThuMedico();
		ThuEspecialidade e= em.find(ThuEspecialidade.class, idEspecialidad);
		m.setThuEspecialidade(e);
		m.setNumConsultorioMedico(numConsultorioMedico);
		em.merge(m);
	}
    
    // Inactivar Usuario Médico
    public void inactivarUsuarioMedico(String cedulaUsu, boolean estadoUsu) throws Exception {
    	SegUsuario u= findUsuario(cedulaUsu);
    	if (u == null)
			throw new Exception("No existe el médico especificado.");
    	u.setEstadoUsu(estadoUsu);
		em.merge(u);
	}

}
