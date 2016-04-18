package com.fabbe50.compressedblocks.handler;

import com.fabbe50.compressedblocks.init.ModBlocks;
import com.fabbe50.compressedblocks.init.ModItems;
import com.fabbe50.compressedblocks.reference.Reference;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

/**
 * Created by fabbe50 on 07/03/2016.
 */
public class AchievementHandler {
    public static AchievementPage page1;

    //Achievements
    public static Achievement compressedStarblock = (new Achievement("achievement.star", "star", 0, 0, ModBlocks.comprstarblock, null)).initIndependentStat().registerStat();
    public static Achievement potatoesfordays = (new Achievement("achievement.potato", "potato", -1, 2, ModBlocks.comprpotatoblock, null)).initIndependentStat().registerStat();
    public static Achievement foodIsWasted = (new Achievement("achievement.potatooct", "potatooct", 1, 2, (new ItemStack(ModBlocks.comprpotatoblock,1,7).getItem()), potatoesfordays)).setSpecial().registerStat();
    public static Achievement acquireEndgamium = (new Achievement("achievement.endgam", "endgam", 2, 0, ModItems.endgamium, foodIsWasted)).setSpecial().registerStat();
    public static Achievement endgameMining = (new Achievement("achievement.endtool", "endtool", 4, -1, ModItems.endgamiummultitool, acquireEndgamium)).registerStat();
    public static Achievement endgameSword = (new Achievement("achievement.endsword", "endsword", 4, 1, ModItems.endgamiumsword, acquireEndgamium)).registerStat();
    public static Achievement pingEgg = (new Achievement("achievement.pingegg", "pingegg", 6, 3, Items.egg, null)).initIndependentStat().setSpecial();


    public static void init() {
        page1 = new AchievementPage(Reference.MOD_NAME,
                acquireEndgamium,
                compressedStarblock,
                potatoesfordays,
                foodIsWasted,
                endgameMining,
                endgameSword,
                pingEgg);
        AchievementPage.registerAchievementPage(page1);
    }
}
