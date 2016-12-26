package com.clinton

class Tag {
    String name
    User user
    static hasMany = [posts : Post]

    static constraints = {
        name blank: false
    }
    static belongsTo = [User, Post]
}
