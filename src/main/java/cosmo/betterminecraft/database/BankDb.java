package cosmo.betterminecraft.database;

import cosmo.Cosmotil.database.MariaDB;
import cosmo.betterminecraft.Core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BankDb {
    private MariaDB db;
    private Core core;

    public BankDb(MariaDB mariaDB) {
        core = Core.getInstance();
        db = mariaDB;
    }

    public void setupTables() {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS bank (UUID VARCHAR(100),balance INT(100),PRIMARY KEY (UUID))");
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean checkPlayerHasAccount(UUID uuid) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM bank WHERE uuid = ?");
            ps.setString(1, uuid.toString());
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
