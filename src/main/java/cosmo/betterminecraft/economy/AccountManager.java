package cosmo.betterminecraft.economy;

import cosmo.betterminecraft.Core;

import java.text.DecimalFormat;

public class AccountManager {
    public String format(double amount) {
        amount = getMoneyRounded(amount);

        String suffix = " ";

        if (amount == 1.0D) {
            suffix = suffix + Core.getInstance().getCurrencyNameSingular();
        } else {
            suffix = suffix + Core.getInstance().getCurrencyNamePlural();
        }
        if (suffix.equalsIgnoreCase(" ")) {
            suffix = "";
        }

        return String.valueOf(amount) + suffix;

    }

    public double getMoneyRounded(double amount) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");

        String formattedAmount = twoDForm.format(amount);

        formattedAmount = formattedAmount.replace(",", ".");

        return Double.valueOf(formattedAmount).doubleValue();
    }
}
