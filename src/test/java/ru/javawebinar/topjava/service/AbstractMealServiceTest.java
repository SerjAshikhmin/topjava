package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

public abstract class AbstractMealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Test
    public void delete() throws Exception {
        service.delete(MEAL1_ID, USER_ID);
        thrown.expect(NotFoundException.class);
        service.get(MEAL1_ID, USER_ID);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(1, USER_ID);
    }

    @Test
    public void deleteNotOwn() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = getNew();

        Meal created = service.create(newMeal, USER_ID);
        created.setUser(null);
        Integer newId = created.getId();
        newMeal.setId(newId);
        Meal actual = service.get(newId, USER_ID);
        actual.setUser(null);

        assertMatch(created, newMeal);
        assertMatch(actual, newMeal);
    }

    @Test
    public void get() throws Exception {
        Meal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);

        assertMatch(actual, ADMIN_MEAL1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(1, ADMIN_ID);
    }

    @Test
    public void getNotOwn() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Meal updated = getUpdated();

        service.update(updated, USER_ID);
        Meal meal = service.get(MEAL1_ID, USER_ID);

        assertMatch(meal, updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + MEAL1_ID);
        service.update(MEAL1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> meals = service.getAll(USER_ID);

        assertMatch(meals, MEALS);
    }

    @Test
    public void getBetween() throws Exception {
        List<Meal> meals = service.getBetweenDates(
                LocalDate.of(2020, Month.MAY, 30),
                LocalDate.of(2020, Month.MAY, 30), USER_ID);

        assertMatch(meals, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void getBetweenWithNullDates() throws Exception {
        assertMatch(service.getBetweenDates(null, null, USER_ID), MEALS);
    }
}