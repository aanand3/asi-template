alter table person
    add column team_id bigint not null
        constraint person_team_id
            references team
