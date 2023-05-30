package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
//    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
//        this.userDao = userDao;
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model){
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

//    @GetMapping("/show")
//    public String post(Model model){
//        Post post1 = new Post("Greetings", "This should show.");
//        model.addAttribute("post1", post1);
//        return "posts/show";
//    }

//    @GetMapping("/index")
//    public String postList(Model model){
//        Post post1 = new Post("Hello", "Please Work.");
//        Post post2 = new Post("Hello", "Hopefully this works.");
//        List<Post> postList = new ArrayList<>(List.of(post1, post2));
//        model.addAttribute("postList", postList);
//        return "posts/index";
//    }

    @GetMapping("/create")
    public String createPost(){
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
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/posts";
    }
}
