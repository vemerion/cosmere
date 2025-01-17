/*
 * File created ~ 27 - 4 - 2021 ~ Leaf
 */

/*
 * File created ~ 27 - 4 - 2021 ~ Leaf
 *
 *
 * Code obtained from xReliquary @see {https://raw.githubusercontent.com/P3pp3rF1y/Reliquary/c18065d5874c106ef1c1befe9cbde4a4b754a05b/src/main/java/xreliquary/util/XpHelper.java}
 *
 * who in turn got it from:
 *
 * Code originally from EnderIO @see {https://github.com/SleepyTrousers/EnderIO/blob/master/src/main/java/crazypants/enderio/xp/XpUtil.java}
 *
 */

package leaf.cosmere.utils.helpers;

/**
 *
 */
public class XPHelper
{
    private static final int RATIO = 20;

    public static int liquidToExperience(int liquid) {
        return liquid / RATIO;
    }

    public static int experienceToLiquid(int xp) {
        return xp * RATIO;
    }

    public static int getExperienceForLevel(int level) {
        if(level == 0) {
            return 0;
        }
        if(level > 0 && level < 16) {
            return level * (12 + level * 2) / 2;
        } else if(level > 15 && level < 31) {
            return (level - 15) * (69 + (level - 15) * 5) / 2 + 315;
        } else {
            return (level - 30) * (215 + (level - 30) * 9) / 2 + 1395;
        }
    }

    public static int getExperienceLimitOnLevel(int level) {
        return level >= 30 ? 112 + (level - 30) * 9 : (level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2);
    }

    public static int getLevelForExperience(int experience) {
        int i = 0;
        while(getExperienceForLevel(i) <= experience) {
            i++;
        }
        return i - 1;
    }

    public static int durabilityToXp(int durability) {
        return durability / 2;
    }

    public static int xpToDurability(int xp) {
        return xp * 2;
    }
}
