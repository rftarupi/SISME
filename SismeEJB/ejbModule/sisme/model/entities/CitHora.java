package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the cit_horas database table.
 * 
 */
@Entity
@Table(name="cit_horas")
@NamedQuery(name="CitHora.findAll", query="SELECT c FROM CitHora c")
public class CitHora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CIT_HORAS_IDHORA_GENERATOR", sequenceName="SEQ_CIT_HORAS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CIT_HORAS_IDHORA_GENERATOR")
	@Column(name="id_hora", unique=true, nullable=false)
	private Integer idHora;

	@Column(name="hora_fin", nullable=false)
	private Time horaFin;

	@Column(name="hora_inicio", nullable=false)
	private Time horaInicio;

	//bi-directional many-to-one association to CitHorariosMedico
	@OneToMany(mappedBy="citHora")
	private List<CitHorariosMedico> citHorariosMedicos;

	public CitHora() {
	}

	public Integer getIdHora() {
		return this.idHora;
	}

	public void setIdHora(Integer idHora) {
		this.idHora = idHora;
	}

	public Time getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public List<CitHorariosMedico> getCitHorariosMedicos() {
		return this.citHorariosMedicos;
	}

	public void setCitHorariosMedicos(List<CitHorariosMedico> citHorariosMedicos) {
		this.citHorariosMedicos = citHorariosMedicos;
	}

	public CitHorariosMedico addCitHorariosMedico(CitHorariosMedico citHorariosMedico) {
		getCitHorariosMedicos().add(citHorariosMedico);
		citHorariosMedico.setCitHora(this);

		return citHorariosMedico;
	}

	public CitHorariosMedico removeCitHorariosMedico(CitHorariosMedico citHorariosMedico) {
		getCitHorariosMedicos().remove(citHorariosMedico);
		citHorariosMedico.setCitHora(null);

		return citHorariosMedico;
	}

}