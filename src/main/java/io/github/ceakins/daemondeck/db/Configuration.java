package io.github.ceakins.daemondeck.db;

import org.dizitart.no2.collection.NitriteId;
import org.dizitart.no2.repository.annotations.Entity;
import org.dizitart.no2.repository.annotations.Id;

import java.util.Set;

@Entity
public class Configuration {

    @Id
    private NitriteId id;
    private String adminUsername;
    private String adminPasswordHash;
    private String steamCmdPath;
    private Set<String> allowedIps;

    public Configuration() {
    }

    public NitriteId getId() {
        return id;
    }

    public void setId(NitriteId id) {
        this.id = id;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPasswordHash() {
        return adminPasswordHash;
    }

    public void setAdminPasswordHash(String adminPasswordHash) {
        this.adminPasswordHash = adminPasswordHash;
    }

    public String getSteamCmdPath() {
        return steamCmdPath;
    }

    public void setSteamCmdPath(String steamCmdPath) {
        this.steamCmdPath = steamCmdPath;
    }

    public Set<String> getAllowedIps() {
        return allowedIps;
    }

    public void setAllowedIps(Set<String> allowedIps) {
        this.allowedIps = allowedIps;
    }
}
