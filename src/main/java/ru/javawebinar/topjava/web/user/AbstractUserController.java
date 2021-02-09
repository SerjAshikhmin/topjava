package ru.javawebinar.topjava.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.debug("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.debug("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.debug("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.debug("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.debug("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public User getByMail(String email) {
        log.debug("getByEmail {}", email);
        return service.getByEmail(email);
    }
}