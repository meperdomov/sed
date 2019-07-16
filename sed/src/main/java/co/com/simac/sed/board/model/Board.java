package co.com.simac.sed.board.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.board.dto.BoardDTO;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

@Entity
@Table(name = "tablero")
public class Board extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 1334535910374816665L;

	@Column(name = "slug", nullable = false)
	private String slug;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "configuration", nullable = false, length = 65535, columnDefinition = "Text")
	private String configuration;

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	@Override
	public BoardDTO getDTO(String... camposIgnorados) {
		BoardDTO dto = new BoardDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
