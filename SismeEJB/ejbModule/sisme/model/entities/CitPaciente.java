package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cit_pacientes database table.
 * 
 */
@Entity
@Table(name="cit_pacientes")
@NamedQuery(name="CitPaciente.findAll", query="SELECT c FROM CitPaciente c")
public class CitPaciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_paciente", unique=true, nullable=false, length=10)
	private String cedulaPaciente;

	@Column(name="apellidos_paciente", nullable=false, length=50)
	private String apellidosPaciente;

	@Column(name="direccion_paciente", nullable=false, length=100)
	private String direccionPaciente;

	@Column(name="email_paciente", length=50)
	private String emailPaciente;

	@Column(name="estado_paciente", nullable=false)
	private Boolean estadoPaciente;

	@Column(name="etnia_paciente", length=20)
	private String etniaPaciente;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento_paciente", nullable=false)
	private Date fechaNacimientoPaciente;

	@Column(name="lugar_nacimiento_paciente", nullable=false, length=50)
	private String lugarNacimientoPaciente;

	@Column(name="nacionalidad_paciente", nullable=false, length=20)
	private String nacionalidadPaciente;

	@Column(name="nombres_paciente", nullable=false, length=50)
	private String nombresPaciente;

	@Column(name="sexo_paciente", nullable=false, length=1)
	private String sexoPaciente;

	@Column(name="telefono_paciente", length=10)
	private String telefonoPaciente;

	//bi-directional many-to-one association to CitHistoriasClinica
	@OneToMany(mappedBy="citPaciente")
	private List<CitHistoriasClinica> citHistoriasClinicas;

	public CitPaciente() {
	}

	public String getCedulaPaciente() {
		return this.cedulaPaciente;
	}

	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

	public String getApellidosPaciente() {
		return this.apellidosPaciente;
	}

	public void setApellidosPaciente(String apellidosPaciente) {
		this.apellidosPaciente = apellidosPaciente;
	}

	public String getDireccionPaciente() {
		return this.direccionPaciente;
	}

	public void setDireccionPaciente(String direccionPaciente) {
		this.direccionPaciente = direccionPaciente;
	}

	public String getEmailPaciente() {
		return this.emailPaciente;
	}

	public void setEmailPaciente(String emailPaciente) {
		this.emailPaciente = emailPaciente;
	}

	public Boolean getEstadoPaciente() {
		return this.estadoPaciente;
	}

	public void setEstadoPaciente(Boolean estadoPaciente) {
		this.estadoPaciente = estadoPaciente;
	}

	public String getEtniaPaciente() {
		return this.etniaPaciente;
	}

	public void setEtniaPaciente(String etniaPaciente) {
		this.etniaPaciente = etniaPaciente;
	}

	public Date getFechaNacimientoPaciente() {
		return this.fechaNacimientoPaciente;
	}

	public void setFechaNacimientoPaciente(Date fechaNacimientoPaciente) {
		this.fechaNacimientoPaciente = fechaNacimientoPaciente;
	}

	public String getLugarNacimientoPaciente() {
		return this.lugarNacimientoPaciente;
	}

	public void setLugarNacimientoPaciente(String lugarNacimientoPaciente) {
		this.lugarNacimientoPaciente = lugarNacimientoPaciente;
	}

	public String getNacionalidadPaciente() {
		return this.nacionalidadPaciente;
	}

	public void setNacionalidadPaciente(String nacionalidadPaciente) {
		this.nacionalidadPaciente = nacionalidadPaciente;
	}

	public String getNombresPaciente() {
		return this.nombresPaciente;
	}

	public void setNombresPaciente(String nombresPaciente) {
		this.nombresPaciente = nombresPaciente;
	}

	public String getSexoPaciente() {
		return this.sexoPaciente;
	}

	public void setSexoPaciente(String sexoPaciente) {
		this.sexoPaciente = sexoPaciente;
	}

	public String getTelefonoPaciente() {
		return this.telefonoPaciente;
	}

	public void setTelefonoPaciente(String telefonoPaciente) {
		this.telefonoPaciente = telefonoPaciente;
	}

	public List<CitHistoriasClinica> getCitHistoriasClinicas() {
		return this.citHistoriasClinicas;
	}

	public void setCitHistoriasClinicas(List<CitHistoriasClinica> citHistoriasClinicas) {
		this.citHistoriasClinicas = citHistoriasClinicas;
	}

	public CitHistoriasClinica addCitHistoriasClinica(CitHistoriasClinica citHistoriasClinica) {
		getCitHistoriasClinicas().add(citHistoriasClinica);
		citHistoriasClinica.setCitPaciente(this);

		return citHistoriasClinica;
	}

	public CitHistoriasClinica removeCitHistoriasClinica(CitHistoriasClinica citHistoriasClinica) {
		getCitHistoriasClinicas().remove(citHistoriasClinica);
		citHistoriasClinica.setCitPaciente(null);

		return citHistoriasClinica;
	}

}