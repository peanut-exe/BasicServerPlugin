package basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Event implements Listener {

    @EventHandler
    //접속메세지
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + "님이 서버에 들어왔다!");
    }

    @EventHandler
    //퇴장메세지
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + "님이 서버에서 나갔다!");}

    //폭팔방지
    @EventHandler
    public void onExplode(ExplosionPrimeEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType().equals(Material.TNT)) {
            Player p = e.getPlayer();
            e.setCancelled(true);
            Bukkit.broadcastMessage(ChatColor.RED + "tnt를 '왜' 설치하나 " + p.getName() + "?");
        }
    }
    //천둥
    @EventHandler
    public void PlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = p.getTargetBlock(null, 80);
        if (p.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(p.getGameMode() == GameMode.CREATIVE) {
                    p.getWorld().strikeLightning(b.getLocation());
                }
                //개발중
                else if(p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {
                    p.getWorld().strikeLightning(b.getLocation());
                }
            }
        }
    }
}