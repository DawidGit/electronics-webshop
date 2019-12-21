package pl.connectis.electronicswebshop.web.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeUserRepository implements UserRepository {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeUserRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByLogin(String username) {
        return getApplicationUsers().stream().filter(user -> username.equals(user.getLogin())).findFirst().get();
    }

    private List<User> getApplicationUsers() {
                User user1 = new User(
                        passwordEncoder.encode("password"),
                        "user",
                        new ArrayList<>(),
                        "test@test.com",
                        true,
                        true,
                        true,
                        true
                );
        ArrayList<User> list = new ArrayList<>();
        list.add(user1);
        return list;
    }

    @Override
    public <S extends User> S save(S s) {
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
