package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_medicamentos database table.
 * 
 */
@Entity
@Table(name="inv_medicamentos")
@NamedQuery(name="InvMedicamento.findAll", query="SELECT i FROM InvMedicamento i")
public class InvMedicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INV_MEDICAMENTOS_IDMEDICAMENTO_GENERATOR", sequenceName="SEQ_INV_MEDICAMENTOS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_MEDICAMENTOS_IDMEDICAMENTO_GENERATOR")
	@Column(name="id_medicamento", unique=true, nullable=false)
	private Integer idMedicamento;

	@Column(name="descripcion_medicamento", nullable=false, length=100)
	private String descripcionMedicamento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_caducidad_medicamento", nullable=false)
	private Date fechaCaducidadMedicamento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso_medicamento", nullable=false)
	private Date fechaIngresoMedicamento;

	@Column(name="nombre_medicamento", nullable=false, length=50)
	private String nombreMedicamento;

	@Column(name="stock_medicamento", nullable=false)
	private Integer stockMedicamento;

	//bi-directional many-to-one association to InvTiposAdministracion
	@ManyToOne
	@JoinColumn(name="id_tipo_administracion", nullable=false)
	private InvTiposAdministracion invTiposAdministracion;

	//bi-directional many-to-one association to InvUnidadesMedida
	@ManyToOne
	@JoinColumn(name="id_unidad_medida", nullable=false)
	private InvUnidadesMedida invUnidadesMedida;

	//bi-directional many-to-one association to RecDetRecetasMedica
	@OneToMany(mappedBy="invMedicamento")
	private List<RecDetRecetasMedica> recDetRecetasMedicas;

	public InvMedicamento() {
	}

	public Integer getIdMedicamento() {
		return this.idMedicamento;
	}

	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getDescripcionMedicamento() {
		return this.descripcionMedicamento;
	}

	public void setDescripcionMedicamento(String descripcionMedicamento) {
		this.descripcionMedicamento = descripcionMedicamento;
	}

	public Date getFechaCaducidadMedicamento() {
		return this.fechaCaducidadMedicamento;
	}

	public void setFechaCaducidadMedicamento(Date fechaCaducidadMedicamento) {
		this.fechaCaducidadMedicamento = fechaCaducidadMedicamento;
	}

	public Date getFechaIngresoMedicamento() {
		return this.fechaIngresoMedicamento;
	}

	public void setFechaIngresoMedicamento(Date fechaIngresoMedicamento) {
		this.fechaIngresoMedicamento = fechaIngresoMedicamento;
	}

	public String getNombreMedicamento() {
		return this.nombreMedicamento;
	}

	public void setNombreMedicamento(String nombreMedicamento) {
		this.nombreMedicamento = nombreMedicamento;
	}

	public Integer getStockMedicamento() {
		return this.stockMedicamento;
	}

	public void setStockMedicamento(Integer stockMedicamento) {
		this.stockMedicamento = stockMedicamento;
	}

	public InvTiposAdministracion getInvTiposAdministracion() {
		return this.invTiposAdministracion;
	}

	public void setInvTiposAdministracion(InvTiposAdministracion invTiposAdministracion) {
		this.invTiposAdministracion = invTiposAdministracion;
	}

	public InvUnidadesMedida getInvUnidadesMedida() {
		return this.invUnidadesMedida;
	}

	public void setInvUnidadesMedida(InvUnidadesMedida invUnidadesMedida) {
		this.invUnidadesMedida = invUnidadesMedida;
	}

	public List<RecDetRecetasMedica> getRecDetRecetasMedicas() {
		return this.recDetRecetasMedicas;
	}

	public void setRecDetRecetasMedicas(List<RecDetRecetasMedica> recDetRecetasMedicas) {
		this.recDetRecetasMedicas = recDetRecetasMedicas;
	}

	public RecDetRecetasMedica addRecDetRecetasMedica(RecDetRecetasMedica recDetRecetasMedica) {
		getRecDetRecetasMedicas().add(recDetRecetasMedica);
		recDetRecetasMedica.setInvMedicamento(this);

		return recDetRecetasMedica;
	}

	public RecDetRecetasMedica removeRecDetRecetasMedica(RecDetRecetasMedica recDetRecetasMedica) {
		getRecDetRecetasMedicas().remove(recDetRecetasMedica);
		recDetRecetasMedica.setInvMedicamento(null);

		return recDetRecetasMedica;
	}

}