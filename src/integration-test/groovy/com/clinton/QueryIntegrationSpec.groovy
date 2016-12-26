package com.clinton


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class QueryIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {

    }

    void "Simple Property Comparison"() {

        given: "Previously saved usernames"

        when: "Users are selected by a simple password match"
        def users = User.where {
            password == 'chucksecretkey'
        }.list(sort: "userName")

        then: "username should be correct"
        users*.userName == ['chuck']
    }

    void "Query on association"() {
        when: "The following criterial is given"
        def users = User.where {
            following.userName == 'sara'
        }.list(sort: 'userName')

        then: "The following result should be obtained"
        users*.userName == ['phill']

    }

    void "Getting a single instance"(){
        when: "The users a queried with a get()"
        def user = User.where {
            userName == 'chuck'
        }.get()

        then: "His password must match"
        user.password == 'chucksecretkey'
    }
}

