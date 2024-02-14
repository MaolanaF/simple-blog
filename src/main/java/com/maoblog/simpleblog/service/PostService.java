package com.maoblog.simpleblog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maoblog.simpleblog.repository.PostJpaRepository;
import com.maoblog.simpleblog.repository.PostRepository;
import com.maoblog.simpleblog.vo.Post;


@Service
public class PostService {
	private static List<Post> posts;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
    PostJpaRepository postJpaRepository;
	
//	public Post getPost() {
//		Post post = new Post(1L, "Mao", "Maolana's Post", "Welcome to My blog");
//		return post;
//	}
	
//	public List<Post> getPosts(){
//		posts = new ArrayList<>();
//		posts.add(new Post(1L, "Mao", "Maolana's Post", "Welcome to My blog"));
//		posts.add(new Post(2L, "Iman", "Ini Iman", "Welcome to my channel"));
//		
//		return posts;
//	}
	
	public Post getPost(Long id){
        Post post = postJpaRepository.findOneById(id);
        return post;
    }

	
//	public Post getPost(Long id) {
//		Post post = postRepository.findOne(id);
//		
//		return post;
//	}
	
	public List<Post> getPosts() {
        List<Post> postlist = postJpaRepository.findAllByOrderByUpdtDateDesc();    
        return postlist;
    }
	
	public List<Post> getPostsOrderByUpdtAsc(){
        List<Post> postlist = postJpaRepository.findAllByOrderByUpdtDateAsc();
        return postlist;
    }
	
	public List<Post> getPostsOrderByRegDesc(){
        List<Post> postlist = postRepository.findPostOrderByRegDateDesc();
        return postlist;
    }
	
	public List<Post> searchPostByTitle(String query){
        List<Post> posts = postJpaRepository.findByTitleContainingOrderByUpdtDateDesc(query);
        return posts;
    }
	
	public List<Post> searchPostByContent(String query){
        List<Post> posts = postJpaRepository.findByContentContainingOrderByUpdtDateDesc(query);
        return posts;
    }
	
	public boolean savePost(Post post){
        Post result = postJpaRepository.save(post);
        boolean isSuccess = true;

        if(result == null){
            isSuccess = false;
        }

        return isSuccess;

    }
	
	public boolean deletePost(Long id){
        Post result = postJpaRepository.findOneById(id);

        if(result == null){
            return false;
        }

        postJpaRepository.deleteById(id);

        return true;

    }

	public boolean updatePost(Post post) {
		Optional<Post> optionalPost = postJpaRepository.findById(post.getId());
	    
	    if (optionalPost.isPresent()) {
	        Post result = optionalPost.get();
	        boolean changes = false;
	        
	        if (post.getTitle() != null && !post.getTitle().isEmpty()) {
	            result.setTitle(post.getTitle());
	            changes = true;
	        }
	        
	        if (post.getContent() != null && !post.getContent().isEmpty()) {
	            result.setContent(post.getContent());
	            changes = true;
	        }
	        
	        if(changes == true) {
	        	result.setUpdtDate(post.getUpdtDate());
	        }
	        
	        postJpaRepository.save(result);
	        
	        return true; 
	    } else {	    	
	        return false;
	    }
		
	}
}
