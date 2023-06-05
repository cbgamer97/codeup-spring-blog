package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.model.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository usersDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository usersDao, EmailService emailService) {
        this.postDao = postDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    @GetMapping("/index")
    public String homePage(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable long id, Model model){
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "posts/posts";
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
        emailService.prepareAndSend(post, "New Post: " + post.getTitle(), "You have created a new post! Here it is: " + post.getBody());
        return "redirect:/index";
    }
}
