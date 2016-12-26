/**
 * Created by clinton on 17/7/16.
 */

import com.clinton.Post
import grails.util.Environment

Environment.executeForCurrentEnvironment(new BootStrap().init)
println("There are ${Post.count()} posts in the database")

Post.where {
    content =~ "/%BBQ%/"
}.list()
