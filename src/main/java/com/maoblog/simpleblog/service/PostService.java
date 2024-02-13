package com.maoblog.simpleblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.maoblog.simpleblog.vo.Post;


@Service
public class PostService {
	private static List<Post> posts;
	
	public Post getPost() {
		Post post = new Post(1L, "Mao", "Maolana's Post", "Welcome to My blog");
		return post;
	}
	
	public List<Post> getPosts(){
		posts = new ArrayList<>();
		posts.add(new Post(1L, "Mao", "Maolana's Post", "Welcome to My blog"));
		posts.add(new Post(2L, "Iman", "Ini Iman", "Welcome to my channel"));
		
		return posts;
	}
	
	public Post getPost(int id) {
		Post post = posts.get(id-1);
		return post;
	}
}