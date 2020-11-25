package basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class GuiEvent implements Listener {

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        //서버 메뉴(기본)(아이템이벤트)
        if (p.getItemInHand().getType().equals(Material.CLOCK)) {
            if (p.getItemInHand().getItemMeta().getDisplayName().equals("서버 메뉴")) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    p.performCommand("메뉴");
                }
            }
        }
        //관리자 메뉴(아이템이벤트)
        if (p.getItemInHand().getType().equals(Material.NETHER_STAR)) {
            if (p.getItemInHand().getItemMeta().getDisplayName().equals("관리자 메뉴")) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (p.isOp()) {
                        p.performCommand("관리자메뉴");
                    }
                    else {p.sendMessage(ChatColor.RED + "당신은 op가 아니기 때문에 이 작업을 할 수 없음!");}
                }
            }
        }
    }
    @EventHandler
    public void InventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        //서버 메뉴(기본)
        if(e.getView().getTitle().equals("서버 메뉴")) {
            e.setCancelled(true);
            p.playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, 1.0f, 1.0f);
            switch (e.getSlot()) {
                case 10:
                    p.performCommand("월드메뉴");
                    break;
                case 11:
                    p.performCommand("상점");
                    break;
                case 13:
                    p.performCommand("minecraft:kill @s");
                    break;
                case 15:
                    p.performCommand("명령어 도움말");
                    break;
                case 16:
                    p.performCommand("설정");
                    break;
            }
        }
        if (e.getView().getTitle().equals("상점")) {
            e.setCancelled(true);
            switch (e.getSlot()) {
                case 10:
                    p.closeInventory();
                    Inventory tshop = Bukkit.createInventory(null, 54, "도구상점");
                    p.openInventory(tshop);
                    p.playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    break;
            }
        }
        //관리자 메뉴
        if(e.getView().getTitle().equals("관리자 메뉴")) {
            e.setCancelled(true);
            p.playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, 1.0f, 1.0f);
            switch (e.getSlot()) {
                case 10:
                    p.performCommand("minecraft:gamemode survival");
                    break;
                case 11:
                    p.performCommand("minecraft:gamemode creative");
                    break;
                case 12:
                    p.performCommand("op @p");
                    break;
                case 13:
                    p.performCommand("deop @p");
                    break;
                case 14:
                    p.performCommand("minecraft:weather clear");
                    break;
                case 15:
                    p.performCommand("minecraft:time set 0");
                    break;
                case 16:
                    p.performCommand("minecraft:kill @e[type=!player]");
                    break;
                case 19:
                    p.performCommand("reload");
                    break;
                case 20:
                    p.performCommand("b reload");
                    break;
                case 25:
                    p.performCommand("stop");
                    break;
            }
        }
        if (e.getView().getTitle().equals("월드 메뉴")) {
            e.setCancelled(true);
            p.playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, 1.0F, 1.0F);
            switch(e.getSlot()) {
                case 10:
                    p.performCommand("mv tp world");
                    break;
                case 12:
                    p.performCommand("mv tp world2");
                    break;
                case 14:
                    p.performCommand("mv tp world3");
                    break;
                case 16:
                    p.performCommand("mv tp world4");
                    break;
                case 26:
                    p.performCommand("메뉴");
                    break;
            }
        }
        if(e.getView().getTitle().equals("개인 설정")) {
            e.setCancelled(true);
            p.playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, 1.0f, 1.0f);
            switch (e.getSlot()) {
                case 10:
                    p.performCommand("//정보표시창");
                    break;
                case 11:
                    p.performCommand("//칭호");
                    break;
                case 12:
                    p.performCommand("//우편");
                    break;
                case 26:
                    p.performCommand("메뉴");
                    break;
            }
        }
    }
}