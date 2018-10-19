package gr.di.uoa.pliakos.databasesystems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gr.di.uoa.pliakos.databasesystems.domain.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
