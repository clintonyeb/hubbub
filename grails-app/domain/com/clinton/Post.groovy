package com.clinton

class Post {

    String content
    Date dateCreated
    static belongsTo = [user : User]
    static mapping = {
        sort dateCreated : "desc"
    }
    static hasMany = [tags : Tag]

    static constraints = {
        content blank: false, maxSize: 1000

    }
}
