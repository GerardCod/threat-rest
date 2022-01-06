package dev.gerardo.threatrest.apirest.dao;

import dev.gerardo.threatrest.apirest.exceptions.IpAlreadyBannedException;
import dev.gerardo.threatrest.apirest.models.entities.IpBanned;
import dev.gerardo.threatrest.apirest.repositories.IpBannedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IpBannedDAOImpl implements IpBannedDAO {

    @Autowired
    private IpBannedRepository repository;

    @Override
    public IpBanned addIpBanned(IpBanned ipBanned) {
        return repository.save(ipBanned);
    }

    @Override
    public void checkIfIpIsBanned(String ipAddress) {
        Optional<IpBanned> result = repository.findIpBannedByIp(ipAddress);

        if (result.isPresent()) {
            throw new IpAlreadyBannedException("the ip " + ipAddress + " is already banned, please enter another one.");
        }

    }
}
