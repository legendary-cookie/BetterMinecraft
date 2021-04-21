package cosmo.betterminecraft.database;

import cosmo.Cosmotil.database.MariaDB;
import cosmo.betterminecraft.Core;
import org.bukkit.Bukkit;

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
            PreparedStatement ps = db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS bank (UUID VARCHAR(100),purse INT(100), balance INT(100),PRIMARY KEY (UUID))");
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addCoinsToBank(UUID uuid, int amount) {
        try {
            int curbal = getPlayerBankBalance(uuid);
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(
                    "UPDATE bank SET balance=? WHERE UUID=?"
            );
            preparedStatement.setInt(1, curbal + amount);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public int getPlayerBankBalance(UUID uuid) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "SELECT * FROM bank WHERE UUID=?"
            );
            ps.setString(1, uuid.toString());
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                return res.getInt("balance");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    public int getPlayerPurse(UUID uuid) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "SELECT * FROM bank WHERE UUID=?"
            );
            ps.setString(1, uuid.toString());
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                return res.getInt("purse");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    public void createPlayerAccount(UUID uuid) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT IGNORE INTO bank (UUID, purse, balance) VALUES (?,?,?)");
            ps.setString(1, uuid.toString());
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
