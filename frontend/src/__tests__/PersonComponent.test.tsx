import userEvent from "@testing-library/user-event";
import {render, screen} from "@testing-library/react";
import React from "react";
import {PersonFormAndList} from "../person/PersonFormAndList";
import {createPerson, getPersons} from "../person/personsApiClient";

jest.mock("../person/personsApiClient")

const mockGetPersons = getPersons as jest.MockedFunction<typeof getPersons>;
const mockCreatePerson = createPerson as jest.MockedFunction<typeof createPerson>;


describe('Persons Page', () => {
    const addPerson = (personName: string) => {
        userEvent.type(screen.getByLabelText("Person Name"), personName);
        userEvent.click(screen.getByRole("button", {name: /submit/i}));
    }

    describe("when I view persons", () => {
        it("should display existing persons", async () => {
            mockGetPersons.mockResolvedValue([{id: 1, name: "a person"}, {id: 2, name: "a second person"}])

            render(<PersonFormAndList/>);

            expect(await screen.findByText("a person")).toBeInTheDocument();
            expect(screen.getByText("a second person")).toBeInTheDocument();
        });
    });

    describe("when I add a new person", () => {
        it("should display the new person in existing persons", async () => {
            mockCreatePerson.mockResolvedValueOnce("a person");
            mockGetPersons.mockResolvedValueOnce([]);
            mockGetPersons.mockResolvedValueOnce([{id: 1, name: "a person"}]);

            render(<PersonFormAndList/>);
            addPerson("a person");

            expect(mockCreatePerson).toHaveBeenCalledWith("a person");
            expect(await screen.findByText("a person")).toBeInTheDocument();
        });
    });
});