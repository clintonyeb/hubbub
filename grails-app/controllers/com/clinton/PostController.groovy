package com.clinton

class PostController {

    static scaffold = Post
    PostService postService

    def index(){
        if (!params .id) params.id = "chuck"
        redirect(action: 'timeline', params: params)
    }

    def timeline(){
        User user = User.findByUserName(params.id)
        if (user)
        {
            [user : user]
        }
        else response.sendError(404)
    }

    def addPost() {
        try {
            def newPost = postService.createPost(params.id, params.content)
            flash.message = "New posts succesfully created: '${newPost.content}'"
        }catch (PostException e){
            flash.message = e.message
        }

        redirect(action: 'timeline', id: params.id)
    }
}
