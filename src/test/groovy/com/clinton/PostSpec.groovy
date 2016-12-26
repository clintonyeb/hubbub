package com.clinton

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(PostController)
@Mock([User, Post])
class PostSpec extends Specification {

   def "Getting a user's timeline given his id"(){

       given: "An existing user called chuck"
       User chuck = new User(
               userName: "chuck_norris",
               password: "password")
       chuck.addToPosts(new Post(content: "A first post"))
       chuck.addToPosts(new Post(content: "A second post"))
       chuck.save(failOnError: true)

       and: "His id is also given"
       params.id = chuck.userName

       when: "The timeline is requested"
       def model = controller.timeline()

       then: "the user must be in the returned model"
       model.user.userName == 'chuck_norris'
       model.user.posts.size() == 2
   }

    def "When given an invalid id"(){

        given: "An existing user called chuck"
        params.id = "invalid"

        when: "The timeline is requested"
        def model = controller.timeline()

        then: "the user must be in the returned model"
       response.status == 404
    }

    def "Adding a new post by a valid user"(){
        given: "A user with posts in the db"
        User chuck = new User(
                userName: "chuck_norris",
                password: "password").save(failOnError: true)
        and: "A loginId parameter"
        params.id = chuck.userName

        and: "Some content for the post"
        params.content = "Chuck Norris can unit test entire applications with a single assert."

        when: "addPost is invoked"
        def model = controller.addPost()

        then: "our flash message and redirect confirms the success"
        flash.message == "Successfully created Post"
        response.redirectedUrl == '/post/timeline/${chuck.userName}'
        Post.countByUser(chuck) == 1
    }

}
