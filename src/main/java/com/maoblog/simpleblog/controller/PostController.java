package com.maoblog.simpleblog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maoblog.simpleblog.service.PostService;
//import com.maoblog.simpleblog.service.PostService;
import com.maoblog.simpleblog.vo.Post;


@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/posts")
	public List<Post> getPosts() {
		List<Post> posts = postService.getPosts();
		return posts;
	}
	
	@GetMapping("/post")
	public Post getPost(@RequestParam("id") int id) {
		Post post = postService.getPost(id);
		return post;
	}
	
	@GetMapping("/post/{id}")
	public Post getPostPathParam(@PathVariable("id") int id) {
		Post post = postService.getPost(id);
		return post;
	}
	
}
