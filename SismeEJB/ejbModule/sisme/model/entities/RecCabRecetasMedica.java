package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rec_cab_recetas_medicas database table.
 * 
 */
@Entity
@Table(name="rec_cab_recetas_medicas")
@NamedQuery(name="RecCabRecetasMedica.findAll", query="SELECT r FROM RecCabRecetasMedica r")
public class RecCabRecetasMedica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REC_CAB_RECETAS_MEDICAS_IDCABRECETAMEDICA_GENERATOR", sequenceName="SEQ_REC_CAB_RECETAS_MEDICAS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REC_CAB_RECETAS_MEDICAS_IDCABRECETAMEDICA_GENERATOR")
	@Column(name="id_cab_receta_medica", unique=true, nullable=false)
	private Integer idCabRecetaMedica;

	@Column(name="estado_receta", nullable=false)
	private Boolean estadoReceta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_receta_medica", nullable=false)
	private Date fechaRecetaMedica;

	//bi-directional many-to-one association to CitHistoriasClinica
	@ManyToOne
	@JoinColumn(name="id_historia_clinica", nullable=false)
	private CitHistoriasClinica citHistoriasClinica;

	//bi-directional many-to-one association to ThuMedico
	@ManyToOne
	@JoinColumn(name="cedula_usu", nullable=false)
	private ThuMedico thuMedico;

	//bi-directional many-to-one association to RecDetRecetasMedica
	@OneToMany(mappedBy="recCabRecetasMedica")
	private List<RecDetRecetasMedica> recDetRecetasMedicas;

	public RecCabRecetasMedica() {
	}

	public Integer getIdCabRecetaMedica() {
		return this.idCabRecetaMedica;
	}

	public void setIdCabRecetaMedica(Integer idCabRecetaMedica) {
		this.idCabRecetaMedica = idCabRecetaMedica;
	}

	public Boolean getEstadoReceta() {
		return this.estadoReceta;
	}

	public void setEstadoReceta(Boolean estadoReceta) {
		this.estadoReceta = estadoReceta;
	}

	public Date getFechaRecetaMedica() {
		return this.fechaRecetaMedica;
	}

	public void setFechaRecetaMedica(Date fechaRecetaMedica) {
		this.fechaRecetaMedica = fechaRecetaMedica;
	}

	public CitHistoriasClinica getCitHistoriasClinica() {
		return this.citHistoriasClinica;
	}

	public void setCitHistoriasClinica(CitHistoriasClinica citHistoriasClinica) {
		this.citHistoriasClinica = citHistoriasClinica;
	}

	public ThuMedico getThuMedico() {
		return this.thuMedico;
	}

	public void setThuMedico(ThuMedico thuMedico) {
		this.thuMedico = thuMedico;
	}

	public List<RecDetRecetasMedica> getRecDetRecetasMedicas() {
		return this.recDetRecetasMedicas;
	}

	public void setRecDetRecetasMedicas(List<RecDetRecetasMedica> recDetRecetasMedicas) {
		this.recDetRecetasMedicas = recDetRecetasMedicas;
	}

	public RecDetRecetasMedica addRecDetRecetasMedica(RecDetRecetasMedica recDetRecetasMedica) {
		getRecDetRecetasMedicas().add(recDetRecetasMedica);
		recDetRecetasMedica.setRecCabRecetasMedica(this);

		return recDetRecetasMedica;
	}

	public RecDetRecetasMedica removeRecDetRecetasMedica(RecDetRecetasMedica recDetRecetasMedica) {
		getRecDetRecetasMedicas().remove(recDetRecetasMedica);
		recDetRecetasMedica.setRecCabRecetasMedica(null);

		return recDetRecetasMedica;
	}

}