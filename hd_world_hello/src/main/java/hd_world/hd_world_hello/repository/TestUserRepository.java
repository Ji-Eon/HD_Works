package hd_world.hd_world_hello.repository;

import hd_world.hd_world_hello.domain.TestUser;

import java.util.Optional;

public interface TestUserRepository {
    TestUser save(TestUser testUser);
    Optional<TestUser> getId(Long id);
}
