package com.d424.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Model class representing an application user.
 * <p>
 * Contains information about the user - their id, username, password (hashed) and authorities (user roles).
 */
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(nullable = false, unique = true)
    private String username;    private String displayName;

    @JsonIgnore
    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(name = "img_url")
    private String profileImageUrl;

    @Column(name = "short_bio")
    private String shortBio;

    // User roles
    @Transient
    private Set<Authority> authorities = new HashSet<>();

    /*
     * The activated property is not currently used by this application. It exists because it
     * is required by the common `security` package code. This allows for a user to be deactivated
     * (preventing log-in) in the future.
     *
     * For now, it is intentionally set to true in the constructors to always have 'active' users
     * and is not updatable. (There is no setter for this property.)
     */
    @JsonIgnore
    @Transient
    private boolean activated;

    public User() {}

    public User(int id, String username, String password, String authorities, String displayName, String imageUrl, String bio) {
        this.id = id;
        this.username = username;
        this.password = password;
        if (authorities != null) this.setAuthorities(authorities);
        this.activated = true;
        this.displayName = displayName;
        this.profileImageUrl = imageUrl;
        this.shortBio = bio;
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    private void syncAuthorities() {
        authorities.clear();
        if (role != null) {
            authorities.add(new Authority(role));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return true;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setAuthorities(String authorities) {
        String[] roles = authorities.split(",");
        for (String role : roles) {
            String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
            this.authorities.add(new Authority(authority));
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                activated == user.activated &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, activated, authorities);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", activated=" + activated +
                ", authorities=" + authorities +
                '}';
    }
}
