package co.com.simac.sed.security.model.oauth;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.type.TimestampType;

@Entity
@Table(name = "oauth_approvals")
public class OauthApprovals {
	@Id
	@Column(name = "client_id")
	private String clientId;

	@Column(name = "userId")
	private String userId;

	@Column(name = "scope")
	private String scope;

	@Column(name = "status")
	private String status;

	@Column(name = "expiresAt")
	private TimestampType expiresAt;

	@Column(name = "lastModifiedAt")
	private TimestampType lastModifiedAt;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TimestampType getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(TimestampType expiresAt) {
		this.expiresAt = expiresAt;
	}

	public TimestampType getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(TimestampType lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

}
