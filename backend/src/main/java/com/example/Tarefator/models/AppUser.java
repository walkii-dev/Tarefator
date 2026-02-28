package com.example.Tarefator.models;

import com.example.Tarefator.dtos.AuthRegisterDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "appusers")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,name = "full_name")
    private String fullName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false,length = 20)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "user_role")
    private AppUserRole userRole;

    @OneToMany(mappedBy = "owner")
    private List<Task> userTasks = new ArrayList<Task>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(AppUserRole userRole) {
        this.userRole = userRole;
    }

    public List<Task> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<Task> userTasks) {
        this.userTasks = userTasks;
    }

    public AppUser(long id, String fullName, String email, String password, AppUserRole userRole, List<Task> userTasks) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userRole = AppUserRole.USER;
        this.userTasks = userTasks;
    }
    public AppUser (AuthRegisterDTO registerData){
        this.fullName = registerData.fullname();
        this.email = registerData.email();
        this.password = registerData.password();
        this.userRole = AppUserRole.USER;
        this.userTasks = new ArrayList<>();
    }

    public AppUser() {
        // empty constructor
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+userRole.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return getId() == appUser.getId() && Objects.equals(getFullName(), appUser.getFullName()) && Objects.equals(getEmail(), appUser.getEmail()) && Objects.equals(getPassword(), appUser.getPassword()) && getUserRole() == appUser.getUserRole() && Objects.equals(getUserTasks(), appUser.getUserTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getEmail(), getPassword(), getUserRole(), getUserTasks());
    }
}


