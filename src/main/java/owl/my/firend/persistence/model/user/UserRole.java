package owl.my.firend.persistence.model.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import owl.my.firend.persistence.util.type.user.UserRoleDefine;

@Entity
@Table(name = "USER_ROLE", catalog = "public")
@Indexed
@Analyzer(impl = org.apache.lucene.analysis.standard.StandardAnalyzer.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRole implements Serializable {

	private static final long serialVersionUID = -7103854620676579324L;

	@Id
	@GenericGenerator(name = "UserRoleInc", strategy = "increment")
	@GeneratedValue(generator = "UserRoleInc")
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "ROLE_NAME")
	@Enumerated(EnumType.STRING)
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	private UserRoleDefine roleName;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<UserDetails> userDetails = new ArrayList<>();

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public UserRoleDefine getRoleName() {
		return roleName;
	}

	public void setRoleName(UserRoleDefine roleName) {
		this.roleName = roleName;
	}

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", roleName=" + roleName + ", userDetails=" + userDetails + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((userDetails == null) ? 0 : userDetails.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (roleName != other.roleName)
			return false;
		if (userDetails == null) {
			if (other.userDetails != null)
				return false;
		} else if (!userDetails.equals(other.userDetails))
			return false;
		return true;
	}
}