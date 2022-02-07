package mil.army.futures.asitemplate.controllers

import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.PersonDTO
import mil.army.futures.asitemplate.services.PersonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(
    private val personService: PersonService
) {
    @PostMapping
    fun createPerson(@RequestBody name: String): String {
        val newPerson = Person(name = name)
        return personService.createPerson(newPerson).name
    }

    @GetMapping
    fun getAllPersons(): List<PersonDTO> {
        return personService.getAllPersons().map { it.toDTO() }
    }
}