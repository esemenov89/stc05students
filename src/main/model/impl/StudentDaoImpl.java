package main.model.impl;

import main.model.dao.StudentDao;
import main.model.dao.mappers.StudentMapper;
import main.model.entity.StudentEntity;
import main.services.DataSourceFactory;
import main.services.HibernateSessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.List;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {

    private static final Logger LOG = Logger.getLogger(StudentDaoImpl.class);
    private DataSource dataSource = DataSourceFactory.getDataSource();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StudentMapper studentMapper;


    public List<StudentEntity> findAll() {

        return studentMapper.getAllStudents();
/*        ArrayList<StudentEntity> students = new ArrayList<>();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<StudentEntity> list = session.createCriteria(StudentEntity.class).list();

        students=(ArrayList)list;

        session.close();
        return students;*/
/*        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            StudentMapper mapper =factory.openSession().getMapper(StudentMapper.class);
            return mapper.getAllStudents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    public StudentEntity findById(int id) {

        return studentMapper.getStudentById(id);
/*        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            StudentMapper mapper =factory.openSession().getMapper(StudentMapper.class);
            return mapper.getStudentById();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //return null;


/*        StudentEntity student = null;

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from StudentEntity where id = :id");
        query.setParameter("id", id);
        List list = query.list();

        if(list.size()>0)
            student = (StudentEntity)list.get(0);

        session.close();
        return student;*/
    }

    public int insert(StudentEntity student) {
        int lastId = 0;

        if (student == null)
            return lastId;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student (name, age, group_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setLong(3, student.getGroupId());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) lastId = rs.getInt(1);
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public void delete(int id) {
        studentMapper.deleteStudentById(id);
    }

/*    public int update(Student student) {
        int result = 0;

        if (student == null)
            return result;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET name = ?, age = ?, group_id = ? WHERE id = ?");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setLong(3, student.getGroupId());
            preparedStatement.setLong(4, student.getId());

            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }*/
}
