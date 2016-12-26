package com.clinton

class UserController {

    static scaffold = User

    def search(){}

    def results(String userName){
        def selectedUsers = User.where {
            userName =~ "%${userName}%"
        }.list()
        return [selectedUsers: selectedUsers,
        term : params.loginId,
        totalUsers : User.count()]
    }

    def register(){
        if (request.method == 'POST'){
            def user = new User(params)
            if (user.validate() && user.save()){
                flash.message = "Updated successfully"
                redirect(uri:"/post/timeline/${params.userName}")
            }else {
                flash.message = "Error registering user"
                return [user: user]
            }
        }
    }
}