import {FormEvent, useEffect, useState} from "react";
import {createPerson, getPersons, Person} from "./personsApiClient";

export const PersonFormAndList = () => {
    const [persons, setPersons] = useState<Person[]>([]);
    const [value, setValue] = useState("")

    useEffect(() => {
        refreshPeople()
    }, []);

    const refreshPeople = () => {
        getPersons().then(setPersons)
    }

    const submitForm = (event: FormEvent) => {
        event.preventDefault();
        createPerson(value).then(() => {
            refreshPeople()
        });
    };

    let peopleList = persons.map(person => {
        return <li key={person.id}>{person.name}</li>
    });

    return (
        <div>
            {peopleList}
            <form onSubmit={event => submitForm(event)}>
                <label>Person Name
                    <input name="person-name" type="text"
                           onChange={event => setValue(event.target.value)}
                           value={value}
                    />
                </label>
                <button type="submit">Submit</button>
            </form>
            hello there
        </div>
    )
}