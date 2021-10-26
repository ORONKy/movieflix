package ch.oronk

import ch.oronk.service.TMDbService

fun main() {
    var tmDbService = TMDbService()
    tmDbService.getRandomMovie()
}