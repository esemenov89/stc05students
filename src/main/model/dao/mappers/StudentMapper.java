package main.model.dao.mappers;

import main.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 17.05.2017.
 */
@Component
public interface StudentMapper {
    List getAllStudents();
    StudentEntity getStudentById(int id);
    void deleteStudentById(int id);
}
