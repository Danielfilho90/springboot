package school.cesar.next.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import school.cesar.next.project.springboot.exception.AlunoMesmoCPFException;
import school.cesar.next.project.springboot.exception.TurmaPoucosAlunosException;
import school.cesar.next.project.springboot.model.Turma;
import school.cesar.next.project.springboot.service.TurmaService;

@Controller
public class TurmaController {

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(final TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping("/turma")
    public String cadastraTurma(Model model) {
        Turma turma = new Turma();
        turma.setNome("a");

        try {
            this.turmaService.cadastraTurma(turma);
            model.addAttribute("success", "Turma cadastrada com sucesso!");
        } catch (AlunoMesmoCPFException | TurmaPoucosAlunosException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "turma";
    }

}
