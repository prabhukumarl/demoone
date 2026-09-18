package com.datajpa.demo.jpa_mappings;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_post")
//from lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private LocalDate postDate;

}
