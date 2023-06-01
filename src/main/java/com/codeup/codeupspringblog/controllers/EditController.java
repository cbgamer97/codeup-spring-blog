package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.model.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditController {

    private final PostRepository postDao;
    private final UserRepository usersDao;

    public EditController(PostRepository postDao, UserRepository usersDao) {
        this.postDao = postDao;
        this.usersDao = usersDao;
    }


    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitForm(@PathVariable long id, @ModelAttribute Post post){
        postDao.findById(id);
        postDao.save(post);
        return "redirect:/index";
    }
}
