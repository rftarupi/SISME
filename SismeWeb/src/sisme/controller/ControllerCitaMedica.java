package sisme.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import sisme.model.manager.ManagerCitaMedica;
import sisme.model.manager.ManagerUsuarios;
import sisme.view.util.JSFUtil;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class ControllerCitaMedica {
	
	private String cedulaPaciente;
	private Integer idHistoriaClinica;
	private String nombresPaciente;
	private String apellidosPaciente;
	private String nacionalidadPaciente;
	private String etniaPaciente;
	private String lugarNacimientoPaciente;
	private Date fechaNacimientoPaciente;
	private String direccionPaciente;
	private String telefonoPaciente;
	private String emailPaciente;
	private String sexoPaciente;
	private boolean estadoPaciente;
	
	
	private Date fechaIngreso;
	private String cedulaUsuario;
	private String nombresRegistrador;
	private String antecedentesMedicos;
	
	
	//private List<SelectItem> listaMedicoEspecialidad;
	private List<String> listaMedicoEspecialidad;
	private Date fechaCitaMedica;
	private int idHorario;
	
	
	@PostConstruct
	public void iniciar() {
		listaMedicoEspecialidad= managerCitaMedica.getMedicosEspecialidades();
		//listaMedicoEspecialidad= managerCitaMedica.obtenerMedicosEspecialidades();
	}
	
	public void asignar(String ced) {
		cedulaUsuario= ced;
		obtenerNombresRegistrador();
	}
	
	@EJB
	private ManagerCitaMedica managerCitaMedica;
	
	// Buscar Paciente
	public String actionBuscarPaciente() {
		if(managerCitaMedica.buscarPaciente(cedulaPaciente)==true) {
			return "citaMedica.xhtml";
		}else {
			JSFUtil.crearMensajeWarning("Paciente no encontrado, registrelo");
			idHistoriaClinica= managerCitaMedica.obtenerNuevoNumHistoriaClinica();
			return "nuevoPaciente.xhtml";
		}
	}
	
	//Registrar paciente
	public void actionListenerRegistrarPaciente () {
		try {
			managerCitaMedica.agregarPaciente(cedulaPaciente, nombresPaciente, apellidosPaciente, nacionalidadPaciente, etniaPaciente, sexoPaciente, lugarNacimientoPaciente, fechaNacimientoPaciente, direccionPaciente, telefonoPaciente, emailPaciente, estadoPaciente, fechaIngreso, cedulaUsuario, antecedentesMedicos);	
			JSFUtil.crearMensajeInfo("Paciente registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// Obtener nombres y apellidos registrador
	public void obtenerNombresRegistrador() {
		try {
			nombresRegistrador=managerCitaMedica.getFullNameUser(cedulaUsuario);	
		}catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getCedulaPaciente() {
		return cedulaPaciente;
	}

	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

	public Integer getIdHistoriaClinica() {
		return idHistoriaClinica;
	}

	public void setIdHistoriaClinica(Integer idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}

	public String getNombresPaciente() {
		return nombresPaciente;
	}

	public void setNombresPaciente(String nombresPaciente) {
		this.nombresPaciente = nombresPaciente;
	}

	public String getApellidosPaciente() {
		return apellidosPaciente;
	}

	public void setApellidosPaciente(String apellidosPaciente) {
		this.apellidosPaciente = apellidosPaciente;
	}

	public String getNacionalidadPaciente() {
		return nacionalidadPaciente;
	}

	public void setNacionalidadPaciente(String nacionalidadPaciente) {
		this.nacionalidadPaciente = nacionalidadPaciente;
	}
	
	public String getEtniaPaciente() {
		return etniaPaciente;
	}

	public void setEtniaPaciente(String etniaPaciente) {
		this.etniaPaciente = etniaPaciente;
	}

	public String getLugarNacimientoPaciente() {
		return lugarNacimientoPaciente;
	}

	public void setLugarNacimientoPaciente(String lugarNacimientoPaciente) {
		this.lugarNacimientoPaciente = lugarNacimientoPaciente;
	}

	public Date getFechaNacimientoPaciente() {
		return fechaNacimientoPaciente;
	}

	public void setFechaNacimientoPaciente(Date fechaNacimientoPaciente) {
		this.fechaNacimientoPaciente = fechaNacimientoPaciente;
	}

	public String getDireccionPaciente() {
		return direccionPaciente;
	}

	public void setDireccionPaciente(String direccionPaciente) {
		this.direccionPaciente = direccionPaciente;
	}

	public String getTelefonoPaciente() {
		return telefonoPaciente;
	}

	public void setTelefonoPaciente(String telefonoPaciente) {
		this.telefonoPaciente = telefonoPaciente;
	}

	public String getEmailPaciente() {
		return emailPaciente;
	}

	public void setEmailPaciente(String emailPaciente) {
		this.emailPaciente = emailPaciente;
	}

	public String getSexoPaciente() {
		return sexoPaciente;
	}

	public boolean getEstadoPaciente() {
		return estadoPaciente;
	}

	public void setEstadoPaciente(boolean estadoPaciente) {
		this.estadoPaciente = estadoPaciente;
	}

	public void setSexoPaciente(String sexoPaciente) {
		this.sexoPaciente = sexoPaciente;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getCedulaUsuario() {
		return cedulaUsuario;
	}

	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	public String getAntecedentesMedicos() {
		return antecedentesMedicos;
	}
	
	public String getNombresRegistrador() {
		return nombresRegistrador;
	}

	public void setNombresRegistrador(String nombresRegistrador) {
		this.nombresRegistrador = nombresRegistrador;
	}

	public void setAntecedentesMedicos(String antecedentesMedicos) {
		this.antecedentesMedicos = antecedentesMedicos;
	}

	/**
	public List<SelectItem> getListaMedicoEspecialidad() {
		return listaMedicoEspecialidad;
	}

	public void setListaMedicoEspecialidad(List<SelectItem> listaMedicoEspecialidad) {
		this.listaMedicoEspecialidad = listaMedicoEspecialidad;
	}
	*/
	

	public Date getFechaCitaMedica() {
		return fechaCitaMedica;
	}

	
	public List<String> getListaMedicoEspecialidad() {
		return listaMedicoEspecialidad;
	}

	public void setListaMedicoEspecialidad(List<String> listaMedicoEspecialidad) {
		this.listaMedicoEspecialidad = listaMedicoEspecialidad;
	}


	public void setFechaCitaMedica(Date fechaCitaMedica) {
		this.fechaCitaMedica = fechaCitaMedica;
	}

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}
	
	
}
