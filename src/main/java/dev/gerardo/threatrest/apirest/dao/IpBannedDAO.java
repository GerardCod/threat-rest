package dev.gerardo.threatrest.apirest.dao;

import dev.gerardo.threatrest.apirest.models.entities.IpBanned;

import java.util.List;

public interface IpBannedDAO {
    IpBanned addIpBanned(IpBanned ipBanned);
    void checkIfIpIsBanned(String ipAddress);
}
