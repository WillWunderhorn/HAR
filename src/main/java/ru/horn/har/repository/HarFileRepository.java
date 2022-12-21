package ru.horn.har.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.horn.har.model.HarFile;

public interface HarFileRepository extends JpaRepository<HarFile, Long> {
}
