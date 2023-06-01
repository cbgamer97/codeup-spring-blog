package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.model.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository usersDao;

    public PostController(PostRepository postDao, UserRepository usersDao) {
        this.postDao = postDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/index")
    public String homePage(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/create")
    public String createPost(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/create")
    public String submitForm(@ModelAttribute Post post) {
        User user = usersDao.findUserById(1L);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }
}
