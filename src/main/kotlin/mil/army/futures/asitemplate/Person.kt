package mil.army.futures.asitemplate

import javax.persistence.*

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @ManyToOne
    @JoinColumn(name="team_id", nullable = false)
    val team: Team = Team(1, "unallocated")
) {
    fun toDTO(): PersonDTO =
        PersonDTO(
            id = this.id,
            name = this.name,
            team = this.team
        )
}

data class PersonDTO(
    val id: Long,
    val name: String,
    val team: Team
) {
    fun toEntity(): Person =
        Person(
            id = this.id,
            name = this.name,
            team = this.team
        )
}
