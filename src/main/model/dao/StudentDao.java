package main.model.dao;

import main.model.entity.StudentEntity;

import java.util.List;

public interface StudentDao {
    List<StudentEntity> findAll();

    StudentEntity findById(int id);

    int insert(StudentEntity student);

    void delete(int id);

    //int update(StudentEntity student);

    //int save(Student student);
}
