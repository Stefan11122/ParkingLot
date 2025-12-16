package org.example.parkinglot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usergroups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "user_group", nullable = false)
    private String userGroup;

    public UserGroup() {}

    public UserGroup(String username, String userGroup) {
        this.username = username;
        this.userGroup = userGroup;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getUserGroup() { return userGroup; }
    public void setUserGroup(String userGroup) { this.userGroup = userGroup; }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userGroup='" + userGroup + '\'' +
                '}';
    }
}
