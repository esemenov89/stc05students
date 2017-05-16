package main.model.impl;

import main.model.dao.StudentDao;
import main.model.entity.StudentEntity;
import main.model.entity.UsersEntity;
import main.services.DataSourceFactory;
import main.services.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {

    private static final Logger LOG = Logger.getLogger(StudentDaoImpl.class);
    private DataSource dataSource = DataSourceFactory.getDataSource();

    public List<StudentEntity> findAll() {
        ArrayList<StudentEntity> students = new ArrayList<>();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<StudentEntity> list = session.createCriteria(StudentEntity.class).list();

        students=(ArrayList)list;

        session.close();
        return students;
    }

    public StudentEntity findById(int id) {
        StudentEntity student = null;

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from StudentEntity where id = :id");
        query.setParameter("id", id);
        List list = query.list();

        if(list.size()>0)
            student = (StudentEntity)list.get(0);

        session.close();
        return student;
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
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        StudentEntity student = (StudentEntity) session.get(StudentEntity.class, id);
        session.delete(student);
        transaction.commit();
        session.close();
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
