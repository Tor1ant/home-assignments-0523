package ru.sberbank.jd.lesson12.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.sberbank.jd.lesson12.model.Student;

/**
 * Тестовый класс для проверки функциональности класса StudentsRepositoryCrudImpl.
 */
class StudentsRepositoryCrudImplTest {

    private static final PostgreSQLContainer<?> sqlContainer = new PostgreSQLContainer<>(
            "postgres:13.9-alpine");
    private static Connection connection;

    /**
     * Выполняется перед всеми тестами.
     *
     * @throws SQLException       если возникает ошибка при работе с базой данных
     * @throws LiquibaseException если возникает ошибка при выполнении Liquibase миграций
     */
    @BeforeAll
    static void setUp() throws SQLException, LiquibaseException {
        sqlContainer.start();
        String jdbcUrl = sqlContainer.getJdbcUrl();
        String userName = sqlContainer.getUsername();
        String password = sqlContainer.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, userName, password);
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("db/changelog.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update();
    }

    /**
     * Проверка подключения к тестовому контейнеру базы данных.
     *
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @DisplayName("Проверка подключения в тестовому контейнеру")
    @Test
    void connectionTest() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * from students");
        resultSet.next();
        assertEquals(0, resultSet.getStatement().getMaxRows());
    }

    /**
     * Проверка создания студента в базе данных.
     */
    @DisplayName("Проверка тестирования создания студента в БД")
    @Test
    void createTest() {
        Student student = new Student(null,
                "Новый студент1",
                "Тестовая фамилия1",
                Date.from(Instant.now()),
                true);

        StudentsRepositoryCrud studentsRepositoryCrud = new StudentsRepositoryCrudImpl(connection);
        UUID uuid = studentsRepositoryCrud.create(student);
        assertNotNull(uuid);
        studentsRepositoryCrud.remove(List.of(uuid));
    }

    /**
     * Проверка создания студента в базе данных с существующим идентификатором.
     */
    @SneakyThrows
    @DisplayName("Проверка тестирования создания студента в БД с существующим id")
    @Test
    void createWithExistingIdTest() {
        Student student = new Student(null,
                "Новый студент1",
                "Тестовая фамилия1",
                Date.from(Instant.now()),
                true);

        StudentsRepositoryCrud studentsRepositoryCrud = new StudentsRepositoryCrudImpl(connection);
        UUID uuid = studentsRepositoryCrud.create(student);
        Student studentForUpdate = new Student("Обновленный студент", "Новая Фамилия",
                false);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateBeforeDb = formatter.format(student.getBirthDate());
        studentForUpdate.setBirthDate(formatter.parse(formattedDateBeforeDb));
        studentForUpdate.setId(uuid);
        UUID id = studentsRepositoryCrud.create(studentForUpdate);
        Student selectedById = studentsRepositoryCrud.selectById(uuid);
        assertEquals(uuid, id);
        assertEquals(studentForUpdate, selectedById);
        assertNotNull(uuid);
        studentsRepositoryCrud.remove(List.of(uuid));
    }

    /**
     * Проверка получения студента из базы данных по идентификатору.
     */
    @DisplayName("Проверка получения пользователя из базы данных")
    @Test
    void selectByIdTest() {
        Student student = new Student(null,
                "Новый студент1",
                "Тестовая фамилия1",
                Date.from(Instant.now()),
                true);

        StudentsRepositoryCrud studentsRepositoryCrud = new StudentsRepositoryCrudImpl(connection);
        UUID uuid = studentsRepositoryCrud.create(student);
        Student selectedStudent = studentsRepositoryCrud.selectById(uuid);
        student.setId(uuid);
        assertAll("все поля должны совпадать с изначальным student",
                () -> assertEquals(student.getId(), selectedStudent.getId()),
                () -> assertEquals(student.getFirstName(), selectedStudent.getFirstName()),
                () -> assertEquals(student.getLastName(), selectedStudent.getLastName()));
        Date birthDate = student.getBirthDate();
        Date birtDateFromDb = selectedStudent.getBirthDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateBeforeDb = formatter.format(birthDate);
        assertEquals(formattedDateBeforeDb, birtDateFromDb.toString());
        studentsRepositoryCrud.remove(List.of(uuid));
    }

    /**
     * Тестирование получения всех студентов из базы данных.
     */
    @SneakyThrows
    @DisplayName("Тестирование получения всех пользователей из базы данных")
    @Test
    void selectAllTest() {
        Student student = new Student(null,
                "Новый студент1",
                "Тестовая фамилия1",
                Date.from(Instant.now()),
                true);
        Student student2 = new Student(null,
                "Новый студент2",
                "Тестовая фамилия2",
                Date.from(Instant.now()),
                true);

        StudentsRepositoryCrud studentsRepositoryCrud = new StudentsRepositoryCrudImpl(connection);
        UUID uuid = studentsRepositoryCrud.create(student);
        UUID uuid2 = studentsRepositoryCrud.create(student2);
        student.setId(uuid);
        student2.setId(uuid2);
        List<Student> students = studentsRepositoryCrud.selectAll();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateBeforeDb = formatter.format(student.getBirthDate());
        student.setBirthDate(formatter.parse(formattedDateBeforeDb));
        student2.setBirthDate(formatter.parse(formattedDateBeforeDb));
        students.forEach(
                studentFromDb -> {
                    try {
                        studentFromDb.setBirthDate(formatter.parse(studentFromDb.getBirthDate().toString()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });
        List<Student> studentList = new ArrayList<>(2);
        studentList.add(student);
        studentList.add(student2);
        assertEquals(studentList, students);
        studentsRepositoryCrud.remove(List.of(uuid, uuid2));
    }

    /**
     * Проверка обновления информации о студенте.
     *
     * @throws ParseException если возникает ошибка при парсинге даты
     */
    @DisplayName("проверка обновления пользователя")
    @Test
    void updateTest() throws ParseException {
        Student student = new Student(null,
                "Новый студент1",
                "Тестовая фамилия1",
                Date.from(Instant.now()),
                true);

        StudentsRepositoryCrud studentsRepositoryCrud = new StudentsRepositoryCrudImpl(connection);
        UUID uuid = studentsRepositoryCrud.create(student);
        Student studentForUpdate = new Student("Обновленный студент", "Новая Фамилия",
                false);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateBeforeDb = formatter.format(student.getBirthDate());
        studentForUpdate.setBirthDate(formatter.parse(formattedDateBeforeDb));
        studentForUpdate.setId(uuid);
        int i = studentsRepositoryCrud.update(studentForUpdate);
        Student selectedById = studentsRepositoryCrud.selectById(uuid);
        assertEquals(1, i);
        assertEquals(studentForUpdate, selectedById);
        studentsRepositoryCrud.remove(List.of(uuid));
    }

    /**
     * Проверка удаления студента из базы данных.
     */
    @DisplayName("проверка удаления пользователя")
    @Test
    void removeTest() {
        Student student = new Student(null,
                "Новый студент1",
                "Тестовая фамилия1",
                Date.from(Instant.now()),
                true);

        StudentsRepositoryCrud studentsRepositoryCrud = new StudentsRepositoryCrudImpl(connection);
        UUID uuid = studentsRepositoryCrud.create(student);
        int removed = studentsRepositoryCrud.remove(List.of(uuid));
        List<Student> selectedAll = studentsRepositoryCrud.selectAll();
        assertEquals(1, removed);
        assertTrue(selectedAll.isEmpty());
    }
}