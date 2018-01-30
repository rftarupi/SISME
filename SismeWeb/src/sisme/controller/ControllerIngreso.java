package sisme.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import sisme.model.util.ModelUtil;
import sisme.model.manager.ManagerUsuarios;
import sisme.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerIngreso {
	private String cedulaUsu;
	private String claveUsu;
	private String rolUsu;
	private boolean confirmadoLogin;

	@EJB
	private ManagerUsuarios managerUsuarios;

	public String actionLogin() {
		try {
			confirmadoLogin = managerUsuarios.comprobarUsuario(cedulaUsu, claveUsu);
			// verificamos el rol del usuario:
			rolUsu=managerUsuarios.obtenerRolUsuario(cedulaUsu);
			if (rolUsu.equals("Administrador")) {
				return "Administradores/inicioGestionTH";
			}else if(rolUsu.equals("Recepcionista")) {
				return "Recepcionistas/inicioCitasMedicas";
			}else if(rolUsu.equals("Bodeguero")) {
				return "Bodegueros/inicioInventario";
			}
			return "Medicos/inicioRecetasMedicas";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	public void actionComprobarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			String path = ec.getRequestPathInfo();
			System.out.println("getRequestContextPath(): " + ec.getRequestContextPath());
			System.out.println("getRequestPathInfo(): " + path);
			System.out.println("Id usuario: " + cedulaUsu);
			if (path.equals("/login.xhtml"))
				return;
			if (ModelUtil.isEmpty(idUsuario))
				ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
			if (!confirmadoLogin) {
				ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
			} else {
				// si hizo login, verificamos que acceda a paginas permitidas:
				if (idUsuario.equals("admin")) {
					if (!path.contains("/admin/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
					else
						return;
				}
				// caso contrario es un blogger:
				if (!path.contains("/blog/"))
					ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	public String actionSalir() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
	
	
	public boolean isConfirmadoLogin() {
		return confirmadoLogin;
	}


	public void setConfirmadoLogin(boolean confirmadoLogin) {
		this.confirmadoLogin = confirmadoLogin;
	}


	public String getCedulaUsu() {
		return cedulaUsu;
	}

	public void setCedulaUsu(String cedulaUsu) {
		this.cedulaUsu = cedulaUsu;
	}

	public String getClaveUsu() {
		return claveUsu;
	}

	public void setClaveUsu(String claveUsu) {
		this.claveUsu = claveUsu;
	}

	public String getRolUsu() {
		return rolUsu;
	}

	public void setRolUsu(String rolUsu) {
		this.rolUsu = rolUsu;
	}

}
