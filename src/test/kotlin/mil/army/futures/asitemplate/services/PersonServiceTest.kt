package mil.army.futures.asitemplate.services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.repositories.PersonRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class PersonServiceTest() {
    @MockK
    private lateinit var mockPersonRepository: PersonRepository

    @InjectMockKs
    private lateinit var personService: PersonService

    @Test
    fun `should retrieve all persons`() {
        val expectedPersons = listOf(Person(1L, "first-person"), Person(2L, "second-person"))
        every { mockPersonRepository.findAll() } returns expectedPersons

        val actualPersons: List<Person> = personService.getAllPersons()

        Assertions.assertThat(actualPersons).isEqualTo(expectedPersons)
    }

    @Test
    fun `should create a new person`() {
        every { mockPersonRepository.save(any()) } answers { firstArg() }
        val personToSave = Person(1L, "first-person")

        personService.createPerson(personToSave)

        verify { mockPersonRepository.save(personToSave) }
    }
}