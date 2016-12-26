import com.clinton.Post
import com.clinton.Profile
import com.clinton.User

class BootStrap {

    def init = { servletContext ->
            environments {
                development {
                    if (!Post.count()){
                        createSampleData()
                    }
                }
                test {
                    if (!Post.count()){
                        createSampleData()
                    }
                }
            }
    }

    def createSampleData() {
        def chuck = new User(
                userName: 'chuck',
                password: 'chucksecretkey',
                profile: new Profile(fullName: 'Chuck Norris', email: 'chuck@hismail.com')
        ).save(failOnError : true)

        def glen = new User(
                userName: 'glen',
                password: 'glensecretkey',
                profile: new Profile(fullName: 'Glen smith', email: 'glenhuck@hismail.com')
        ).save(failOnError : true)

        def peter = new User(
                userName: 'peter',
                password: 'petersecretkey',
                profile: new Profile(fullName: 'Peter Lebrook', email: 'peter@hismail.com')
        ).save(failOnError : true)

        def frankie = new User(
                userName: 'frankie',
                password: 'frankiesecretkey',
                profile: new Profile(fullName: 'Frankie Hollywood', email: 'frankie@hismail.com')
        ).save(failOnError : true)

        def sara = new User(
                userName: 'sara',
                password: 'sarasecretkey',
                profile: new Profile(fullName: 'Sara Miles', email: 'sara@hismail.com')
        ).save(failOnError : true)

        def phill = new User(
                userName: 'phill',
                password: 'phillsecretkey',
                profile: new Profile(fullName: 'phill Mores', email: 'phill@hismail.com')
        ).save(failOnError : true)

        def dillon = new User(
                userName: 'dillon',
                password: 'dillonsecretkey',
                profile: new Profile(fullName: 'Dillon Gottam', email: 'dillon@hismail.com')
        ).save(failOnError : true)

        chuck.addToFollowing(phill)
        chuck.addToPosts(content: "Been working my roundhouse kicks.")
        chuck.addToPosts(content: "Working on a few new moves. Bit sluggish today.")
        chuck.addToPosts(content: "Tinkering with the hubbub app.")
        chuck.save(failOnError: true)

        phill.addToFollowing(frankie)
        phill.addToFollowing(sara)
        phill.save(failOnError: true)

        Post phillPosts = new  Post(content: "Very first post")
        phill.addToPosts(phillPosts)
        phillPosts.addToTags(name: 'groovy', user: phill)
        phillPosts.addToTags(name: 'grails', user: phill)

        phill.addToPosts(content: "Second post")
        phill.addToPosts(content: "Time for a BBQ!")
        phill.addToPosts(content: "Writing a very very long book")
        phill.addToPosts(content: "Tap dancing")
        phill.addToPosts(content: "Pilate is killing me")
        phill.save(failOnError: true)

        sara.addToPosts(content: "My first post")
        sara.addToPosts(content: "Second post")
        sara.addToPosts(content: "Time for a BBQ!")
        sara.addToPosts(content: "Writing a very very long book")
        sara.addToPosts(content: "Tap dancing")
        sara.addToPosts(content: "Pilates is killing me")
        sara.save(failOnError: true)

        dillon.addToPosts(content: "Pilates is killing me as well")
        dillon.save(failOnError: true, flush: true)
    }
    def destroy = {
    }
}
