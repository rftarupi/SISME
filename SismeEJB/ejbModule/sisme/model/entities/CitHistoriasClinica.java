package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cit_historias_clinicas database table.
 * 
 */
@Entity
@Table(name="cit_historias_clinicas")
@NamedQuery(name="CitHistoriasClinica.findAll", query="SELECT c FROM CitHistoriasClinica c")
public class CitHistoriasClinica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CIT_HISTORIAS_CLINICAS_IDHISTORIACLINICA_GENERATOR", sequenceName="SEQ_CIT_HISTORIAS_CLINICAS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CIT_HISTORIAS_CLINICAS_IDHISTORIACLINICA_GENERATOR")
	@Column(name="id_historia_clinica", unique=true, nullable=false)
	private Integer idHistoriaClinica;

	@Column(name="antescedentes_medicos", length=200)
	private String antescedentesMedicos;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso", nullable=false)
	private Date fechaIngreso;

	//bi-directional many-to-one association to CitCitasMedica
	@OneToMany(mappedBy="citHistoriasClinica")
	private List<CitCitasMedica> citCitasMedicas;

	//bi-directional many-to-one association to CitPaciente
	@ManyToOne
	@JoinColumn(name="cedula_paciente", nullable=false)
	private CitPaciente citPaciente;

	//bi-directional many-to-one association to SegUsuario
	@ManyToOne
	@JoinColumn(name="cedula_usu", nullable=false)
	private SegUsuario segUsuario;

	//bi-directional many-to-one association to RecCabRecetasMedica
	@OneToMany(mappedBy="citHistoriasClinica")
	private List<RecCabRecetasMedica> recCabRecetasMedicas;

	public CitHistoriasClinica() {
	}

	public Integer getIdHistoriaClinica() {
		return this.idHistoriaClinica;
	}

	public void setIdHistoriaClinica(Integer idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}

	public String getAntescedentesMedicos() {
		return this.antescedentesMedicos;
	}

	public void setAntescedentesMedicos(String antescedentesMedicos) {
		this.antescedentesMedicos = antescedentesMedicos;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public List<CitCitasMedica> getCitCitasMedicas() {
		return this.citCitasMedicas;
	}

	public void setCitCitasMedicas(List<CitCitasMedica> citCitasMedicas) {
		this.citCitasMedicas = citCitasMedicas;
	}

	public CitCitasMedica addCitCitasMedica(CitCitasMedica citCitasMedica) {
		getCitCitasMedicas().add(citCitasMedica);
		citCitasMedica.setCitHistoriasClinica(this);

		return citCitasMedica;
	}

	public CitCitasMedica removeCitCitasMedica(CitCitasMedica citCitasMedica) {
		getCitCitasMedicas().remove(citCitasMedica);
		citCitasMedica.setCitHistoriasClinica(null);

		return citCitasMedica;
	}

	public CitPaciente getCitPaciente() {
		return this.citPaciente;
	}

	public void setCitPaciente(CitPaciente citPaciente) {
		this.citPaciente = citPaciente;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	public List<RecCabRecetasMedica> getRecCabRecetasMedicas() {
		return this.recCabRecetasMedicas;
	}

	public void setRecCabRecetasMedicas(List<RecCabRecetasMedica> recCabRecetasMedicas) {
		this.recCabRecetasMedicas = recCabRecetasMedicas;
	}

	public RecCabRecetasMedica addRecCabRecetasMedica(RecCabRecetasMedica recCabRecetasMedica) {
		getRecCabRecetasMedicas().add(recCabRecetasMedica);
		recCabRecetasMedica.setCitHistoriasClinica(this);

		return recCabRecetasMedica;
	}

	public RecCabRecetasMedica removeRecCabRecetasMedica(RecCabRecetasMedica recCabRecetasMedica) {
		getRecCabRecetasMedicas().remove(recCabRecetasMedica);
		recCabRecetasMedica.setCitHistoriasClinica(null);

		return recCabRecetasMedica;
	}

}