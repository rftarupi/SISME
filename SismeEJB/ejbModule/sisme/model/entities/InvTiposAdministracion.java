package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inv_tipos_administracion database table.
 * 
 */
@Entity
@Table(name="inv_tipos_administracion")
@NamedQuery(name="InvTiposAdministracion.findAll", query="SELECT i FROM InvTiposAdministracion i")
public class InvTiposAdministracion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INV_TIPOS_ADMINISTRACION_IDTIPOADMINISTRACION_GENERATOR", sequenceName="SEQ_INV_TIPOS_ADMINISTRACION",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_TIPOS_ADMINISTRACION_IDTIPOADMINISTRACION_GENERATOR")
	@Column(name="id_tipo_administracion", unique=true, nullable=false)
	private Integer idTipoAdministracion;

	@Column(name="tipo_administracion", nullable=false, length=20)
	private String tipoAdministracion;

	//bi-directional many-to-one association to InvMedicamento
	@OneToMany(mappedBy="invTiposAdministracion")
	private List<InvMedicamento> invMedicamentos;

	public InvTiposAdministracion() {
	}

	public Integer getIdTipoAdministracion() {
		return this.idTipoAdministracion;
	}

	public void setIdTipoAdministracion(Integer idTipoAdministracion) {
		this.idTipoAdministracion = idTipoAdministracion;
	}

	public String getTipoAdministracion() {
		return this.tipoAdministracion;
	}

	public void setTipoAdministracion(String tipoAdministracion) {
		this.tipoAdministracion = tipoAdministracion;
	}

	public List<InvMedicamento> getInvMedicamentos() {
		return this.invMedicamentos;
	}

	public void setInvMedicamentos(List<InvMedicamento> invMedicamentos) {
		this.invMedicamentos = invMedicamentos;
	}

	public InvMedicamento addInvMedicamento(InvMedicamento invMedicamento) {
		getInvMedicamentos().add(invMedicamento);
		invMedicamento.setInvTiposAdministracion(this);

		return invMedicamento;
	}

	public InvMedicamento removeInvMedicamento(InvMedicamento invMedicamento) {
		getInvMedicamentos().remove(invMedicamento);
		invMedicamento.setInvTiposAdministracion(null);

		return invMedicamento;
	}

}