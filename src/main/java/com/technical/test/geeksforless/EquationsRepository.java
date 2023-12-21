package com.technical.test.geeksforless;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquationsRepository extends JpaRepository<Equation, UUID> {
    public List<Equation> findAllByRootsContaining(Double root);

    @Query("SELECT e FROM Equation e WHERE :root MEMBER OF e.roots AND SIZE(e.roots) = 1")
    public List<Equation> findAllByRootsContainingOnlyOne(Double root);
}
