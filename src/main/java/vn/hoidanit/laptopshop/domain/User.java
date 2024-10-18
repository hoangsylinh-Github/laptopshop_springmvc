package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Email(message = "Email không hợp lệ!!!", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    // @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotNull
    @Size(min = 2, message = "Password phải có tối thiểu 2 ký tự!!! ")
    private String password;
    @NotNull
    @Size(min = 3, message = "Fullname phải có tối thiểu 3 ký tự!!! ")
    private String fullName;
    @NotNull
    @Size(min = 3, message = "Address phải có tối thiểu 3 ký tự!!! ")
    private String address;
    @NotNull
    @Size(min = 3, message = "PhoneNumber phải có tối thiểu 3 ký tự!!! ")
    @Size(max = 11, message = "PhoneNumber phải có tối đa không quá 11 kí tự!!!")
    private String phone;
    private String avatar;

    // roleId
    // User many -> to one -> role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // User - one -> many - order
    @OneToMany(mappedBy = "user")
    private List<Order> oders;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOders() {
        return oders;
    }

    public void setOders(List<Order> oders) {
        this.oders = oders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User: [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName
                + ", address=" + address + ", phone=" + phone + ",avatar= " + avatar + "]";
    }
}
