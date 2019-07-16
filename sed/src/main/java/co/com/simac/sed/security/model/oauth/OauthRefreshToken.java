package co.com.simac.sed.security.model.oauth;



import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "token_id")
	private String tokenId;
	
	@Column(name = "token")
	private Blob token;
	
	@Column(name = "authentication")
	private Blob authentication;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Blob getToken() {
		return token;
	}

	public void setToken(Blob token) {
		this.token = token;
	}

	public Blob getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Blob authentication) {
		this.authentication = authentication;
	}
	
}
