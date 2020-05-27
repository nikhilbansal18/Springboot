package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository("postgres")
public class PersonDataAcessService implements PersonDao{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDataAcessService(JdbcTemplate jdbcTemplate) {
		//super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertPerson(UUID id, Person person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Person> selectAllPeople() {
		final String sql = "select id,name from person";
		List<Person> person= jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID personId = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			return new Person(personId,name);
		});
		return person;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		final String sql = "select id,name from person where id = ?";
		Person person= jdbcTemplate.queryForObject(sql, new Object[] {id} ,(resultSet, i) -> {
			UUID personId = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			return new Person(personId,name);
		});
		return Optional.ofNullable(person);

	}

	@Override
	public int deletePersonById(UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		// TODO Auto-generated method stub
		return 0;
	}

}
