package mil.army.futures.asitemplate

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
) {
    fun toDTO(): PersonDTO =
        PersonDTO(
            id = this.id,
            name = this.name
        )
}

data class PersonDTO(
    val id: Long,
    val name: String
) {
    fun toEntity(): Person =
        Person(
            id = this.id,
            name = this.name
        )
}
