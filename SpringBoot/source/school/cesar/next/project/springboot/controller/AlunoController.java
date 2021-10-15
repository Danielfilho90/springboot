package school.cesar.next.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.cesar.next.project.springboot.exception.AlunoMesmoCPFException;
import school.cesar.next.project.springboot.model.Aluno;
import school.cesar.next.project.springboot.service.AlunoService;

@Controller
public class AlunoController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(final AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping({"/", "/aluno/list", "/aluno/list/"})
    public String showListAluno(Model model) {
        model.addAttribute("alunos", this.alunoService.listagemAlunos());
        return "listaluno";
    }

    @GetMapping({"/aluno/{id}/detail", "/aluno/{id}/detail/"})
    public String showAlunoDetails(@PathVariable(value="id") final Long id, Model model) {
        model.addAttribute("aluno", this.alunoService.obterAluno(id));
        return "detailaluno";
    }

    @GetMapping({"/aluno/{id}/edit", "/aluno/{id}/edit/"})
    public String editAluno(@PathVariable(value="id") final Long id, Model model) {
        model.addAttribute("aluno", this.alunoService.obterAluno(id));
        return "editaluno";
    }

    @PostMapping({"/aluno/edit/save", "/aluno/edit/save/"})
    public String saveEditAluno(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "cpf", required = false) String cpf,
            Model model
    ) {
        Aluno aluno = new Aluno(nome);
        aluno.setId(id);
        aluno.setCpf(cpf);

        try {
            this.alunoService.cadastrarOuAtualizarAluno(aluno);
            model.addAttribute("success", "Aluno atualizado com sucesso!");
            return this.showListAluno(model);
        } catch (AlunoMesmoCPFException e) {
            model.addAttribute("error", e.getMessage());
            return "editaluno";
        }
    }

    @GetMapping({"/aluno/new", "/aluno/new/"})
    public String showNewAlunoScreen() {
        return "newaluno";
    }

    @PostMapping({"/aluno/new/save", "/aluno/new/save/"})
    public String saveNewAluno(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "cpf", required = false) String cpf,
            Model model
    ) {
        Aluno aluno = new Aluno(nome);
        aluno.setCpf(cpf);

        try {
            this.alunoService.cadastrarOuAtualizarAluno(aluno);
            model.addAttribute("success", "Aluno cadastrado com sucesso!");
            return this.showListAluno(model);
        } catch (AlunoMesmoCPFException e) {
            model.addAttribute("error", e.getMessage());
            return "newaluno";
        }
    }

}
