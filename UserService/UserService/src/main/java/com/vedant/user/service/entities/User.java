package com.vedant.user.service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tblUser")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String uniqueId;
    private String userName;
    private String name;
    private String email;
    private long mobile;
    @Transient
    private List<Rating> productRatings = new ArrayList<>();

}
