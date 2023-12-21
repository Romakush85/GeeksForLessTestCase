package com.technical.test.geeksforless;

import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@Controller
@Data
@RequestMapping("/equations")
public class EquationsController {
    private final EquationsService equationsService;
    private final EquationValidator equationValidator;

    @Autowired
    public EquationsController(EquationsService equationsService, EquationValidator equationValidator) {
        this.equationsService = equationsService;
        this.equationValidator = equationValidator;
    }

    @InitBinder("equation")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(equationValidator);
    }

    @GetMapping()
    public String getAllEquations(Model model) {
        model.addAttribute("equations", equationsService.findAll());
        return "equations";
    }

    @GetMapping("/searchByRoot")
    public String getSearchPage(Model model) {
        model.addAttribute("rootWrapper", new RootWrapper());
        return "searchByRoot";
    }

    @GetMapping("/searchBySingleRoot")
    public  String findBySingleRoot(Model model, @ModelAttribute("rootWrapper") @Valid RootWrapper wrapper,
                                    BindingResult rootResult) {
        if(wrapper.getPossibleRoots().get(0).isEmpty() || rootResult.hasErrors())
            return "searchByRoot";

        model.addAttribute("equations", equationsService.findAllBySingleRoot(wrapper));
        model.addAttribute("rootWrapper", new RootWrapper());
        return "searchByRoot";
    }

    @GetMapping("/searchByOneOfRoots")
    public  String findByRoot(Model model, @ModelAttribute("rootWrapper") @Valid RootWrapper wrapper,
                                    BindingResult rootResult) {
        if(wrapper.getPossibleRoots().get(0).isEmpty() || rootResult.hasErrors())
            return "searchByRoot";

        model.addAttribute("equations", equationsService.findAllByRoot(wrapper));
        model.addAttribute("rootWrapper", new RootWrapper());
        return "searchByRoot";
    }

    @GetMapping("/new")
    public String newEquation(Model model) {
        model.addAttribute("equation", new Equation());
        model.addAttribute("rootWrapper", new RootWrapper());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("equation") @Valid Equation equation,
                         BindingResult expressionResult,
                         @ModelAttribute("rootWrapper")  @Valid RootWrapper rootWrapper,
                         BindingResult rootsResult) {
            if(expressionResult.hasErrors() || rootsResult.hasErrors())
                return "new";
            equationsService.save(equation, rootWrapper);
            return "redirect:/equations";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id) {
        equationsService.delete(id);
        return "redirect:/equations";
    }
}
