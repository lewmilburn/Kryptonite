package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * The main menu GUI for KOS.
 */
public class KosMainGui {
    private final CommandSender commandSender;
    private final Kryptonite plugin;
    private InventoryGui gui;

    /**
     * Constructor for the KosMainGui class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param commandSender CommandSender - the player who sent the command.
     */
    public KosMainGui(Kryptonite plugin, CommandSender commandSender) {
        this.plugin = plugin;
        this.commandSender = commandSender;
    }

    /**
     * Displays the GUI to the user.
     */
    public void show() {
        InventoryHolder holder = this.commandSender.getServer().getPlayer(this.commandSender.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Select a Profile", this.getElements());

        this.gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.commandSender);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.gui.addElement(new StaticGuiElement('y',
                new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KosPregeneratedGui kpg = new KosPregeneratedGui(this.plugin, this.commandSender, "YouHaveTrouble");
                    kpg.show();
                    return true;
                },
                "YouHaveTrouble"
        ));

        this.gui.addElement(new StaticGuiElement('h',
                new ItemStack(Material.PAPER),
                1,
                click -> {
                    click.getWhoClicked().sendMessage(ChatColor.YELLOW+"https://wiki.lewmc.net/kr-kryptonite-optimisation-system.html");
                    click.getGui().close();
                    return true;
                },
                "Need help?",
                "Click here to get a link to our Wiki."
        ));
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {
        return new String[]{
                " y       ",
                "         ",
                "    h    "
        };
    }
}
