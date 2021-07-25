package cosmo.betterminecraft.database;

import cosmo.Cosmotil.database.MariaDB;
import cosmo.betterminecraft.Core;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Everything related to bank/purse db
 * is handled in this class
 *
 * @author Vento
 * @since 0.1.0
 */
public class BankDb {
    private final MariaDB db;
    private final Core core;

    public BankDb(@NotNull MariaDB mariaDB) {
        core = Core.getInstance();
        db = mariaDB;
    }

    /**
     * Initialize the DB's tables
     *
     * @author Vento
     * @since 0.1.0
     */
    public void setupTables() {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS bank (UUID VARCHAR(100),purse INT(100), balance INT(100),PRIMARY KEY (UUID))");
            ps.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Add the given amount of coins to
     * the players bank account
     *
     * @param amount The amount of money to add
     * @param uuid   The players UUID
     * @author Vento
     * @since 0.1.0
     */
    public void addCoinsToBank(UUID uuid, int amount) {
        try {
            int currentBalance = getPlayerBankBalance(uuid);
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(
                    "UPDATE bank SET balance=? WHERE UUID=?"
            );
            preparedStatement.setInt(1, currentBalance + amount);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get a players bank balance
     * See {@link cosmo.betterminecraft.database.BankDb#getPlayerPurse(UUID)}
     * to get a players purse
     *
     * @author Vento
     * @since 0.1.0
     */
    public int getPlayerBankBalance(UUID uuid) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "SELECT * FROM bank WHERE UUID=?"
            );
            ps.setString(1, uuid.toString());
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return res.getInt("balance");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    /**
     * Get a players purse
     *
     * @param uuid The Players UUID
     * @return The Players purse
     * @author Vento
     * @since 0.1.0
     */
    public int getPlayerPurse(UUID uuid) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "SELECT * FROM bank WHERE UUID=?"
            );
            ps.setString(1, uuid.toString());
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return res.getInt("purse");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * Add player to db
     *
     * @author Vento
     * @since 0.1.0
     */
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
