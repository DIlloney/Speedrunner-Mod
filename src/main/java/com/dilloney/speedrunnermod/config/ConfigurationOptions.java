package com.dilloney.speedrunnermod.config;

import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "speedrunnermod")
public class ConfigurationOptions {

    public int difficulty = 1;

    public boolean makeStructuresMoreCommon = true;
    public boolean modifiedWorldGeneration = true;
    public boolean combineFortressAndBastion = false;
    public boolean modifiedBlockHardness = true;
    public boolean modifiedLootTables = true;

    public boolean fog = true;
    public boolean defaultParticles = true;
    public boolean netherBiomeParticles = true;

    public boolean killGhastUponFireball = false;
    public boolean doomMode = false;
    public boolean iCarusMode = false;
}
