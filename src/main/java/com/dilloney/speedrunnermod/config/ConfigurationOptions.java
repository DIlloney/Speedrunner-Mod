package com.dilloney.speedrunnermod.config;

import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "speedrunnermod")
public class ConfigurationOptions {

    public int difficulty = 1;

    public boolean makeStructuresMoreCommon = true;
    public boolean modifiedWorldGeneration = true;
    public boolean modifiedBlockHardness = true;
    public boolean modifiedLootTables = true;

    public boolean killGhastUponFireball = false;
    public boolean doomMode = false;
    public boolean iCarusMode = false;

    public boolean fog = true;
    public boolean particles = true;
}
