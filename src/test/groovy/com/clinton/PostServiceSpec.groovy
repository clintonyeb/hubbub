package com.clinton

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(PostService)
@Mock([User, Post])
class PostServiceSpec extends Specification {

    void "Service can successfully update user posts"(){
        given: "A valid user"
        def chuck = new User(userName: 'chuck', password: 'chuckpasswordhere').save(failOnError: true)

        when: "User tries to update with the service"
        def post = service.createPost('chuck', "I am on a holiday in London")

        then: "We can verify that the post is saved"
        post.content == "I am on a holiday in London"
        chuck.posts.size() == 1
    }

    void "Invalid posts should result in exceptoins"(){
        given: "A valid user"
        def chuck = new User(userName: 'chuck', password: 'chuckpasswordhere').save(failOnError: true)

        when: "User tries to update with an invalid post"
        def post = service.createPost('chuck', null)

        then: "An exception must be thrown"
        thrown(PostException)


    }

    void "Empty posts should result in exceptoins"(){
        given: "A valid user"
        def chuck = new User(userName: 'chuck', password: 'chuckpasswordhere').save(failOnError: true)

        when: "User tries to update with an invalid post"
        def post = service.createPost('chuck', '')

        then: "An exception must be thrown"
        thrown(PostException)


    }
}
