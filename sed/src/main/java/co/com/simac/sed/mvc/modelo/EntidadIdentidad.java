package co.com.simac.sed.mvc.modelo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadIdentidad extends EntidadGenerica {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 7688484753777092306L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toString() {
		return String.format("Entidad de tipo %s con id: %s", new Object[] { getClass().getSimpleName(), getId() });
	}
}
