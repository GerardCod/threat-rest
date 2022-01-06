package dev.gerardo.threatrest.apirest.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpBanned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "the ip is required")
    @NotBlank(message = "the ip cannot be empty")
    private String ip;

    @Column(nullable = false)
    private Date bannedAt;

    @PrePersist
    private void prePersist() {
        this.bannedAt = new Date();
    }
}
