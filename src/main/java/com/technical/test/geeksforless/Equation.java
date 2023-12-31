package com.technical.test.geeksforless;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="equation")
public class Equation {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id", nullable=false)
    private UUID id;

    @Column(name="expression", nullable=false)
    @NotEmpty
    @Valid
    private String expression;

    @ElementCollection
    @CollectionTable(name = "equation_roots", joinColumns = @JoinColumn(name = "equation_id"))
    @Column(name = "root")
    private  List<Double> roots = new ArrayList<>();
}
