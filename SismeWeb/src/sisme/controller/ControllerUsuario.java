package sisme.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sisme.model.entities.SegUsuario;
import sisme.model.manager.ManagerUsuarios;
import sisme.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerUsuario {
	private String cedulaUsu;
	private int idRol;
	private String apellidosUsu;
	private String nombresUsu;
	private String sexoUsu;
	private String direccionUsu;
	private String telefonoUsu;
	private String emailUsu;
	private String claveUsu;
	private boolean estadoUsu;
	
	private List<SegUsuario> lista;
	
	private String cambioEstado;
	
	@EJB
	private ManagerUsuarios managerUsuarios;

	public String iniciarAdministradores() {
		lista = managerUsuarios.findAllAdministradores();
		return "administradores";
	}
	
	public void iniciarBodegueros() {
		lista = managerUsuarios.findAllBodegueros();
	}
	
	public void iniciarRecepcionistas() {
		lista = managerUsuarios.findAllRecepcionistas();
	}
	
	//M É T O D O S  C O M U N E S
	
	//Método para poner en el botón de estado activar/desactivar según sea el caso
	public String mensajeEstado(boolean estado) {
		if(estado==false) {
			cambioEstado="Activar";
		}else {
			cambioEstado="Inactivar";
		}
		return cambioEstado;
	}
	
	//Método para reemplazar true o false por Activo e Inactivo respectivamente
	public String mensajeEstado2(boolean estado) {
		if(estado==true) {
			cambioEstado="Activo";
		}else {
			cambioEstado="Inactivo";
		}
		return cambioEstado;
	}
	
	//Método para reemplazar M o F por Masculino y Femenino respectivamente
	public String mensajeGenero(String sexo) {
		if(sexo.equals("M")) {
			return "Masculino";
		}else {
			return "Femenino";
		}
	}
	
	
	//Agregar nuevo usuario
		public void actionListenerAgregarUsuario(int tipo) {
			try {
				idRol=tipo;
				managerUsuarios.agregarUsuario(cedulaUsu, idRol, apellidosUsu, nombresUsu, sexoUsu, direccionUsu, telefonoUsu, emailUsu, claveUsu, estadoUsu);
				lista= managerUsuarios.findAllAdministradores();
				limpiar();
				JSFUtil.crearMensajeInfo("Usuario registrado correctamente");
			} catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
				e.printStackTrace();
			}
		}
	
	//Cargar Usuario
	public void actionListenerCargarUsuario(SegUsuario usuario) {
		cedulaUsu = usuario.getCedulaUsu();
		apellidosUsu= usuario.getApellidosUsu();
		nombresUsu= usuario.getNombresUsu();
		sexoUsu= usuario.getSexoUsu();
		direccionUsu= usuario.getDireccionUsu();
		telefonoUsu= usuario.getTelefonoUsu();
		emailUsu= usuario.getEmailUsu();
		claveUsu= usuario.getClaveUsu();
		estadoUsu= usuario.getEstadoUsu();
	}
	
	public void limpiar() {
		cedulaUsu="";
		apellidosUsu= "";
		nombresUsu= "";
		sexoUsu= "";
		direccionUsu= "";
		telefonoUsu= "";
		emailUsu= "";
		claveUsu= "";
	}
	
	//Editar Usuario
	public void actionListenerEditarUsuario() {
		try {
			managerUsuarios.editarUsuario(cedulaUsu, apellidosUsu, nombresUsu, sexoUsu, direccionUsu, telefonoUsu, emailUsu, claveUsu, estadoUsu);
			lista = managerUsuarios.findAllAdministradores();
			limpiar();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Cambio estado Usuario
	public void actionListenerCambioEstado(String cedulaUsu, boolean estadoUsu) {
		try {
			if(estadoUsu==false) {
				managerUsuarios.inactivarUsuario(cedulaUsu, true);
			}else {
				managerUsuarios.inactivarUsuario(cedulaUsu, false);
			}
			lista = managerUsuarios.findAllAdministradores();
			JSFUtil.crearMensajeInfo("Cambio de estado correcto.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	
	//Getters and Setters
	public String getCedulaUsu() {
		return cedulaUsu;
	}

	public void setCedulaUsu(String cedulaUsu) {
		this.cedulaUsu = cedulaUsu;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getApellidosUsu() {
		return apellidosUsu;
	}

	public void setApellidosUsu(String apellidosUsu) {
		this.apellidosUsu = apellidosUsu;
	}

	public String getNombresUsu() {
		return nombresUsu;
	}

	public void setNombresUsu(String nombresUsu) {
		this.nombresUsu = nombresUsu;
	}

	public String getSexoUsu() {
		return sexoUsu;
	}

	public void setSexoUsu(String sexoUsu) {
		this.sexoUsu = sexoUsu;
	}

	public String getDireccionUsu() {
		return direccionUsu;
	}

	public void setDireccionUsu(String direccionUsu) {
		this.direccionUsu = direccionUsu;
	}

	public String getTelefonoUsu() {
		return telefonoUsu;
	}

	public void setTelefonoUsu(String telefonoUsu) {
		this.telefonoUsu = telefonoUsu;
	}

	public String getEmailUsu() {
		return emailUsu;
	}

	public void setEmailUsu(String emailUsu) {
		this.emailUsu = emailUsu;
	}

	public String getClaveUsu() {
		return claveUsu;
	}

	public void setClaveUsu(String claveUsu) {
		this.claveUsu = claveUsu;
	}

	public boolean isEstadoUsu() {
		return estadoUsu;
	}

	public void setEstadoUsu(boolean estadoUsu) {
		this.estadoUsu = estadoUsu;
	}

	public List<SegUsuario> getLista() {
		return lista;
	}

	public void setLista(List<SegUsuario> lista) {
		this.lista = lista;
	}

	public String getCambioEstado() {
		return cambioEstado;
	}

	public void setCambioEstado(String cambioEstado) {
		this.cambioEstado = cambioEstado;
	}
	
	
}
