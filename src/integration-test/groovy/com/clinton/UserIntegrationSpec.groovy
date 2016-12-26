package com.clinton


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class UserIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Saving a User to the database"(){

        given: "A new user to save"
        def joe = new User(userName: "joe", password: "joe1111")

        when: "The user is saved"
        joe.save(flush: true)

        then: "Confirm that user has being saved"
        joe.errors.errorCount == 0
        joe.id != null
        User.get(joe.id).userName == joe.userName
    }

    void "Updating a User Data when Changed"(){

        given: "AN existing user"
        def existingUser = new User(userName: "joe", password: "joe1111")
        existingUser.save(failOnError:true)

        when: "A user changes properties"
        def newUser = User.get(existingUser.id)
        newUser.password = 'secret'
        newUser.save(failOnError:true)

        then: "Validate user properties are  updated"
        User.get(existingUser.id).password == newUser.password
    }

    void "When deleting a User"(){

        given: "An existing user"
        def oldUser = new User(userName: "joe2", password: "joe1111")
        oldUser.save(failOnError:true)

        when: "User is deleted"
        User user = User.get(oldUser.id)
        user.delete(flush: true)

        then:"Check if user does not exist anymore"
        !User.exists(user.id)
    }

    void "Produces error when a user fails validation during saving"(){

        given: "A new user being created"
        def newUser = new User(userName: "clintonjoe", password: "j")

        when: "User is validated"
        newUser.validate()

        then: "Errors should be thrown appropriately"
        newUser.hasErrors()
        newUser.errors.getFieldError("password").code == "minSize.notmet"
        newUser.errors.getFieldError("password").rejectedValue == "j"
        !newUser.errors.getFieldError("userName")


    }
}
