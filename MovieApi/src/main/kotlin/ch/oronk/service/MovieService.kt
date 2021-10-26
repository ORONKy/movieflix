package ch.oronk.service

import ch.oronk.model.Group

class MovieService {

    private val groups: HashMap<Int, Group> = HashMap()

    suspend fun createGrope(){
        groups.keys.sortedDescending().getOrElse(0){1}

        //Group()
    }
}