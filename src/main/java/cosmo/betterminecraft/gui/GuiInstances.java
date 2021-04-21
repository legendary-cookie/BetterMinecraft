package cosmo.betterminecraft.gui;

public class GuiInstances {
    public CustomItemGui cig;
    public AdminMoneyGui moneyGui;

    public GuiInstances() {
        this.cig = new CustomItemGui();
        this.moneyGui = new AdminMoneyGui();
    }

    public AdminMoneyGui getMoneyGui() {
        return moneyGui;
    }

    public CustomItemGui getCig() {
        return cig;
    }
}
