package owl.my.firend.persistence.model.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
//import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import owl.my.firend.bussiness.annotation.model.UserDetailsHelper;

@Entity
@Table(name = "USER_DETAILS")
@Indexed
@Analyzer(impl = org.apache.lucene.analysis.standard.StandardAnalyzer.class)
@NamedQueries({
		@NamedQuery(name = "UserDetails.findUserByAccount", query = "SELECT r FROM UserDetails r WHERE r.account= :account"),
		@NamedQuery(name = "UserDetails.changeStatusUser", query = "UPDATE UserDetails r set r.enable = :enable WHERE r.account = :account") })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SelectBeforeUpdate
@Model
@UserDetailsHelper
public class UserDetails implements Serializable {

	private static final long serialVersionUID = 2425382264544323308L;
	public static final String FIND_BY_ACCOUNT = "findUserByAccount";
	public static final String FIND_ALL = "finalAll";

	@Id
	@GenericGenerator(name = "UserDetailsInc", strategy = "increment")
	@GeneratedValue(generator = "UserDetailsInc")
	@Column(name = "ID")
	private BigDecimal id;

	@NotEmpty
	@Column(name = "ACCOUNT", nullable = false, unique = true)
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	private String account;

	@NotBlank
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "SALT", nullable = false)
	private String salt;

	@Pattern(regexp = "[a-zA-Z]")
	@Column(name = "NAME")
	private String name;

	@Pattern(regexp = "[a-zA-Z]")
	@Column(name = "LAST_NAME")
	private String lastName;

	@NotBlank
	@Email
	@Column(name = "EMAIL")
	private String email;

	@Pattern(regexp = "[0-9]")
	@Column(name = "NATIONAL_CODE", nullable = false)
	private String nationalCode;

	@Column(name = "JOB")
	private String job;

	@Column(name = "PHONE")
	private Long phone;

	@Column(name = "MOBILE")
	private Long mobile;

	@CreationTimestamp
	@Column(name = "REGISTER_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@Field(index = Index.YES, analyze = Analyze.NO, store = Store.NO)
	private Date registerDate;

	@UpdateTimestamp
	@Column(name = "RMODIFY_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;

	@Column(name = "ENABLE", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	private boolean enable;

	@Lob
	@Column(name = "PERSONAL_IMAGE")
	private byte[] personalImage;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userDetails", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@ContainedIn
	@OrderBy("id ASC")
	private List<AddressBook> addressBookList = new ArrayList<>();

	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	@Fetch(FetchMode.SELECT)
	@ContainedIn
	@OrderBy("id ASC")
	@IndexedEmbedded
	private List<UserRole> userRoleList = new ArrayList<>();

	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "USERS_PERMISSION", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID"))
	@Fetch(FetchMode.SELECT)
	@ContainedIn
	@OrderBy("id ASC")
	@IndexedEmbedded
	private List<UserPermission> userPermissions = new ArrayList<>();

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public byte[] getPersonalImage() {
		return personalImage;
	}

	public void setPersonalImage(byte[] personalImage) {
		this.personalImage = personalImage;
	}

	public List<AddressBook> getAddressBookList() {
		return addressBookList;
	}

	public void setAddressBookList(List<AddressBook> addressBookList) {
		this.addressBookList = addressBookList;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public List<UserPermission> getUserPermissions() {
		return userPermissions;
	}

	public void setUserPermissions(List<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", account=" + account + ", password=" + password + ", salt=" + salt
				+ ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", nationalCode=" + nationalCode
				+ ", job=" + job + ", phone=" + phone + ", mobile=" + mobile + ", registerDate=" + registerDate
				+ ", modifyDate=" + modifyDate + ", enable=" + enable + ", personalImage="
				+ Arrays.toString(personalImage) + ", addressBookList=" + addressBookList + ", userRoleList="
				+ userRoleList + ", userPermissions=" + userPermissions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((addressBookList == null) ? 0 : addressBookList.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enable ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((modifyDate == null) ? 0 : modifyDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nationalCode == null) ? 0 : nationalCode.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + Arrays.hashCode(personalImage);
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((userPermissions == null) ? 0 : userPermissions.hashCode());
		result = prime * result + ((userRoleList == null) ? 0 : userRoleList.hashCode());
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
		UserDetails other = (UserDetails) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (addressBookList == null) {
			if (other.addressBookList != null)
				return false;
		} else if (!addressBookList.equals(other.addressBookList))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enable != other.enable)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (modifyDate == null) {
			if (other.modifyDate != null)
				return false;
		} else if (!modifyDate.equals(other.modifyDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nationalCode == null) {
			if (other.nationalCode != null)
				return false;
		} else if (!nationalCode.equals(other.nationalCode))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (!Arrays.equals(personalImage, other.personalImage))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (userPermissions == null) {
			if (other.userPermissions != null)
				return false;
		} else if (!userPermissions.equals(other.userPermissions))
			return false;
		if (userRoleList == null) {
			if (other.userRoleList != null)
				return false;
		} else if (!userRoleList.equals(other.userRoleList))
			return false;
		return true;
	}

}