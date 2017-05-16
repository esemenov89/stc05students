package main.services;

import main.model.dao.StudentDao;
import main.model.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aleksei Lysov on 19.04.2017.
 */
@Service
public class StudentServiceImpl implements StudentService{

//    @Autowired
//    public StudentServiceImpl(StudentDao studentDao) {
//        this.studentDao = studentDao;
//    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    @Autowired
    @Qualifier(value = "studentDao")
    public void setStudentDao(StudentDao studentDao) {
         this.studentDao = studentDao;
    }

    // @Autowired
    private StudentDao studentDao;

    public List<StudentEntity> getAllStudents(){
        return studentDao.findAll();
    }

    public void deleteStudent(int id){
        studentDao.delete(id);
    }

    @Override
    public void insert(StudentEntity student) {
        studentDao.insert(student);
    }

/*    @Override
    public void update(StudentEntity student) {
        studentDao.update(student);
    }*/

    @Override
    public StudentEntity findById(int id) {
        return studentDao.findById(id);
    }
}
