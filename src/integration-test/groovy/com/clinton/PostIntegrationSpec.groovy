package com.clinton


import grails.test.mixin.integration.Integration
import grails.transaction.*
import org.jboss.logging.annotations.Pos
import spock.lang.*

@Integration
@Rollback
class PostIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {

    }

    void "Adding posts to user links posts to user"() {

        given: "A new user"
        def newUser = new User(userName: "joe", password: "joePassword")
        newUser.save(failOnError : true)

        when: "Several posts are added to the user"
        newUser.addToPosts(new Post(content: "I am eating bread this lunch"))
        newUser.addToPosts(new Post(content: "I am spending my summer in London"))
        newUser.addToPosts(new Post(content: "On my way to the Mall"))

        then: "Test if posts have being associated to user"
        newUser.getPosts().size() == 3
    }

    void "Ensure that a User's post can be retrieved"() {

        given: "A new user"
        def newUser = new User(userName: "joe", password: "joePassword")
        newUser.save(failOnError : true, flush : true)
        newUser.addToPosts(new Post(content: "First"))
        newUser.addToPosts(new Post(content: "Second"))
        newUser.addToPosts(new Post(content: "Third"))

        when: "Several posts are added to the user"
        def foundUser = User.get(newUser.id)
        List<String> sortedPosts = foundUser.getPosts().collect{
            it.content
        }.sort()

        then: "User posts can be retrieved"
        sortedPosts == ["First", "Second", "Third"]
    }

    void "Tests tagging several posts from to users"() {

        given: "A user with posts and tags"
        def newUser = new User(userName: "joe", password: "secretpassword")
        Tag groovyTag = new Tag(name: 'Groovy')
        Tag grailTag = new Tag(name: 'Grail')
        newUser.addToTags(grailTag)
        newUser.addToTags(groovyTag)
        newUser.save()

        when: "The user tags two fresh posts"
        Post post1 = new Post(content: "I am eating fish right now")
        post1.addToTags(groovyTag)
        newUser.addToPosts(post1)

        Post post2 = new Post(content: "On my way to holland")
        post2.addToTags(groovyTag)
        post2.addToTags(grailTag)
        newUser.addToPosts(post2)

        then: "All tags must be present"
        newUser.tags*.name.sort() == ['Grail', 'Groovy']
        post1.tags.size() == 1
        post2.tags.size() == 2
    }

    void "Ensure a user can follow other users"(){

        given: "A set of users"
        def joel = new User(userName: 'joel', password: 'joelSecret')
        def porcia = new User(userName: 'porcia', password: 'porciaSecret')
        def brian = new User(userName: 'brian', password: 'brianSecret')

        when: "User try to follow each other"
        joel.addToFollowing(porcia)
        joel.addToFollowing(brian)
        joel.addToFollowing(joel)
        brian.addToFollowing(joel)

        then: "The number of followers must match"
        joel.following.size() == 3
        brian.following.size() == 1
    }
}
