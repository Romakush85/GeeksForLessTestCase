package com.technical.test.geeksforless;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Data
public class EquationsService {
    private final EquationsRepository equationsRepository;
    private final RootChecker rootChecker;

    @Autowired
    public EquationsService(EquationsRepository equationsRepository, RootChecker rootChecker) {
        this.equationsRepository = equationsRepository;
        this.rootChecker = rootChecker;
    }

    public List<Equation> findAll() { return equationsRepository.findAll();
    }

    public List<Equation> findAllBySingleRoot(RootWrapper wrapper) {
        if (!wrapper.getPossibleRoots().isEmpty()) {
            return equationsRepository.findAllByRootsContainingOnlyOne( Double.parseDouble(wrapper.getPossibleRoots().get(0)));
        }
        return new ArrayList<>();
    }

    public List<Equation> findAllByRoot(RootWrapper wrapper) {
        if (!wrapper.getPossibleRoots().isEmpty()) {
            return equationsRepository.findAllByRootsContaining( Double.parseDouble(wrapper.getPossibleRoots().get(0)));
        }
        return new ArrayList<>();
    }



    public void save(Equation equation, RootWrapper rootWrapper) {
        if (!rootWrapper.getPossibleRoots().isEmpty()) {
            for (String root : rootWrapper.getPossibleRoots()) {
                if(root !=null && !root.isBlank() && !root.isEmpty()) {
                    Double rootDouble = Double.parseDouble(root);
                    if(rootChecker.isRootOfEquation(equation, rootDouble)
                            && !equation.getRoots().contains(rootDouble))
                        equation.getRoots().add(rootDouble);
                }
            }
        }
        equationsRepository.save(equation);
    }

    public void delete(UUID id) {equationsRepository.deleteById(id);}
}
