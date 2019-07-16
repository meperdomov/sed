package co.com.simac.sed.security.model.oauth;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_token")
public class OauthClientToken {

	@Column(name = "token_id")
	private String tokenId;

	@Column(name = "token")
	private byte[] token;

	@Id
	@Column(name = "authentication_id")
	private String authenticationId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "client_id")
	private String client_id;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

}
