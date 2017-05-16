package main.services;

import main.model.entity.StudentEntity;

import java.util.List;

/**
 * Created by Aleksei Lysov on 19.04.2017.
 */
public interface StudentService {
    List<StudentEntity> getAllStudents();
    void deleteStudent(int id);
    void insert(StudentEntity student);
    //void update(StudentEntity student);
    StudentEntity findById(int id);
}
