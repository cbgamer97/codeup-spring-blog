package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(@PathVariable int id){
        return "View an individual post.";
    }

    @GetMapping("/show")
    public String post(Model model){
        Post post1 = new Post("Greetings", "This should show.");
        model.addAttribute("post1", post1);
        return "posts/show";
    }

    @GetMapping("/index")
    public String postList(Model model){
        Post post1 = new Post("Hello", "Please Work.");
        Post post2 = new Post("Hello", "Hopefully this works.");
        List<Post> postList = new ArrayList<>(List.of(post1, post2));
        model.addAttribute("postList", postList);
        return "posts/index";
    }

    @GetMapping("/create")
    public String createPost(Model model){
        return "posts/create";
    }
    @PostMapping("/create")
    public String createPost(@RequestParam(name="title") String title, @RequestParam(name="body") String description){
        Post post = new Post(title, description);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String viewPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/posts";
    }
}
