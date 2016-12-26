package com.clinton

class User {

    String userName
    String password
    Date dateCreated

    static hasOne = [profile : Profile]
    static hasMany = [posts : Post, tags : Tag, following : User]

    static mapping = {
        posts sort: "dateCreated", order: 'desc'
    }

    static constraints = {
        userName maxSize: 20, unique: true, nullable: false
        password minSize: 6, nullable: false, validator: {password, user ->
            password != user.userName
        }
        profile nullable: true
    }
}
