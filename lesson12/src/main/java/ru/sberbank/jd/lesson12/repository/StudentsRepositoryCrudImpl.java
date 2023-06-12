package ru.sberbank.jd.lesson12.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.sberbank.jd.lesson12.model.Student;

/**
 * Класс реализация интерфейса {@link StudentsRepositoryCrud}.
 */
@RequiredArgsConstructor
public class StudentsRepositoryCrudImpl implements StudentsRepositoryCrud {

    public static String CONNECTION = "jdbc:postgresql://localhost:5435/db?user=user&password=123";
    private final Connection connection;

    /**
     * Создание записи в БД. id у student должен быть null, иначе требуется вызов update. id генерируем через
     * UUID.randomUUID()
     *
     * @param student - заполненный объект
     * @return сгенерированный UUID
     */
    @SneakyThrows
    @Override
    public UUID create(Student student) {
        if (student.getId() != null) {
            int updated = update(student);
            System.out.println("Количество обновленных записей: " + updated);
            return student.getId();
        }

        String sql = "insert into students (id,first_name,last_name,birth_date,is_graduated) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Date sqlDate = Date.valueOf(student.getBirthDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        UUID uuid = UUID.randomUUID();

        preparedStatement.setString(1, String.valueOf(uuid.toString()));
        preparedStatement.setString(2, student.getFirstName());
        preparedStatement.setString(3, student.getLastName());
        preparedStatement.setDate(4, sqlDate);
        preparedStatement.setBoolean(5, student.isGraduated());
        int rowInserted = preparedStatement.executeUpdate();
        if (rowInserted > 0) {
            student.setId(uuid);
            System.out.println("student=" + student + " добавлен в базу данных");
        }
        return uuid;
    }

    /**
     * Получение записи по id из БД.
     *
     * @param id идентификатор записи
     * @return запись
     */
    @SneakyThrows
    @Override
    public Student selectById(UUID id) {
        String sql = "select * from students where id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new Student(UUID.fromString(resultSet.getString("id")),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getDate("birth_date"),
                resultSet.getBoolean("is_graduated"));
    }

    /**
     * Получение всех записей из БД.
     *
     * @return записи
     */
    @SneakyThrows
    @Override
    public List<Student> selectAll() {
        String sql = "select * from students";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student(UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getDate("birth_date"),
                    resultSet.getBoolean("is_graduated"));
            students.add(student);
        }
        return students;
    }

    /**
     * Обновление записи в БД.
     *
     * @param student измененная запись
     * @return количество обновленных записей
     */
    @SneakyThrows
    @Override
    public int update(Student student) {
        String sql = " update students set first_name=?,last_name=?,birth_date=?,is_graduated=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Date sqlDate = Date.valueOf(student.getBirthDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());
        preparedStatement.setDate(3, sqlDate);
        preparedStatement.setBoolean(4, student.isGraduated());
        preparedStatement.setString(5, String.valueOf(student.getId().toString()));
        System.out.println("Пользователь с id" + student.getId() + " обновлен.");
        return preparedStatement.executeUpdate();
    }

    /**
     * Удаление указанных записей по id.
     *
     * @param idList список идентификаторов записей
     * @return количество удаленных записей
     */
    @Override
    @SneakyThrows
    public int remove(List<UUID> idList) {
        String sql = "delete from students where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        AtomicInteger removeCount = new AtomicInteger(0);
        idList.forEach(uuid -> {
            try {
                preparedStatement.setString(1, uuid.toString());
                preparedStatement.executeUpdate();
                removeCount.set(removeCount.get() + 1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return removeCount.get();
    }
}
