package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.model.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository usersDao;

    public User randomUser(UserRepository usersDao){
        List<User> allUsers = usersDao.findAll();
        int randomInt = new Random().nextInt(allUsers.size());
        return allUsers.get(randomInt);
    }
    public PostController(PostRepository postDao, UserRepository usersDao) {
        this.postDao = postDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model){
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/user/{email}/ad")
    public String userPost(@PathVariable String email, Model model){
        User user = usersDao.findUserByEmail(email);
        model.addAttribute("userEmail", user.getPosts());
        return "posts/user_post";
    }

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
        User user = randomUser(usersDao);
        Post post = new Post(title, description, user);
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
