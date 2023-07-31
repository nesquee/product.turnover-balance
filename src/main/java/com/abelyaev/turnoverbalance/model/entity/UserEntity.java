package com.abelyaev.turnoverbalance.model.entity;

import com.abelyaev.turnoverbalance.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileduplicate_seq")
    @SequenceGenerator(sequenceName = "fileduplicate_seq", name = "fileduplicate_seq", allocationSize = 1)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    private Date createDate;

    private Date updateDate;

}
