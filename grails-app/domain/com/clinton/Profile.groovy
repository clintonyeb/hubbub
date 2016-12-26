package com.clinton

class Profile {
    static belongsTo = [user: User]
    String lastPage
    byte [] photo
    String fullName
    String bio
    String email
    String timezone
    String country
    String jabberAddress


    static constraints = {
        fullName blank: false
        bio nullable: true, maxSize: 1000
        lastPage url: true, nullable: true
        email email: true, blank: false, unique: true
        photo nullable: true, maxSize: 2 * 1024 * 1024
        country nullable: true
        timezone nullable: true
        jabberAddress email: true, nullable: true
    }

    @Override
    String toString() {
        return "Profile: Name: ${fullName}\n\tEmail: ${email}"
    }
}
