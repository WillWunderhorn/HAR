package ru.horn.har.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.horn.har.model.HarFile;

@Repository
public interface HarFileRepository extends JpaRepository<HarFile, Long> {
}
