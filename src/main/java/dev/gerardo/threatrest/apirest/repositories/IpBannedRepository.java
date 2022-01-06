package dev.gerardo.threatrest.apirest.repositories;

import dev.gerardo.threatrest.apirest.models.entities.IpBanned;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IpBannedRepository extends CrudRepository<IpBanned, Long> {

    Optional<IpBanned> findIpBannedByIp(String ip);

}
