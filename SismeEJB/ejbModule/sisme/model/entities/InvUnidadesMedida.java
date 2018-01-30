package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inv_unidades_medida database table.
 * 
 */
@Entity
@Table(name="inv_unidades_medida")
@NamedQuery(name="InvUnidadesMedida.findAll", query="SELECT i FROM InvUnidadesMedida i")
public class InvUnidadesMedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INV_UNIDADES_MEDIDA_IDUNIDADMEDIDA_GENERATOR", sequenceName="SEQ_INV_UNIDADES_MEDIDA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_UNIDADES_MEDIDA_IDUNIDADMEDIDA_GENERATOR")
	@Column(name="id_unidad_medida", unique=true, nullable=false)
	private Integer idUnidadMedida;

	@Column(name="unidad_medida", nullable=false, length=20)
	private String unidadMedida;

	//bi-directional many-to-one association to InvMedicamento
	@OneToMany(mappedBy="invUnidadesMedida")
	private List<InvMedicamento> invMedicamentos;

	public InvUnidadesMedida() {
	}

	public Integer getIdUnidadMedida() {
		return this.idUnidadMedida;
	}

	public void setIdUnidadMedida(Integer idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public List<InvMedicamento> getInvMedicamentos() {
		return this.invMedicamentos;
	}

	public void setInvMedicamentos(List<InvMedicamento> invMedicamentos) {
		this.invMedicamentos = invMedicamentos;
	}

	public InvMedicamento addInvMedicamento(InvMedicamento invMedicamento) {
		getInvMedicamentos().add(invMedicamento);
		invMedicamento.setInvUnidadesMedida(this);

		return invMedicamento;
	}

	public InvMedicamento removeInvMedicamento(InvMedicamento invMedicamento) {
		getInvMedicamentos().remove(invMedicamento);
		invMedicamento.setInvUnidadesMedida(null);

		return invMedicamento;
	}

}