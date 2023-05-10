package ru.sberbank.jd.lesson01;

import java.util.Collection;

/**
 * Класс является реализацией интерфейса {@link Greeting} и представляет собой набор методов для представления человека.
 */

public class GreetingImpl implements Greeting {

    private final String firstName;
    private final String lastName;
    private final int birthYear;
    private final Collection<String> hobbies;
    private final String bitbucketUrl;
    private final String phone;
    private final Collection<String> courseExpectations;

    /**
     * Конструктор класса ожидающий все аргументы для инициализации объекта.
     */
    public GreetingImpl(String firstName, String lastName, int birthYear, Collection<String> hobbies,
            String bitbucketUrl, String phone,
            Collection<String> courseExpectations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.hobbies = hobbies;
        this.bitbucketUrl = bitbucketUrl;
        this.phone = phone;
        this.courseExpectations = courseExpectations;
    }


    /**
     * Возвращает имя представляющегося.
     *
     * @return  {@link String}.
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Возвращает фамилию представляющегося.
     *
     * @return  {@link String}
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Возвращает год рождения представляющегося.
     *
     * @return int
     */
    @Override
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Возвращает Коллекцию с репрезентацией хобби представляющегося.
     *
     * @return {@link Collection}
     */
    @Override
    public Collection<String> getHobbies() {
        return hobbies;
    }

    /**
     * Возвращает ссылку на репозиторий представляющегося.
     *
     * @return {@link String}
     */
    @Override
    public String getBitbucketUrl() {
        return bitbucketUrl;
    }

    /**
     * Возвращает номер телефона представляющегося.
     *
     * @return {@link String}
     */
    @Override
    public String getPhone() {
        return phone;
    }

    /**
     * Возвращает коллекцию с репрезентацией ожиданий представляющегося от курса.
     *
     * @return  {@link Collection} of  {@link String}
     */
    @Override
    public Collection<String> getCourseExpectations() {
        return courseExpectations;
    }
}
