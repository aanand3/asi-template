import {useEffect, useState} from "react";
import {getPersons, Person} from "./personsApiClient";

export const PersonFormAndList = () => {
    const [persons, setPersons] = useState<Person[]>([]);

    useEffect(() => {
        getPersons().then(setPersons)
    }, []);

    return (
        <div>
            hello there
        </div>
    )
}