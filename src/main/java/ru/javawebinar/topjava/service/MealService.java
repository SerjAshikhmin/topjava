package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private static final Logger log = LoggerFactory.getLogger(MealService.class);

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int userId) {
        log.debug("get meal {} for user {}", id, userId);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        log.debug("delete meal {} for user {}", id, userId);
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Meal> getBetweenDates(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        log.debug("getBetweenDates dates({} - {}) for user {}", startDate, endDate, userId);
        return repository.getBetween(
                DateTimeUtil.createDateTime(startDate, LocalDate.MIN, LocalTime.MIN),
                DateTimeUtil.createDateTime(endDate, LocalDate.MAX, LocalTime.MAX),
                userId);
    }

    public List<Meal> getAll(int userId) {
        log.debug("getAll for user {}", userId);
        return repository.getAll(userId);
    }

    public void update(Meal meal, int userId) {
        log.debug("update {} for user {}", meal, userId);
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public Meal create(Meal meal, int userId) {
        log.debug("create {} for user {}", meal, userId);
        return repository.save(meal, userId);
    }
}