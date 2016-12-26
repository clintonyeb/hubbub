package com.clinton

import grails.transaction.Transactional

@Transactional
class PostService {

    Post createPost(String userName, String content) {
        def user = User.findByUserName(userName)
        if (user){
            Post post = new Post(content: content)
            user.addToPosts(post)
            if (post.validate() && user.save() )return post
            else throw new PostException(message: "Invalid Post", post: post)
        }else throw new PostException(message: "Invalid user-id")
    }
}

class PostException extends RuntimeException{
    Post post
    String message
}
