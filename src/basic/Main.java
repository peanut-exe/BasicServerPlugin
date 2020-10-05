package basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;

public class Main extends JavaPlugin implements Listener {

    public HashMap<String, String> 칭호 = new HashMap<String, String>();

    @Override
    //플러그인 활성화 메세지
    public void onEnable() {
        System.out.println("[BasicServerPlugin] Activate/활성화!");
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new Event(), this);
        Bukkit.getPluginManager().registerEvents(new GuiEvent(), this);
    }

    @Override
    //플러그인 비활성화 메세지
    public void onDisable() {
        System.out.println("[BasicServerPlugin] Deactivate/비활성화!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(!(sender instanceof Player)) {
            System.out.println("[BasicServerPlugin] not support console!/콘솔은 지원되지 않습니다!");
            return true;
        }
        //플러그인 설명
        if (command.getName().equalsIgnoreCase("b")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Type the command to execute.");}
            else if (args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(ChatColor.YELLOW + "This Plugin is Made By bjw300 and peanutexe");}
            else {
                sender.sendMessage(ChatColor.RED + "Command is not correct.");}
            return true;
        }
        //도움말
        if (command.getName().equalsIgnoreCase("명령어")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "명령어 사용법: /명령어 [도움/도움말/모음]");}
            else if (args[0].equals("도움") || args[0].equals("도움말") || args[0].equals("모음")) {
                sender.sendMessage("메뉴열기,지급 명령어: /[메뉴/메뉴 지급]\n정보표시창 켜기/끄기: /정보표시창 [켜기/끄기]\n자신의 창고보기: /(창고/warehouse)\n법전보기: /법전");}
            else {
                sender.sendMessage(ChatColor.RED + "명령어 사용법: /명령어 [도움/도움말/모음]");}
            return true;
        }
        //법전
        if (command.getName().equalsIgnoreCase("법전")) {
            if (sender instanceof Player) {
                sender.sendMessage("-Ss 야생서버 법-\n1,아래 조항들은 크루 팀원들의 의견에 따라 바뀔수 있다.\n2,서로 존중해 주기.\n3,크리에이티브 사용 금지.\n4,위 조항은 팀원 모두의 허락을 받을시 가능.\n5,똥싸지 말기.\n6,흉물은 팀원들의 의견에 따라 과반수가 넘을시 철거 가능.\n7,명령어를 이용하여 아이템 및 버프 부여 금지.\n8,인첸크를 위한 청금석과 경험치는 명령어 이용 가능.\n9,위 조항들을 어길시 경우에 따라 재판 및 처벌 절차.\n10,재판에서 묵비권 행사 가능 및 변호사를 선임하든 말든 맘데로.");
                return false;
            }
        }
        //칭호(설정부분)
        if (command.getName().equalsIgnoreCase("칭호설정")) {
            if (args.length == 1) {
                String 칭호 = args[0];
                this.칭호.put(p.getName(), 칭호);
                p.sendMessage(ChatColor.YELLOW + "채팅 칭호가 설정되었습니다 [" + 칭호 + "]");
            }
            else {
                p.sendMessage(ChatColor.RED + "잘못된 구문입니다");
            }
        }
        //homes(개발중)
        if (command.getName().equalsIgnoreCase("home")) {
            String home = args[0];
            }
        //서버 메뉴(기본)
        if (command.getName().equalsIgnoreCase("메뉴")) {
            if (args.length == 0) {
                Inventory inv = Bukkit.createInventory(null, 27, "서버 메뉴");
                ItemStack item = new ItemStack(Material.GRASS_BLOCK);ItemMeta meta = item.getItemMeta();meta.setDisplayName("월드");meta.setLore(Arrays.asList("월드 목록"));item.setItemMeta(meta);
                ItemStack item2 = new ItemStack(Material.EMERALD);ItemMeta meta2 = item2.getItemMeta();meta2.setDisplayName("상점");meta2.setLore(Arrays.asList("상점을 실행합니다"));item2.setItemMeta(meta2);
                ItemStack item3 = new ItemStack(Material.CHEST);ItemMeta meta3 = item3.getItemMeta();meta3.setDisplayName("창고 보기");meta3.setLore(Arrays.asList("자신의 창고를", "실행합니다"));item3.setItemMeta(meta3);
                ItemStack item4 = new ItemStack(Material.SKELETON_SKULL);ItemMeta meta4 = item4.getItemMeta();meta4.setDisplayName("자살하기");meta4.setLore(Arrays.asList("자살합니다"));item4.setItemMeta(meta4);
                ItemStack item5 = new ItemStack(Material.WRITABLE_BOOK);ItemMeta meta5 = item5.getItemMeta();meta5.setDisplayName("서버 법 보기");meta5.setLore(Arrays.asList("법전을 실행합니다"));item5.setItemMeta(meta5);
                ItemStack item6 = new ItemStack(Material.BOOK);ItemMeta meta6 = item6.getItemMeta();meta6.setDisplayName("명령어 도움말 보기");meta6.setLore(Arrays.asList("명령어 도움말을", "실행합니다"));item6.setItemMeta(meta6);
                ItemStack item7 = new ItemStack(Material.PAPER);ItemMeta meta7 = item7.getItemMeta();meta7.setDisplayName("설정");meta7.setLore(Arrays.asList("설정을 실행합니다"));item7.setItemMeta(meta7);
                inv.setItem(10, new ItemStack(item));
                inv.setItem(11, new ItemStack(item2));
                inv.setItem(12, new ItemStack(item3));
                inv.setItem(13, new ItemStack(item4));
                inv.setItem(14, new ItemStack(item5));
                inv.setItem(15, new ItemStack(item6));
                inv.setItem(16, new ItemStack(item7));
                p.openInventory(inv);
            }
            //서버 메뉴(기본)(지급)
            else if (args[0].equals("지급")) {
                ItemStack item = new ItemStack(Material.CLOCK);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("서버 메뉴");
                meta.setLore(Arrays.asList("서버메뉴를 실행합니다"));
                item.setItemMeta(meta);
                p.getInventory().addItem(new ItemStack(item));}
            else {
                sender.sendMessage(ChatColor.RED + "명령어 사용법: 메뉴열기,지급 명령어: /[메뉴/메뉴 지급]");
            }
        }
        if (command.getName().equalsIgnoreCase("상점")) {
            Inventory shop = Bukkit.createInventory(null, 54, "상점");
            ItemStack shp = new ItemStack(Material.IRON_PICKAXE);ItemMeta meta = shp.getItemMeta();meta.setDisplayName("도구상점");meta.setLore(Arrays.asList("도구상점을 실행합니다"));shp.setItemMeta(meta);
            ItemStack shp1 = new ItemStack(Material.CARROT);ItemMeta meta1 = shp1.getItemMeta();meta1.setDisplayName("음식상점");meta1.setLore(Arrays.asList("음식상점을 실행합니다"));shp1.setItemMeta(meta1);
            ItemStack shp2 = new ItemStack(Material.DIAMOND_ORE);ItemMeta meta2 = shp2.getItemMeta();meta2.setDisplayName("도구상점");meta2.setLore(Arrays.asList("도구상점을 실행합니다"));shp2.setItemMeta(meta2);
            shop.setItem(10, new ItemStack(shp));
            shop.setItem(11, new ItemStack(shp1));
            shop.setItem(12, new ItemStack(shp2));
            p.openInventory(shop);
        }
        //관리자 메뉴
        if (command.getName().equalsIgnoreCase("관리자메뉴")) {
            if (p.isOp()) {
                if (args.length == 0) {
                    Inventory inv = Bukkit.createInventory(null, 36, "관리자 메뉴");
                    ItemStack item = new ItemStack(Material.BOOK);ItemMeta meta = item.getItemMeta();meta.setDisplayName("서바이벌 모드");meta.setLore(Arrays.asList("서바이벌 모드로 바꿔줍니다"));item.setItemMeta(meta);
                    ItemStack item2 = new ItemStack(Material.BOOK);ItemMeta meta2 = item2.getItemMeta();meta2.setDisplayName("크레이티브 모드");meta2.setLore(Arrays.asList("크레이티브 모드로 바꿔줍니다"));item2.setItemMeta(meta2);
                    ItemStack item3 = new ItemStack(Material.BOOK);ItemMeta meta3 = item3.getItemMeta();meta3.setDisplayName("op 받기");item3.setItemMeta(meta3);
                    ItemStack item4 = new ItemStack(Material.BOOK);ItemMeta meta4 = item4.getItemMeta();meta4.setDisplayName("op 없애기");item4.setItemMeta(meta4);
                    ItemStack item5 = new ItemStack(Material.BOOK);ItemMeta meta5 = item5.getItemMeta();meta5.setDisplayName("날씨 맑게 하기");meta5.setLore(Arrays.asList("비,눈이 올때 실행하세요"));item5.setItemMeta(meta5);
                    ItemStack item6 = new ItemStack(Material.BOOK);ItemMeta meta6 = item6.getItemMeta();meta6.setDisplayName("낮으로 바꾸기");meta6.setLore(Arrays.asList("해가 뜰 때로", "바꿔줍니다"));item6.setItemMeta(meta6);
                    ItemStack item7 = new ItemStack(Material.WITHER_SKELETON_SKULL);ItemMeta meta7 = item7.getItemMeta();meta7.setDisplayName("죽이기");meta7.setLore(Arrays.asList("플레이어 빼고 '다' 죽입니다"));item7.setItemMeta(meta7);
                    ItemStack item8 = new ItemStack(Material.BOOK);ItemMeta meta8 = item8.getItemMeta();meta8.setDisplayName("서버 리로드");meta8.setLore(Arrays.asList("서버를 다시 로딩합니다"));item8.setItemMeta(meta8);
                    ItemStack item9 = new ItemStack(Material.BOOK);ItemMeta meta9 = item9.getItemMeta();meta9.setDisplayName("스크립트 리로드");meta9.setLore(Arrays.asList("모든 스크립트를", "다시 로딩합니다"));item9.setItemMeta(meta9);
                    ItemStack item10 = new ItemStack(Material.BOOK);ItemMeta meta10 = item10.getItemMeta();meta10.setDisplayName("10");meta10.setLore(Arrays.asList("10"));item10.setItemMeta(meta10);
                    ItemStack item11 = new ItemStack(Material.BOOK);ItemMeta meta11 = item11.getItemMeta();meta11.setDisplayName("11");meta11.setLore(Arrays.asList("11"));item11.setItemMeta(meta11);
                    ItemStack item12 = new ItemStack(Material.BOOK);ItemMeta meta12 = item12.getItemMeta();meta12.setDisplayName("12");meta12.setLore(Arrays.asList("12"));item12.setItemMeta(meta12);
                    ItemStack item13 = new ItemStack(Material.BOOK);ItemMeta meta13 = item13.getItemMeta();meta13.setDisplayName("13");meta13.setLore(Arrays.asList("13"));item13.setItemMeta(meta13);
                    ItemStack item14 = new ItemStack(Material.BARRIER);ItemMeta meta14 = item14.getItemMeta();meta14.setDisplayName("서버중지");meta14.setLore(Arrays.asList("서버를", "중지합니다"));item14.setItemMeta(meta14);
                    inv.setItem(10, new ItemStack(item));
                    inv.setItem(11, new ItemStack(item2));
                    inv.setItem(12, new ItemStack(item3));
                    inv.setItem(13, new ItemStack(item4));
                    inv.setItem(14, new ItemStack(item5));
                    inv.setItem(15, new ItemStack(item6));
                    inv.setItem(16, new ItemStack(item7));
                    inv.setItem(19, new ItemStack(item8));
                    inv.setItem(20, new ItemStack(item9));
                    inv.setItem(21, new ItemStack(item10));
                    inv.setItem(22, new ItemStack(item11));
                    inv.setItem(23, new ItemStack(item12));
                    inv.setItem(24, new ItemStack(item13));
                    inv.setItem(25, new ItemStack(item14));
                    p.openInventory(inv);
                }
                //관리자 메뉴(지급)
                else if (args[0].equals("지급")) {
                    ItemStack item = new ItemStack(Material.NETHER_STAR);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName("관리자 메뉴");
                    meta.setLore(Arrays.asList("관리자 메뉴를 실행합니다"));
                    item.setItemMeta(meta);
                    p.getInventory().addItem(new ItemStack(item));}
            }
            else {
                p.sendMessage(ChatColor.RED + "당신은 op가 아니기 때문에 이 작업을 할 수 없음!");
            }
        }
        if (command.getName().equalsIgnoreCase("월드메뉴")) {
            if (args.length == 0) {
                Inventory inv = Bukkit.createInventory((InventoryHolder) null, 27, "월드 메뉴");ItemStack item = new ItemStack(Material.GRASS_BLOCK);ItemMeta meta = item.getItemMeta();meta.setDisplayName("월드");meta.setLore(Arrays.asList("월드(기본월드)로 이동합니다"));item.setItemMeta(meta);
                ItemStack item2 = new ItemStack(Material.GRASS_BLOCK);ItemMeta meta2 = item2.getItemMeta();meta2.setDisplayName("월드 2");meta2.setLore(Arrays.asList("월드 2로 이동합니다"));item2.setItemMeta(meta2);
                ItemStack item3 = new ItemStack(Material.GRASS_BLOCK);ItemMeta meta3 = item3.getItemMeta();meta3.setDisplayName("월드 3");meta3.setLore(Arrays.asList("월드 3로 이동합니다"));item3.setItemMeta(meta3);
                ItemStack item4 = new ItemStack(Material.GRASS_BLOCK);ItemMeta meta4 = item4.getItemMeta();meta4.setDisplayName("월드 4");meta4.setLore(Arrays.asList("월드 4로 이동합니다"));item4.setItemMeta(meta4);
                ItemStack item5 = new ItemStack(Material.RED_DYE);ItemMeta meta5 = item5.getItemMeta();meta5.setDisplayName(ChatColor.RED + "메뉴로 돌아가기");meta5.setLore(Arrays.asList(ChatColor.RED + "메뉴로 이동합니다"));item5.setItemMeta(meta5);
                inv.setItem(10, new ItemStack(item));
                inv.setItem(12, new ItemStack(item2));
                inv.setItem(14, new ItemStack(item3));
                inv.setItem(16, new ItemStack(item4));
                inv.setItem(26, new ItemStack(item5));
                p.openInventory(inv);
            }
        }
        //설정
        if (command.getName().equalsIgnoreCase("설정")) {
            if (args.length == 0) {
                Inventory inv = Bukkit.createInventory(null, 27, "개인 설정");
                ItemStack item = new ItemStack(Material.OAK_SIGN);ItemMeta meta = item.getItemMeta();meta.setDisplayName("정보표시창 [켜기/끄기]");meta.setLore(Arrays.asList("정보표시창을 켜거나 끕니다"));item.setItemMeta(meta);
                ItemStack item2 = new ItemStack(Material.EMERALD);ItemMeta meta2 = item2.getItemMeta();meta2.setDisplayName("칭호 관리");meta2.setLore(Arrays.asList("칭호목록을 실행합니다"));item2.setItemMeta(meta2);
                ItemStack item3 = new ItemStack(Material.CHEST);ItemMeta meta3 = item3.getItemMeta();meta3.setDisplayName("우편함 관리");meta3.setLore(Arrays.asList("우편함 목록을 실행합니다"));item3.setItemMeta(meta3);
                ItemStack item4 = new ItemStack(Material.BOOK);ItemMeta meta4 = item4.getItemMeta();meta4.setDisplayName("~~");meta4.setLore(Arrays.asList("~~"));item4.setItemMeta(meta4);
                ItemStack item5 = new ItemStack(Material.BOOK);ItemMeta meta5 = item5.getItemMeta();meta5.setDisplayName("~~");meta5.setLore(Arrays.asList("~~"));item5.setItemMeta(meta5);
                ItemStack item6 = new ItemStack(Material.BOOK);ItemMeta meta6 = item6.getItemMeta();meta6.setDisplayName("~~");meta6.setLore(Arrays.asList("~~"));item6.setItemMeta(meta6);
                ItemStack item7 = new ItemStack(Material.BOOK);ItemMeta meta7 = item7.getItemMeta();meta7.setDisplayName("~~");meta7.setLore(Arrays.asList("~~"));item7.setItemMeta(meta7);
                ItemStack item8 = new ItemStack(Material.RED_DYE);ItemMeta meta8 = item8.getItemMeta();meta8.setDisplayName(ChatColor.RED + "메뉴로 돌아가기");meta8.setLore(Arrays.asList(ChatColor.RED + "메뉴로 이동합니다"));item8.setItemMeta(meta8);
                inv.setItem(10, new ItemStack(item));
                inv.setItem(11, new ItemStack(item2));
                inv.setItem(12, new ItemStack(item3));
                inv.setItem(13, new ItemStack(item4));
                inv.setItem(14, new ItemStack(item5));
                inv.setItem(15, new ItemStack(item6));
                inv.setItem(16, new ItemStack(item7));
                inv.setItem(26, new ItemStack(item8));
                p.openInventory(inv);
            }
        }
        return false;
    }
    //칭호(메시지포멧)
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        String format = e.getFormat();
        String 칭호 = this.칭호.get(e.getPlayer().getName());
        if(칭호 != null)
            e.setFormat(칭호 + ChatColor.RESET + " " + format);
    }
}