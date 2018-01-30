package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_usuarios database table.
 * 
 */
@Entity
@Table(name="seg_usuarios")
@NamedQuery(name="SegUsuario.findAll", query="SELECT s FROM SegUsuario s")
public class SegUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_usu", unique=true, nullable=false, length=10)
	private String cedulaUsu;

	@Column(name="apellidos_usu", nullable=false, length=50)
	private String apellidosUsu;

	@Column(name="clave_usu", nullable=false, length=50)
	private String claveUsu;

	@Column(name="direccion_usu", nullable=false, length=100)
	private String direccionUsu;

	@Column(name="email_usu", length=50)
	private String emailUsu;

	@Column(name="estado_usu", nullable=false)
	private Boolean estadoUsu;

	@Column(name="nombres_usu", nullable=false, length=50)
	private String nombresUsu;

	@Column(name="sexo_usu", nullable=false, length=1)
	private String sexoUsu;

	@Column(name="telefono_usu", length=10)
	private String telefonoUsu;

	//bi-directional many-to-one association to CitHistoriasClinica
	@OneToMany(mappedBy="segUsuario")
	private List<CitHistoriasClinica> citHistoriasClinicas;

	//bi-directional many-to-one association to SegRole
	@ManyToOne
	@JoinColumn(name="id_rol", nullable=false)
	private SegRole segRole;

	//bi-directional one-to-one association to ThuMedico
	@OneToOne(mappedBy="segUsuario")
	private ThuMedico thuMedico;

	public SegUsuario() {
	}

	public String getCedulaUsu() {
		return this.cedulaUsu;
	}

	public void setCedulaUsu(String cedulaUsu) {
		this.cedulaUsu = cedulaUsu;
	}

	public String getApellidosUsu() {
		return this.apellidosUsu;
	}

	public void setApellidosUsu(String apellidosUsu) {
		this.apellidosUsu = apellidosUsu;
	}

	public String getClaveUsu() {
		return this.claveUsu;
	}

	public void setClaveUsu(String claveUsu) {
		this.claveUsu = claveUsu;
	}

	public String getDireccionUsu() {
		return this.direccionUsu;
	}

	public void setDireccionUsu(String direccionUsu) {
		this.direccionUsu = direccionUsu;
	}

	public String getEmailUsu() {
		return this.emailUsu;
	}

	public void setEmailUsu(String emailUsu) {
		this.emailUsu = emailUsu;
	}

	public Boolean getEstadoUsu() {
		return this.estadoUsu;
	}

	public void setEstadoUsu(Boolean estadoUsu) {
		this.estadoUsu = estadoUsu;
	}

	public String getNombresUsu() {
		return this.nombresUsu;
	}

	public void setNombresUsu(String nombresUsu) {
		this.nombresUsu = nombresUsu;
	}

	public String getSexoUsu() {
		return this.sexoUsu;
	}

	public void setSexoUsu(String sexoUsu) {
		this.sexoUsu = sexoUsu;
	}

	public String getTelefonoUsu() {
		return this.telefonoUsu;
	}

	public void setTelefonoUsu(String telefonoUsu) {
		this.telefonoUsu = telefonoUsu;
	}

	public List<CitHistoriasClinica> getCitHistoriasClinicas() {
		return this.citHistoriasClinicas;
	}

	public void setCitHistoriasClinicas(List<CitHistoriasClinica> citHistoriasClinicas) {
		this.citHistoriasClinicas = citHistoriasClinicas;
	}

	public CitHistoriasClinica addCitHistoriasClinica(CitHistoriasClinica citHistoriasClinica) {
		getCitHistoriasClinicas().add(citHistoriasClinica);
		citHistoriasClinica.setSegUsuario(this);

		return citHistoriasClinica;
	}

	public CitHistoriasClinica removeCitHistoriasClinica(CitHistoriasClinica citHistoriasClinica) {
		getCitHistoriasClinicas().remove(citHistoriasClinica);
		citHistoriasClinica.setSegUsuario(null);

		return citHistoriasClinica;
	}

	public SegRole getSegRole() {
		return this.segRole;
	}

	public void setSegRole(SegRole segRole) {
		this.segRole = segRole;
	}

	public ThuMedico getThuMedico() {
		return this.thuMedico;
	}

	public void setThuMedico(ThuMedico thuMedico) {
		this.thuMedico = thuMedico;
	}

}