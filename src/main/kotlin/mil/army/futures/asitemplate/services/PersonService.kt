package mil.army.futures.asitemplate.services

import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.repositories.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(private val personRepository: PersonRepository) {
    fun createPerson(person: Person): Person = personRepository.save(person)

    fun getAllPersons(): List<Person> = personRepository.findAll()
}
