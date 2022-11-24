package ru.alexey.library.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.alexey.library.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setYearOfBirth(rs.getString("year_of_birth"));

        return person;
    }
}
