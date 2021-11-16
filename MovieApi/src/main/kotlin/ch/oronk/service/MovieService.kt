package ch.oronk.service

import ch.oronk.model.Group
import ch.oronk.model.Movie

object MovieService {

    private val groups: HashMap<Int, Group> = HashMap()
    private val tmDbService:TMDbService = TMDbService()

    fun createGrope(): Int{
        val id = groups.keys.sortedDescending().getOrElse(0){1}
        val movies = tmDbService.getRandomMovie()
        val group = Group(movies, emptyList())
        groups.put(id+1, group)
        return id
    }

    fun getMoviesForGroup(groupId: Int): List<Movie>{
        return groups.get(groupId)?.movies ?: emptyList();
    }
}