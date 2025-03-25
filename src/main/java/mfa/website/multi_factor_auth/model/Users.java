package mfa.website.multi_factor_auth.model;

import jakarta.persistence.*; // Import JPA annotations for database mapping


@Entity
@Table(name = "users") // Maps this entity to the "users" table in the database
public class Users {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing primary key
    private long id;

    @Column(unique = true, nullable = false) // Ensures the username is unique and cannot be null
    private String username;

    @Column(nullable = false) // Password cannot be null
    private String password;

    @Column(nullable = false) // Role cannot be null
    private String role;


    public Users() {}


    public Users(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter methods to retrieve user details
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    // Setter methods to modify user details
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}
