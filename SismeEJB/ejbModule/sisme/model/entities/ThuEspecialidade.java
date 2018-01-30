package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the thu_especialidades database table.
 * 
 */
@Entity
@Table(name="thu_especialidades")
@NamedQuery(name="ThuEspecialidade.findAll", query="SELECT t FROM ThuEspecialidade t")
public class ThuEspecialidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="THU_ESPECIALIDADES_IDESPECIALIDAD_GENERATOR", sequenceName="SEQ_THU_ESPECIALIDADES",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="THU_ESPECIALIDADES_IDESPECIALIDAD_GENERATOR")
	@Column(name="id_especialidad", unique=true, nullable=false)
	private Integer idEspecialidad;

	@Column(name="descripcion_especialidad", length=50)
	private String descripcionEspecialidad;

	@Column(name="nombre_especialidad", nullable=false, length=30)
	private String nombreEspecialidad;

	//bi-directional many-to-one association to ThuMedico
	@OneToMany(mappedBy="thuEspecialidade")
	private List<ThuMedico> thuMedicos;

	public ThuEspecialidade() {
	}

	public Integer getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getDescripcionEspecialidad() {
		return this.descripcionEspecialidad;
	}

	public void setDescripcionEspecialidad(String descripcionEspecialidad) {
		this.descripcionEspecialidad = descripcionEspecialidad;
	}

	public String getNombreEspecialidad() {
		return this.nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

	public List<ThuMedico> getThuMedicos() {
		return this.thuMedicos;
	}

	public void setThuMedicos(List<ThuMedico> thuMedicos) {
		this.thuMedicos = thuMedicos;
	}

	public ThuMedico addThuMedico(ThuMedico thuMedico) {
		getThuMedicos().add(thuMedico);
		thuMedico.setThuEspecialidade(this);

		return thuMedico;
	}

	public ThuMedico removeThuMedico(ThuMedico thuMedico) {
		getThuMedicos().remove(thuMedico);
		thuMedico.setThuEspecialidade(null);

		return thuMedico;
	}

}