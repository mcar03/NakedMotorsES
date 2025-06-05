package backend.spring.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import backend.spring.modelos.Categoria;
import backend.spring.repositorios.RepoCategoria;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/admin")
public class CategoriaController {
    
    @Autowired
    private RepoCategoria repoCategoria;

    @GetMapping("/categoria")
    public String findAll(Model model) {
        model.addAttribute("categorias", repoCategoria.findAll());
        return "admin/categorias";
    }

    @GetMapping("/categoria/hijos/{id}")
    public String findChilds(@PathVariable("id") Long id, Model model) {
        Optional<Categoria> oCategoria = repoCategoria.findById(id);
        
        if (oCategoria.isPresent()) {
            Categoria padre = oCategoria.get();
            model.addAttribute("categorias", repoCategoria.findByPadre(padre));
            return "admin/categorias";
        } else {
            model.addAttribute("titulo", "Categoría: ERROR");
            model.addAttribute("mensaje", "No se encontró la categoría en la base de datos.");
            return "error";
        }
    }

    @GetMapping("/categoria/add")
    public String addForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("categorias", repoCategoria.findAll());
        return "admin/categorias-add";
    }

    @PostMapping("/categoria/add")
    public String saveCategoria(
        @Valid @ModelAttribute("categoria") Categoria categoria,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", repoCategoria.findAll());
            return "admin/categorias-add";
        }

        repoCategoria.save(categoria);
        return "redirect:/admin/categoria";
    }

    @GetMapping("/categoria/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        Optional<Categoria> categoria = repoCategoria.findById(id);
        if (categoria.isPresent()) {
            model.addAttribute("categoria", categoria.get());
            return "admin/categorias-del";
        } else {
            model.addAttribute("titulo", "Error");
            model.addAttribute("mensaje", "La categoría no existe.");
            return "error";
        }
    }

    @PostMapping("/categoria/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        try {
            repoCategoria.deleteById(id);    
            return "redirect:/admin/categoria";
        } catch (Exception e) {
            model.addAttribute("titulo", "Error al eliminar");
            model.addAttribute("mensaje", "No se pudo eliminar la categoría.");
            return "error";
        }
    }

    @GetMapping("/categoria/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Optional<Categoria> categoria = repoCategoria.findById(id);
        List<Categoria> categorias = repoCategoria.findAll();
        
        if (categoria.isPresent()) {
            model.addAttribute("categoria", categoria.get());
            model.addAttribute("categorias", categorias);
            return "admin/categorias-edit";
        } else {
            model.addAttribute("titulo", "Error en categorías");
            model.addAttribute("mensaje", "Categoría no encontrada");
            return "error";
        }
    }

    @PostMapping("/categoria/edit/{id}")
    public String updateCategoria(
        @PathVariable("id") Long id,
        @Valid @ModelAttribute("categoria") Categoria categoria,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", repoCategoria.findAll());
            return "admin/categorias-edit";
        }

        repoCategoria.save(categoria);
        return "redirect:/admin/categoria";
    }
}
