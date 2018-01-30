package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cit_horarios_medicos database table.
 * 
 */
@Entity
@Table(name="cit_horarios_medicos")
@NamedQuery(name="CitHorariosMedico.findAll", query="SELECT c FROM CitHorariosMedico c")
public class CitHorariosMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CIT_HORARIOS_MEDICOS_IDHORARIO_GENERATOR", sequenceName="SEQ_CIT_HORARIOS_MEDICOS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CIT_HORARIOS_MEDICOS_IDHORARIO_GENERATOR")
	@Column(name="id_horario", unique=true, nullable=false)
	private Integer idHorario;

	//bi-directional many-to-one association to CitCitasMedica
	@OneToMany(mappedBy="citHorariosMedico")
	private List<CitCitasMedica> citCitasMedicas;

	//bi-directional many-to-one association to CitHora
	@ManyToOne
	@JoinColumn(name="id_hora", nullable=false)
	private CitHora citHora;

	//bi-directional many-to-one association to ThuMedico
	@ManyToOne
	@JoinColumn(name="cedula_usu", nullable=false)
	private ThuMedico thuMedico;

	public CitHorariosMedico() {
	}

	public Integer getIdHorario() {
		return this.idHorario;
	}

	public void setIdHorario(Integer idHorario) {
		this.idHorario = idHorario;
	}

	public List<CitCitasMedica> getCitCitasMedicas() {
		return this.citCitasMedicas;
	}

	public void setCitCitasMedicas(List<CitCitasMedica> citCitasMedicas) {
		this.citCitasMedicas = citCitasMedicas;
	}

	public CitCitasMedica addCitCitasMedica(CitCitasMedica citCitasMedica) {
		getCitCitasMedicas().add(citCitasMedica);
		citCitasMedica.setCitHorariosMedico(this);

		return citCitasMedica;
	}

	public CitCitasMedica removeCitCitasMedica(CitCitasMedica citCitasMedica) {
		getCitCitasMedicas().remove(citCitasMedica);
		citCitasMedica.setCitHorariosMedico(null);

		return citCitasMedica;
	}

	public CitHora getCitHora() {
		return this.citHora;
	}

	public void setCitHora(CitHora citHora) {
		this.citHora = citHora;
	}

	public ThuMedico getThuMedico() {
		return this.thuMedico;
	}

	public void setThuMedico(ThuMedico thuMedico) {
		this.thuMedico = thuMedico;
	}

}