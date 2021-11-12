package com.example.mongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			List<String> list=new ArrayList<String>();
			list.add("Computer Science");
			list.add("Maths");

			Address address = new Address(
					"Singapore",
					"Singapore",
					"SG100"
			);
			String emailStore= "johnnykep3@gmail.com";
			Student student = new Student(
					 "Johnny",
					"Kepler",
					 emailStore,
					Gender.MALE,
			        address,
					list,
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			// can be done in optimized way under StudentReposiotry
			repository.findStudentByEmail(emailStore)
					.ifPresentOrElse( s -> {
						System.out.println(s + "already exists!!!");
					}, () -> {
						System.out.println("Inserting student ... " + student);
						repository.insert(student);
					});


			/* for checking email exist
			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(emailStore));

			List<Student> students= mongoTemplate.find(query,Student.class);

			if(students.size() > 1 ){
				throw new IllegalStateException("found students with same id" + emailStore);
			}else{
				System.out.println("Inserting student " + student);
				repository.insert(student);
			}*/

		};
	}
}
