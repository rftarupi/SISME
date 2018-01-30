package sisme.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the thu_medicos database table.
 * 
 */
@Entity
@Table(name="thu_medicos")
@NamedQuery(name="ThuMedico.findAll", query="SELECT t FROM ThuMedico t")
public class ThuMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_usu", unique=true, nullable=false, length=10)
	private String cedulaUsu;

	@Column(name="num_consultorio_medico")
	private Integer numConsultorioMedico;

	//bi-directional many-to-one association to CitHorariosMedico
	@OneToMany(mappedBy="thuMedico")
	private List<CitHorariosMedico> citHorariosMedicos;

	//bi-directional many-to-one association to RecCabRecetasMedica
	@OneToMany(mappedBy="thuMedico")
	private List<RecCabRecetasMedica> recCabRecetasMedicas;

	//bi-directional one-to-one association to SegUsuario
	@OneToOne
	@JoinColumn(name="cedula_usu", nullable=false, insertable=false, updatable=false)
	private SegUsuario segUsuario;

	//bi-directional many-to-one association to ThuEspecialidade
	@ManyToOne
	@JoinColumn(name="id_especialidad", nullable=false)
	private ThuEspecialidade thuEspecialidade;

	public ThuMedico() {
	}

	public String getCedulaUsu() {
		return this.cedulaUsu;
	}

	public void setCedulaUsu(String cedulaUsu) {
		this.cedulaUsu = cedulaUsu;
	}

	public Integer getNumConsultorioMedico() {
		return this.numConsultorioMedico;
	}

	public void setNumConsultorioMedico(Integer numConsultorioMedico) {
		this.numConsultorioMedico = numConsultorioMedico;
	}

	public List<CitHorariosMedico> getCitHorariosMedicos() {
		return this.citHorariosMedicos;
	}

	public void setCitHorariosMedicos(List<CitHorariosMedico> citHorariosMedicos) {
		this.citHorariosMedicos = citHorariosMedicos;
	}

	public CitHorariosMedico addCitHorariosMedico(CitHorariosMedico citHorariosMedico) {
		getCitHorariosMedicos().add(citHorariosMedico);
		citHorariosMedico.setThuMedico(this);

		return citHorariosMedico;
	}

	public CitHorariosMedico removeCitHorariosMedico(CitHorariosMedico citHorariosMedico) {
		getCitHorariosMedicos().remove(citHorariosMedico);
		citHorariosMedico.setThuMedico(null);

		return citHorariosMedico;
	}

	public List<RecCabRecetasMedica> getRecCabRecetasMedicas() {
		return this.recCabRecetasMedicas;
	}

	public void setRecCabRecetasMedicas(List<RecCabRecetasMedica> recCabRecetasMedicas) {
		this.recCabRecetasMedicas = recCabRecetasMedicas;
	}

	public RecCabRecetasMedica addRecCabRecetasMedica(RecCabRecetasMedica recCabRecetasMedica) {
		getRecCabRecetasMedicas().add(recCabRecetasMedica);
		recCabRecetasMedica.setThuMedico(this);

		return recCabRecetasMedica;
	}

	public RecCabRecetasMedica removeRecCabRecetasMedica(RecCabRecetasMedica recCabRecetasMedica) {
		getRecCabRecetasMedicas().remove(recCabRecetasMedica);
		recCabRecetasMedica.setThuMedico(null);

		return recCabRecetasMedica;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	public ThuEspecialidade getThuEspecialidade() {
		return this.thuEspecialidade;
	}

	public void setThuEspecialidade(ThuEspecialidade thuEspecialidade) {
		this.thuEspecialidade = thuEspecialidade;
	}

}