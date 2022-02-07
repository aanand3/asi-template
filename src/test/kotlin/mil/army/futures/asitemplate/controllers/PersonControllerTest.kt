package mil.army.futures.asitemplate.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.PersonDTO
import mil.army.futures.asitemplate.services.PersonService
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(PersonController::class)
internal class PersonControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var mockPersonService: PersonService

    @Test
    fun `should save a person when a new one is created`() {
        val testPerson = Person(name = "first-person")
        every { mockPersonService.createPerson(testPerson) } returns testPerson

        mockMvc.post("/person") {
            contentType = MediaType.TEXT_PLAIN
            content = testPerson.name
        }.andExpect {
            status { is2xxSuccessful() }
            content { string(containsString(testPerson.name)) }
        }

        verify { mockPersonService.createPerson(testPerson) }
    }

    @Test
    fun `should get all persons`() {
        val personList = listOf(
            Person(name = "first-person"),
            Person(name = "second-person")
        )
        every { mockPersonService.getAllPersons() } returns personList

        val mvcResult = mockMvc.get("/person").andExpect {
            status { is2xxSuccessful() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn()

        val mapper = jacksonObjectMapper()
        val resultList = mapper.readValue<List<PersonDTO>>(mvcResult.response.contentAsString)

        assertThat(resultList).containsExactlyInAnyOrderElementsOf(personList.map{it.toDTO()})
    }
}