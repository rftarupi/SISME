package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_roles database table.
 * 
 */
@Entity
@Table(name="seg_roles")
@NamedQuery(name="SegRole.findAll", query="SELECT s FROM SegRole s")
public class SegRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_ROLES_IDROL_GENERATOR", sequenceName="SEQ_SEG_ROLES",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_ROLES_IDROL_GENERATOR")
	@Column(name="id_rol", unique=true, nullable=false)
	private Integer idRol;

	@Column(name="descripcion_rol", nullable=false, length=30)
	private String descripcionRol;

	//bi-directional many-to-one association to SegUsuario
	@OneToMany(mappedBy="segRole")
	private List<SegUsuario> segUsuarios;

	public SegRole() {
	}

	public Integer getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcionRol() {
		return this.descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public List<SegUsuario> getSegUsuarios() {
		return this.segUsuarios;
	}

	public void setSegUsuarios(List<SegUsuario> segUsuarios) {
		this.segUsuarios = segUsuarios;
	}

	public SegUsuario addSegUsuario(SegUsuario segUsuario) {
		getSegUsuarios().add(segUsuario);
		segUsuario.setSegRole(this);

		return segUsuario;
	}

	public SegUsuario removeSegUsuario(SegUsuario segUsuario) {
		getSegUsuarios().remove(segUsuario);
		segUsuario.setSegRole(null);

		return segUsuario;
	}

}