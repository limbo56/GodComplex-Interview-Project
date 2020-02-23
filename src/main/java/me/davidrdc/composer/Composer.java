package me.davidrdc.composer;

import me.davidrdc.composer.menu.ShareMenu;
import me.davidrdc.composer.song.SongManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Composer extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MenuClickListener(), this);
        getCommand("songs").setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player))
                return false;
            new ShareMenu((Player) sender).open();
            return true;
        });
    }

    @Override
    public void onDisable() {
        SongManager.getInstance().clearSongs();
    }
}
