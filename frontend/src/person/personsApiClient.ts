import axios from "axios";

export async function createPerson(personName: string): Promise<string> {
    return (await axios.post<string>("/person", personName, {headers: {'Content-Type': 'text/plain'}})).data
}

export async function getPersons(): Promise<Person[]> {
    return (await axios.get<Person[]>("/person")).data
}

export interface Person {
    id: number,
    name: string
}
