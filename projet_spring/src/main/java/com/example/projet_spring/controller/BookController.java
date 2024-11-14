package com.example.projet_spring.controller;

import com.example.projet_spring.model.Book;
import com.example.projet_spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour gérer les opérations CRUD sur les livres.
 */
@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Affiche la liste des livres.
     * @param model Le modèle de la vue pour passer la liste des livres
     * @return La page de liste des livres
     */
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/list";
    }

    /**
     * Affiche le formulaire pour ajouter un nouveau livre.
     * @param model Le modèle de la vue pour passer un objet Book vide
     * @return La page de formulaire pour ajouter un livre
     */
    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/form";
    }

    /**
     * Enregistre un nouveau livre dans la base de données.
     * @param book L'entité Book remplie via le formulaire
     * @return Redirection vers la liste des livres après enregistrement
     */
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    /**
     * Affiche le formulaire pour modifier un livre existant.
     * @param id L'identifiant du livre à modifier
     * @param model Le modèle de la vue pour passer l'objet Book à modifier
     * @return La page de formulaire pour éditer le livre
     * @throws IllegalArgumentException Si l'ID du livre est invalide
     */
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id)));
        return "book/form";
    }

    /**
     * Met à jour les informations d'un livre existant.
     * @param id L'identifiant du livre à mettre à jour
     * @param book L'entité Book mise à jour via le formulaire
     * @return Redirection vers la liste des livres après mise à jour
     */
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.save(book);
        return "redirect:/books";
    }

    /**
     * Supprime un livre de la base de données.
     * @param id L'identifiant du livre à supprimer
     * @return Redirection vers la liste des livres après suppression
     */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
