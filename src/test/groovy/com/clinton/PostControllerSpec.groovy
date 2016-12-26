package com.clinton

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*

@TestMixin(GrailsUnitTestMixin)
@TestFor(PostController)
@Mock([User, Post])
class PostControllerSpec extends Specification {

    void "Adding a new post to the timeline"() {
       given: "a mock post service"
        def mockService = Mock(PostService)
        1 * mockService.createPost(_, _) >> new Post(content: "Washing dishes")
        controller.postService = mockService

        and: "A valid user is given"
        def joe = new User(userName: 'joe', password: 'joepassword').save(failOnError: true)
        params.id = 'joe'
        params.content = 'Washing Dishes'

        when: "controller is invoked"
        def result = controller.addPost()

        then: "Redirect and flash message must be correct"
        flash.message == "New posts succesfully created: 'Washing dishes'"
        response.redirectUrl == '/post/timeline/joe'

    }
}