package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        return "post index page.";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(@PathVariable int id){
        return "View an individual post.";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewPost(){
        return "View the form for creating a post.";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "Create a new post.";
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
}
