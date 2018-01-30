package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cit_citas_medicas database table.
 * 
 */
@Entity
@Table(name="cit_citas_medicas")
@NamedQuery(name="CitCitasMedica.findAll", query="SELECT c FROM CitCitasMedica c")
public class CitCitasMedica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CIT_CITAS_MEDICAS_IDCITAMEDICA_GENERATOR", sequenceName="SEQ_CIT_CITAS_MEDICAS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CIT_CITAS_MEDICAS_IDCITAMEDICA_GENERATOR")
	@Column(name="id_cita_medica", unique=true, nullable=false)
	private Integer idCitaMedica;

	@Column(name="estado_cita", nullable=false)
	private Boolean estadoCita;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_cita_medica", nullable=false)
	private Date fechaCitaMedica;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision", nullable=false)
	private Date fechaEmision;

	//bi-directional many-to-one association to CitHistoriasClinica
	@ManyToOne
	@JoinColumn(name="id_historia_clinica", nullable=false)
	private CitHistoriasClinica citHistoriasClinica;

	//bi-directional many-to-one association to CitHorariosMedico
	@ManyToOne
	@JoinColumn(name="id_horario", nullable=false)
	private CitHorariosMedico citHorariosMedico;

	public CitCitasMedica() {
	}

	public Integer getIdCitaMedica() {
		return this.idCitaMedica;
	}

	public void setIdCitaMedica(Integer idCitaMedica) {
		this.idCitaMedica = idCitaMedica;
	}

	public Boolean getEstadoCita() {
		return this.estadoCita;
	}

	public void setEstadoCita(Boolean estadoCita) {
		this.estadoCita = estadoCita;
	}

	public Date getFechaCitaMedica() {
		return this.fechaCitaMedica;
	}

	public void setFechaCitaMedica(Date fechaCitaMedica) {
		this.fechaCitaMedica = fechaCitaMedica;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public CitHistoriasClinica getCitHistoriasClinica() {
		return this.citHistoriasClinica;
	}

	public void setCitHistoriasClinica(CitHistoriasClinica citHistoriasClinica) {
		this.citHistoriasClinica = citHistoriasClinica;
	}

	public CitHorariosMedico getCitHorariosMedico() {
		return this.citHorariosMedico;
	}

	public void setCitHorariosMedico(CitHorariosMedico citHorariosMedico) {
		this.citHorariosMedico = citHorariosMedico;
	}

}