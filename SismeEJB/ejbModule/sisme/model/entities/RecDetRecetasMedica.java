package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rec_det_recetas_medicas database table.
 * 
 */
@Entity
@Table(name="rec_det_recetas_medicas")
@NamedQuery(name="RecDetRecetasMedica.findAll", query="SELECT r FROM RecDetRecetasMedica r")
public class RecDetRecetasMedica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REC_DET_RECETAS_MEDICAS_IDDETRECETAMEDICA_GENERATOR", sequenceName="SEQ_REC_DET_RECETAS_MEDICAS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REC_DET_RECETAS_MEDICAS_IDDETRECETAMEDICA_GENERATOR")
	@Column(name="id_det_receta_medica", unique=true, nullable=false)
	private Integer idDetRecetaMedica;

	@Column(nullable=false)
	private Integer cantidad;

	//bi-directional many-to-one association to InvMedicamento
	@ManyToOne
	@JoinColumn(name="id_medicamento", nullable=false)
	private InvMedicamento invMedicamento;

	//bi-directional many-to-one association to RecCabRecetasMedica
	@ManyToOne
	@JoinColumn(name="id_cab_receta_medica", nullable=false)
	private RecCabRecetasMedica recCabRecetasMedica;

	public RecDetRecetasMedica() {
	}

	public Integer getIdDetRecetaMedica() {
		return this.idDetRecetaMedica;
	}

	public void setIdDetRecetaMedica(Integer idDetRecetaMedica) {
		this.idDetRecetaMedica = idDetRecetaMedica;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public InvMedicamento getInvMedicamento() {
		return this.invMedicamento;
	}

	public void setInvMedicamento(InvMedicamento invMedicamento) {
		this.invMedicamento = invMedicamento;
	}

	public RecCabRecetasMedica getRecCabRecetasMedica() {
		return this.recCabRecetasMedica;
	}

	public void setRecCabRecetasMedica(RecCabRecetasMedica recCabRecetasMedica) {
		this.recCabRecetasMedica = recCabRecetasMedica;
	}

}