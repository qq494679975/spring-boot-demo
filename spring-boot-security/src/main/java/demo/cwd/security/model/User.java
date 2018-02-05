package demo.cwd.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by chenweida on 2018/1/24.
 */
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; //用户名称

    private String account;//用户账号

    private String password;//用户密码

    /**
     * 用户的权限
     *
     * @return
     */
    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 密码
     *
     * @return
     */
    @Override
    @Transient
    public String getPassword() {
        return password;
    }

    /**
     * 账号
     *
     * @return
     */
    @Override
    @Transient
    public String getUsername() {
        return account;
    }

    /**
     * 过期
     * @return
     */
    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否冻结
     * @return
     */
    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证过期
     * @return
     */
    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     * @return
     */
    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
