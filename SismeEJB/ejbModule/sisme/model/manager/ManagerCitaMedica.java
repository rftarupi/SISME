package sisme.model.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sisme.model.entities.SegUsuario;
import sisme.model.entities.ThuMedico;
import sisme.model.entities.CitCitasMedica;
import sisme.model.entities.CitHistoriasClinica;
import sisme.model.entities.CitHorariosMedico;
import sisme.model.entities.CitPaciente;

/**
 * Session Bean implementation class ManagerCitaMedica
 */
@Stateless
@LocalBean
public class ManagerCitaMedica {
	@PersistenceContext(unitName = "SismeDS")
	private EntityManager em;

	public ManagerCitaMedica() {
		// TODO Auto-generated constructor stub
	}

	// Buscar Usuario
	public SegUsuario findUsuario(String cedulaUsu) throws Exception {
		SegUsuario u = em.find(SegUsuario.class, cedulaUsu); // Busca en la BDD un objeto a traves de la PK
		return u;
	}

	// Obtener nombres Usuario
	public String getFullNameUser(String cedulaUsu) throws Exception {
		SegUsuario u = em.find(SegUsuario.class, cedulaUsu); // Busca en la BDD un objeto a traves de la PK
		return u.getNombresUsu() + " " + u.getApellidosUsu();
	}

	// Buscar Paciente
	public boolean buscarPaciente(String cedulaPaciente) {
		CitPaciente paciente = em.find(CitPaciente.class, cedulaPaciente);
		if (paciente == null) {
			System.out.println("No Encontrado .... :( ");
			return false;
		} else {
			System.out.println("Encontrado .... :) ");
			return true;
		}
	}

	// Registrar paciente
	public void agregarPaciente(String cedulaPaciente, String nombresPaciente, String apellidosPaciente,
			String nacionalidadPaciente, String etniaPaciente, String sexoPaciente, String lugarNacimientoPaciente,
			Date fechaNacimientoPaciente, String direccionPaciente, String telefonoPaciente, String emailPaciente,
			boolean estadoPaciente, Date fechaIngreso, String cedulaRegistrador, String antescedentesMedicos)
			throws Exception {

		CitPaciente p = new CitPaciente();
		p.setCedulaPaciente(cedulaPaciente);
		p.setNombresPaciente(nombresPaciente);
		p.setApellidosPaciente(apellidosPaciente);
		p.setNacionalidadPaciente(nacionalidadPaciente);
		p.setEtniaPaciente(etniaPaciente);
		p.setSexoPaciente(sexoPaciente);
		p.setLugarNacimientoPaciente(lugarNacimientoPaciente);
		p.setFechaNacimientoPaciente(fechaNacimientoPaciente);
		p.setDireccionPaciente(direccionPaciente);
		p.setTelefonoPaciente(telefonoPaciente);
		p.setEmailPaciente(emailPaciente);
		p.setEstadoPaciente(estadoPaciente);
		em.persist(p);

		// Historia Clínica
		// Busqueda recepcionista registrador
		SegUsuario registrador = em.find(SegUsuario.class, cedulaRegistrador);
		CitHistoriasClinica hc = new CitHistoriasClinica();
		hc.setFechaIngreso(fechaIngreso);
		hc.setCitPaciente(p);
		hc.setSegUsuario(registrador);
		hc.setAntescedentesMedicos(antescedentesMedicos);
		em.persist(hc);
	}

	// obtener Numero Historia Clínica {
	public Integer obtenerNuevoNumHistoriaClinica() {
		//String jpql="select h.idHistoriaClinica from CitHistoriasClinica h";
		// Query q=em.createQuery(jpql, CitHistoriasClinica.class);

		String sql = "select MAX (c.id_historia_clinica) from cit_historias_clinicas c;"; 
		Query query = em.createNativeQuery(sql); 
		return (Integer)query.getResultList().get(0)+1;
	}
	
	// C I T A   M É D I C A
	
	// obtener Nombre y especialidad médico Historia Clínica {
			 public List<String> getMedicosEspecialidades() {
					List<String> listado=new ArrayList<>();
					Query q;
					List<SegUsuario> listadoMedicos;
					String sentenciaSQL;
					sentenciaSQL = "SELECT u FROM SegUsuario u where u.segRole=7 ORDER BY u.cedulaUsu";
					q = em.createQuery(sentenciaSQL);
					listadoMedicos = q.getResultList();
					for	(int i=0; i<listadoMedicos.size();i++) {
						ThuMedico m = em.find(ThuMedico.class, listadoMedicos.get(i).getCedulaUsu());
						String nombres= listadoMedicos.get(i).getApellidosUsu()+" "+listadoMedicos.get(i).getNombresUsu()+" - "+ m.getThuEspecialidade().getNombreEspecialidad();
						//listado.set(i, nombres);
						listado.add(nombres);
					}

					return listado;
				}
		 
		// Registrar cita médica
			public void agregarCitaMedica(int idHistoriaClinica, Date fechaCitaMedica, int idHorario )throws Exception {			
				CitCitasMedica cm= new CitCitasMedica();
				CitHistoriasClinica historiaClinica= em.find(CitHistoriasClinica.class, idHistoriaClinica);
				cm.setCitHistoriasClinica(historiaClinica);
				cm.setFechaCitaMedica(fechaCitaMedica);
				cm.setFechaEmision(new Date());
				CitHorariosMedico hm= em.find(CitHorariosMedico.class, idHorario);
				cm.setCitHorariosMedico(hm);
				cm.setEstadoCita(true);
				em.persist(cm);
			}
}
