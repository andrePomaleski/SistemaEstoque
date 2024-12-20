package com.andrePomaleski.SistemaEstoque.model;

import com.andrePomaleski.SistemaEstoque.dto.UserRequestDTO;
import com.andrePomaleski.SistemaEstoque.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(unique = true)
    @NotBlank
    private String email;

    public User(UserRequestDTO user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.email = user.getEmail();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Check for reference equality
        if (obj == null || getClass() != obj.getClass()) return false;  // Null check and class type check
        User user = (User) obj;  // Cast to User
        return Objects.equals(id, user.id);  // Compare userId (use Objects.equals to handle nulls)
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);  // Generate hash code based on userId
    }
}
