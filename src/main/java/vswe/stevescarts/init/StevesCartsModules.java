package vswe.stevescarts.init;

import net.minecraft.resources.ResourceLocation;
import vswe.stevescarts.Constants;
import vswe.stevescarts.api.StevesCartsAPI;
import vswe.stevescarts.api.modules.DefaultModuleGroups;
import vswe.stevescarts.api.modules.ModuleType;
import vswe.stevescarts.api.modules.data.ModuleData;
import vswe.stevescarts.api.modules.data.ModuleDataGroup;
import vswe.stevescarts.api.modules.data.ModuleDataHull;
import vswe.stevescarts.helpers.Localization;
import vswe.stevescarts.modules.addons.*;
import vswe.stevescarts.modules.addons.mobdetectors.*;
import vswe.stevescarts.modules.addons.plants.ModuleNetherwart;
import vswe.stevescarts.modules.addons.plants.ModulePlantSize;
import vswe.stevescarts.modules.engines.*;
import vswe.stevescarts.modules.hull.*;
import vswe.stevescarts.modules.realtimers.*;
import vswe.stevescarts.modules.storages.chests.ModuleFrontChest;
import vswe.stevescarts.modules.storages.chests.ModuleInternalStorage;
import vswe.stevescarts.modules.storages.chests.ModuleSideChests;
import vswe.stevescarts.modules.storages.chests.ModuleTopChest;
import vswe.stevescarts.modules.storages.tanks.*;
import vswe.stevescarts.modules.workers.*;
import vswe.stevescarts.modules.workers.tools.*;

public class StevesCartsModules
{
    //HULLS
    public static ModuleData WOODEN_HULL;
    public static ModuleData STANDARD_HULL;
    public static ModuleData REINFORCED_HULL;
    public static ModuleData PUMPKIN_HULL;
    public static ModuleData MACHANICAL_PIG;
    public static ModuleData CREATIVE_HULL;
    public static ModuleData GALGADORIAN_HULL;

    //ENGINES
    public static ModuleData COAL_ENGINE;
    public static ModuleData TINY_COAL_ENGINE;
    public static ModuleData CREATIVE_ENGINE;
    public static ModuleData THERMAL_ENGINE;
    public static ModuleData ADVANCED_THERMAL_ENGINE;

    public static ModuleData SOLAR_ENGINE;
    public static ModuleData BASIC_SOLAR_ENGINE;
    public static ModuleData COMPACT_SOLAR_ENGINE;

    //Chest
    public static ModuleData SIDE_CHESTS;
    public static ModuleData TOP_CHEST;
    public static ModuleData FRONT_CHEST;
    public static ModuleData INTERNAL_STORAGE;

    //Tools
    public static ModuleData TORCH_PLACER;
    public static ModuleData BASIC_DRILL;
    public static ModuleData IRON_DRILL;
    public static ModuleData HARDENED_DRILL;
    public static ModuleData GALGADORIAN_DRILL;

    public static ModuleData RAILER;
    public static ModuleData LARGE_RAILER;

    public static ModuleData BRIDGE_BUILDER;
    public static ModuleData TRACK_REMOVER;

    public static ModuleData BASIC_FARMER;
    public static ModuleData GALGADORIAN_FARMER;

    public static ModuleData BASIC_WOOD_CUTTER;
    public static ModuleData HARDENED_WOOD_CUTTER;
    public static ModuleData GALGADORIAN_WOOD_CUTTER;
    public static ModuleData NETHERITE_WOOD_CUTTER;

    public static ModuleData HYDRATER;
    public static ModuleData FERTILIZER;
    public static ModuleData HEIGHT_CONTROLLER;
    public static ModuleData LIQUID_SENSORS;
    public static ModuleData SEAT;
    public static ModuleData BRAKE;
    public static ModuleData ADVANCED_CONTROL_SYSTEM;
    public static ModuleData SHOOTER;
    public static ModuleData ADVANCED_SHOOTER;
    public static ModuleData CLEANER;
    public static ModuleData DYNAMITE_CARRIER;
    public static ModuleData DIVINE_SHIELD;
    public static ModuleData MELTER;
    public static ModuleData EXTREME_MELTER;
    public static ModuleData INVISIBILITY_CORE;
    public static ModuleData NOTE_SEQUENCER;
    public static ModuleData FREEZER;
    public static ModuleData CAGE;
    public static ModuleData FIREWORK_DISPLAY;

    //Detectors
    public static ModuleData ENTITY_DETECTOR_ANIMAL;
    public static ModuleData ENTITY_DETECTOR_PLAYER;
    public static ModuleData ENTITY_DETECTOR_VILLAGER;
    public static ModuleData ENTITY_DETECTOR_MONSTER;
    public static ModuleData ENTITY_DETECTOR_BAT;

    public static ModuleData CROP_NETHER_WART;
    public static ModuleData INCINERATOR;
    public static ModuleData CLEANER_LIQUID;
    public static ModuleData DRILL_INTELLIGENCE;
    public static ModuleData POWER_OBSERVER;
    public static ModuleData ARCADE;
    public static ModuleData ENCHANTER;
    public static ModuleData ORE_EXTRACTOR;
    public static ModuleData LAWN_MOWER;
    public static ModuleData MILKER;
    public static ModuleData CRAFTER;
    public static ModuleData PLANTER_RANGE_EXTENDER;
    public static ModuleData SMELTER;
    public static ModuleData ADVANCED_CRAFTER;
    public static ModuleData ADVANCED_SMELTER;
    public static ModuleData INFORMATION_PROVIDER;
    public static ModuleData EXPERIENCE_BANK;
    public static ModuleData CAKE_SERVER;

    //TANKS
    public static ModuleData INTERNAL_TANK;
    public static ModuleData SIDE_TANKS;
    public static ModuleData TOP_TANK;
    public static ModuleData ADVANCED_TANK;
    public static ModuleData FRONT_TANK;
    public static ModuleData OPEN_TANK;

    public static ModuleData CHUNK_LOADER;

    public static void init()
    {
        setupDefaultGroups();

        //Hulls
        WOODEN_HULL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "wooden_hull"),
                new ModuleDataHull(new ResourceLocation(Constants.MOD_ID, "wooden_hull"), "Wooden Hull", ModuleWood.class, ModuleType.HULL).setCapacity(50).setEngineMax(1).setAddonMax(0).setComplexityMax(15));

        STANDARD_HULL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "standard_hull"),
                new ModuleDataHull(new ResourceLocation(Constants.MOD_ID, "standard_hull"), "Standard Hull", ModuleStandard.class, ModuleType.HULL).setCapacity(200).setEngineMax(3).setAddonMax(6).setComplexityMax(50));

        REINFORCED_HULL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "reinforced_hull"),
                new ModuleDataHull(new ResourceLocation(Constants.MOD_ID, "reinforced_hull"), "Reinforced Hull", ModuleReinforced.class, ModuleType.HULL).setCapacity(500).setEngineMax(5).setAddonMax(12).setComplexityMax(150));

        PUMPKIN_HULL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "pumpkin_chariot"),
                new ModuleDataHull(new ResourceLocation(Constants.MOD_ID, "pumpkin_chariot"), "Pumpkin Chariot", ModulePumpkin.class, ModuleType.HULL).setCapacity(40).setEngineMax(1).setAddonMax(0).setComplexityMax(15));

        MACHANICAL_PIG = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "mechanical_pig"),
                new ModuleDataHull(new ResourceLocation(Constants.MOD_ID, "mechanical_pig"), "Mechanical Pig", ModulePig.class, ModuleType.HULL).setCapacity(150).setEngineMax(2).setAddonMax(4).setComplexityMax(50).addSide(ModuleData.SIDE.FRONT).addMessage(Localization.MODULE_INFO.PIG_MESSAGE));

        CREATIVE_HULL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "creative_hull"),
                new ModuleDataHull(new ResourceLocation(Constants.MOD_ID, "creative_hull"), "Creative Hull", ModuleCheatHull.class, ModuleType.HULL).setCapacity(10000).setEngineMax(5).setAddonMax(12).setComplexityMax(150));

        GALGADORIAN_HULL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "galgadorian_hull"),
                new ModuleDataHull(new ResourceLocation(Constants.MOD_ID, "galgadorian_hull"), "Galgadorian Hull", ModuleGalgadorian.class, ModuleType.HULL).setCapacity(1000).setEngineMax(5).setAddonMax(12).setComplexityMax(150));

        //Tanks (Need to be before engines so the TANK_GROUP is populated when we copy it for the ADVANCED_THERMAL_ENGINE)
        INTERNAL_TANK = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "internal_sctank"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "internal_sctank"), "Internal SCTank", ModuleInternalTank.class, ModuleType.STORAGE, 37).setAllowDuplicate());
        DefaultModuleGroups.TANK_GROUP.add(INTERNAL_TANK);

        SIDE_TANKS = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "side_tanks"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "side_tanks"), "Side Tanks", ModuleSideTanks.class, ModuleType.STORAGE, 10).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.RIGHT, ModuleData.SIDE.LEFT}));
        DefaultModuleGroups.TANK_GROUP.add(SIDE_TANKS);

        TOP_TANK = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "top_sctank"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "top_sctank"), "Top SCTank", ModuleTopTank.class, ModuleType.STORAGE, 22).addSide(ModuleData.SIDE.TOP));
        DefaultModuleGroups.TANK_GROUP.add(TOP_TANK);

        ADVANCED_TANK = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "advanced_sctank"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "advanced_sctank"), "Advanced SCTank", ModuleAdvancedTank.class, ModuleType.STORAGE, 54).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.CENTER, ModuleData.SIDE.TOP}));
        DefaultModuleGroups.TANK_GROUP.add(ADVANCED_TANK);

        FRONT_TANK = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "front_sctank"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "front_sctank"), "Front SCTank", ModuleFrontTank.class, ModuleType.STORAGE, 15).addSide(ModuleData.SIDE.FRONT));
        DefaultModuleGroups.TANK_GROUP.add(FRONT_TANK);

        OPEN_TANK = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "open_sctank"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "open_sctank"), "Open SCTank", ModuleOpenTank.class, ModuleType.STORAGE, 31).addSide(ModuleData.SIDE.TOP).addMessage(Localization.MODULE_INFO.OPEN_TANK));
        DefaultModuleGroups.TANK_GROUP.add(OPEN_TANK);


        //Engines
        COAL_ENGINE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "coal_engine"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "coal_engine"), "Coal Engine", ModuleCoalStandard.class, ModuleType.ENGINE, 15));
        DefaultModuleGroups.ENGINE_GROUP.add(COAL_ENGINE);

        TINY_COAL_ENGINE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "tiny_coal_engine"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "tiny_coal_engine"), "Tiny Coal Engine", ModuleCoalTiny.class, ModuleType.ENGINE, 2));
        DefaultModuleGroups.ENGINE_GROUP.add(TINY_COAL_ENGINE);

        CREATIVE_ENGINE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "creative_engine"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "creative_engine"), "Creative Engine", ModuleCheatEngine.class, ModuleType.ENGINE, 1));
        DefaultModuleGroups.ENGINE_GROUP.add(CREATIVE_ENGINE);

        THERMAL_ENGINE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "thermal_engine"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "thermal_engine"), "Thermal Engine", ModuleThermalStandard.class, ModuleType.ENGINE, 28).addRequirement(DefaultModuleGroups.TANK_GROUP));
        DefaultModuleGroups.ENGINE_GROUP.add(THERMAL_ENGINE);

        ADVANCED_THERMAL_ENGINE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "advanced_thermal_engine"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "advanced_thermal_engine"), "Advanced Thermal Engine", ModuleThermalAdvanced.class, ModuleType.ENGINE, 58).addRequirement(DefaultModuleGroups.TANK_GROUP.copy(2)));
        DefaultModuleGroups.ENGINE_GROUP.add(ADVANCED_THERMAL_ENGINE);


        //Solar engine
        SOLAR_ENGINE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "solar_engine"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "solar_engine"), "Solar Engine", ModuleSolarStandard.class, ModuleType.ENGINE, 20).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.CENTER, ModuleData.SIDE.TOP}));

        BASIC_SOLAR_ENGINE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "basic_solar_engine"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "basic_solar_engine"), "Basic Solar Engine", ModuleSolarBasic.class, ModuleType.ENGINE, 12).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.CENTER, ModuleData.SIDE.TOP}));

        COMPACT_SOLAR_ENGINE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "compact_solar_engine"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "compact_solar_engine"), "Compact Solar Engine", ModuleSolarCompact.class, ModuleType.ENGINE, 32).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.RIGHT, ModuleData.SIDE.LEFT}));


        //Chest
        SIDE_CHESTS = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "side_chests"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "side_chests"), "Side Chests", ModuleSideChests.class, ModuleType.STORAGE, 3).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.RIGHT, ModuleData.SIDE.LEFT}));

        TOP_CHEST = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "top_chest"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "top_chest"), "Top Chest", ModuleTopChest.class, ModuleType.STORAGE, 6).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.TOP}));

        FRONT_CHEST = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "front_chest"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "front_chest"), "Front Chest", ModuleFrontChest.class, ModuleType.STORAGE, 6).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.FRONT}));

        INTERNAL_STORAGE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "internal_storage"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "internal_storage"), "Internal Storage", ModuleInternalStorage.class, ModuleType.STORAGE, 6));


        //TOOLS
        TORCH_PLACER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "torch_placer"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "torch_placer"), "Torch Placer", ModuleTorch.class, ModuleType.ATTACHMENT, 10)).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.RIGHT, ModuleData.SIDE.LEFT});

        BASIC_DRILL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "basic_drill"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "basic_drill"), "Basic Drill", ModuleDrillDiamond.class, ModuleType.TOOL, 10)).addSide(ModuleData.SIDE.FRONT);
        DefaultModuleGroups.DRILL_GROUP.add(BASIC_DRILL);

        IRON_DRILL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "iron_drill"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "iron_drill"), "Iron Drill", ModuleDrillIron.class, ModuleType.TOOL, 10)).addSide(ModuleData.SIDE.FRONT);
        DefaultModuleGroups.DRILL_GROUP.add(IRON_DRILL);

        HARDENED_DRILL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "hardened_drill"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "hardened_drill"), "Hardened Drill", ModuleDrillHardened.class, ModuleType.TOOL, 45)).addSide(ModuleData.SIDE.FRONT);
        DefaultModuleGroups.DRILL_GROUP.add(HARDENED_DRILL);

        GALGADORIAN_DRILL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "galgadorian_drill"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "galgadorian_drill"), "Galgadorian Drill", ModuleDrillGalgadorian.class, ModuleType.TOOL, 45)).addSide(ModuleData.SIDE.FRONT);
        DefaultModuleGroups.DRILL_GROUP.add(GALGADORIAN_DRILL);

        RAILER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "railer"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "railer"), "Railer", ModuleRailer.class, ModuleType.ATTACHMENT, 3));

        LARGE_RAILER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "large_railer"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "large_railer"), "Large Railer", ModuleRailerLarge.class, ModuleType.ATTACHMENT, 17));

        BRIDGE_BUILDER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "bridge_builder"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "bridge_builder"), "Bridge Builder", ModuleBridge.class, ModuleType.ATTACHMENT, 14));

        TRACK_REMOVER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "track_remover"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "track_remover"), "Track Remover", ModuleRemover.class, ModuleType.TOOL, 8).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.TOP, ModuleData.SIDE.BACK}));

        BASIC_FARMER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "basic_farmer"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "basic_farmer"), "Basic Farmer", ModuleFarmerDiamond.class, ModuleType.TOOL, 36).addSide(ModuleData.SIDE.FRONT));
        DefaultModuleGroups.FARMER_GROUP.add(BASIC_FARMER);

        GALGADORIAN_FARMER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "galgadorian_farmer"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "galgadorian_farmer"), "Galgadorian Farmer", ModuleFarmerGalgadorian.class, ModuleType.TOOL, 55).addSide(ModuleData.SIDE.FRONT));
        DefaultModuleGroups.FARMER_GROUP.add(GALGADORIAN_DRILL);

        BASIC_WOOD_CUTTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "basic_wood_cutter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "basic_wood_cutter"), "Basic Wood Cutter", ModuleWoodcutterDiamond.class, ModuleType.TOOL, 34).addSide(ModuleData.SIDE.FRONT));
        DefaultModuleGroups.WOODCUTTER_GROUP.add(BASIC_WOOD_CUTTER);

        HARDENED_WOOD_CUTTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "hardened_wood_cutter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "hardened_wood_cutter"), "Hardened Wood Cutter", ModuleWoodcutterHardened.class, ModuleType.TOOL, 65).addSide(ModuleData.SIDE.FRONT));
        DefaultModuleGroups.WOODCUTTER_GROUP.add(HARDENED_WOOD_CUTTER);

        GALGADORIAN_WOOD_CUTTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "galgadorian_wood_cutter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "galgadorian_wood_cutter"), "Galgadorian Wood Cutter", ModuleWoodcutterGalgadorian.class, ModuleType.TOOL, 120).addSide(ModuleData.SIDE.FRONT));
        DefaultModuleGroups.WOODCUTTER_GROUP.add(GALGADORIAN_WOOD_CUTTER);

        NETHERITE_WOOD_CUTTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "netherite_wood_cutter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "netherite_wood_cutter"), "Netherite Wood Cutter", ModuleWoodcutterNetherite.class, ModuleType.TOOL, 120).addSide(ModuleData.SIDE.FRONT));
        DefaultModuleGroups.WOODCUTTER_GROUP.add(NETHERITE_WOOD_CUTTER);

        //ADDONS
        HYDRATER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "hydrator"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "hydrator"), "Hydrator", ModuleHydrater.class, ModuleType.ADDON, 6).addRequirement(DefaultModuleGroups.TANK_GROUP));

        FERTILIZER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "fertilizer"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "fertilizer"), "Fertilizer", ModuleFertilizer.class, ModuleType.ADDON, 10));

        HEIGHT_CONTROLLER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "height_controller"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "height_controller"), "Height Controller", ModuleHeightControl.class, ModuleType.ADDON, 20));

        LIQUID_SENSORS = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "liquid_sensors"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "liquid_sensors"), "Liquid Sensors", ModuleLiquidSensors.class, ModuleType.ADDON, 27).addRequirement(DefaultModuleGroups.DRILL_GROUP));

        SEAT = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "seat"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "seat"), "Seat", ModuleSeat.class, ModuleType.ADDON, 3).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.CENTER, ModuleData.SIDE.TOP}));

        BRAKE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "brake_handle"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "brake_handle"), "Brake Handle", ModuleBrake.class, ModuleType.ATTACHMENT, 12).addSide(ModuleData.SIDE.RIGHT).addParent(SEAT));

        ADVANCED_CONTROL_SYSTEM = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "advanced_control_system"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "advanced_control_system"), "Advanced Control System", ModuleAdvControl.class, ModuleType.ATTACHMENT, 38).addSide(ModuleData.SIDE.RIGHT).addParent(SEAT));

        SHOOTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "shooter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "shooter"), "Shooter", ModuleShooter.class, ModuleType.ATTACHMENT, 15).addSide(ModuleData.SIDE.TOP));

        ADVANCED_SHOOTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "advanced_shooter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "advanced_shooter"), "Advanced Shooter", ModuleShooterAdv.class, ModuleType.ATTACHMENT, 50).addSide(ModuleData.SIDE.TOP).addRequirement(DefaultModuleGroups.ENTITY_DETECTOR_GROUP));

        ENTITY_DETECTOR_ANIMAL = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "entity_detector_animal"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "entity_detector_animal"), "Entity Detector: Animal", ModuleAnimal.class, ModuleType.ADDON, 1));
        DefaultModuleGroups.ENTITY_DETECTOR_GROUP.add(ENTITY_DETECTOR_ANIMAL);

        ENTITY_DETECTOR_PLAYER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "entity_detector_player"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "entity_detector_player"), "Entity Detector: Player", ModulePlayer.class, ModuleType.ADDON, 7));
        DefaultModuleGroups.ENTITY_DETECTOR_GROUP.add(ENTITY_DETECTOR_PLAYER);

        ENTITY_DETECTOR_VILLAGER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "entity_detector_villager"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "entity_detector_villager"), "Entity Detector: Villager", ModuleVillager.class, ModuleType.ADDON, 1));
        DefaultModuleGroups.ENTITY_DETECTOR_GROUP.add(ENTITY_DETECTOR_VILLAGER);

        ENTITY_DETECTOR_MONSTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "entity_detector_monster"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "entity_detector_monster"), "Entity Detector: Monster", ModuleMonster.class, ModuleType.ADDON, 1));
        DefaultModuleGroups.ENTITY_DETECTOR_GROUP.add(ENTITY_DETECTOR_MONSTER);

        ENTITY_DETECTOR_BAT = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "entity_detector_bat"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "entity_detector_bat"), "Entity Detector: Bat", ModuleBat.class, ModuleType.ADDON, 1));
        DefaultModuleGroups.ENTITY_DETECTOR_GROUP.add(ENTITY_DETECTOR_BAT);

        CLEANER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "cleaning_machine"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "cleaning_machine"), "Cleaning Machine", ModuleCleaner.class, ModuleType.ADDON, 23).addSide(ModuleData.SIDE.CENTER));

        DYNAMITE_CARRIER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "dynamite_carrier"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "dynamite_carrier"), "Dynamite Carrier", ModuleDynamite.class, ModuleType.ADDON, 3).addSide(ModuleData.SIDE.TOP));

        DIVINE_SHIELD = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "divine_shield"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "divine_shield"), "Divine Shield", ModuleShield.class, ModuleType.ADDON, 60));

        MELTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "melter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "melter"), "Melter", ModuleMelter.class, ModuleType.ADDON, 10));

        EXTREME_MELTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "extreme_melter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "extreme_melter"), "Extreme Melter", ModuleMelterExtreme.class, ModuleType.ADDON, 19));

        INVISIBILITY_CORE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "invisibility_core"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "invisibility_core"), "Invisibility Core", ModuleInvisible.class, ModuleType.ADDON, 21));

        NOTE_SEQUENCER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "note_sequencer"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "note_sequencer"), "Note Sequencer", ModuleNote.class, ModuleType.ADDON, 30)).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.RIGHT, ModuleData.SIDE.LEFT});

        FREEZER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "freezer"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "freezer"), "Freezer", ModuleSnowCannon.class, ModuleType.ADDON, 24));

        CAGE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "cage"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "cage"), "Cage", ModuleCage.class, ModuleType.ADDON, 7).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.TOP, ModuleData.SIDE.CENTER}));

        CROP_NETHER_WART = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "crop_nether_wart"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "crop_nether_wart"), "Crop: Nether Wart", ModuleNetherwart.class, ModuleType.ADDON, 20).addRequirement(DefaultModuleGroups.FARMER_GROUP));

        FIREWORK_DISPLAY = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "firework_display"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "firework_display"), "Firework display", ModuleFirework.class, ModuleType.ADDON, 45));

        INCINERATOR = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "incinerator"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "incinerator"), "Incinerator", ModuleIncinerator.class, ModuleType.ADDON, 23).addRequirement(DefaultModuleGroups.TANK_GROUP).addRequirement(DefaultModuleGroups.DRILL_GROUP));

        CLEANER_LIQUID = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "liquid_cleaner"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "liquid_cleaner"), "Liquid Cleaner", ModuleLiquidDrainer.class, ModuleType.ADDON, 30).addSide(ModuleData.SIDE.CENTER).addParent(LIQUID_SENSORS).addRequirement(DefaultModuleGroups.TANK_GROUP));

        DRILL_INTELLIGENCE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "drill_intelligence"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "drill_intelligence"), "Drill Intelligence", ModuleDrillIntelligence.class, ModuleType.ADDON, 21).addRequirement(DefaultModuleGroups.DRILL_GROUP));

        POWER_OBSERVER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "power_observer"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "power_observer"), "Power Observer", ModulePowerObserver.class, ModuleType.ADDON, 12).addRequirement(DefaultModuleGroups.ENGINE_GROUP));

        ARCADE = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "steves_arcade"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "steves_arcade"), "Steve's Arcade", ModuleArcade.class, ModuleType.ADDON, 10).addParent(SEAT));

        ENCHANTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "enchanter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "enchanter"), "Enchanter", ModuleEnchants.class, ModuleType.ADDON, 72).addRequirement(DefaultModuleGroups.ENGINE_GROUP));

        ORE_EXTRACTOR = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "ore_extractor"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "ore_extractor"), "Ore Extractor", ModuleOreTracker.class, ModuleType.ADDON, 80).addRequirement(DefaultModuleGroups.DRILL_GROUP));

        LAWN_MOWER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "lawn_mower"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "lawn_mower"), "Lawn Mower", ModuleFlowerRemover.class, ModuleType.ADDON, 38).addSides(new ModuleData.SIDE[]{ModuleData.SIDE.RIGHT, ModuleData.SIDE.LEFT}));

        MILKER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "milker"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "milker"), "Milker", ModuleMilker.class, ModuleType.ADDON, 26).addParent(CAGE));

        CRAFTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "crafter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "crafter"), "Crafter", ModuleCrafter.class, ModuleType.ADDON, 22).setAllowDuplicate());

        PLANTER_RANGE_EXTENDER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "planter_range_extender"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "planter_range_extender"), "Planter Range Extender", ModulePlantSize.class, ModuleType.ADDON, 20).addRequirement(DefaultModuleGroups.WOODCUTTER_GROUP));

        SMELTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "smelter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "smelter"), "Smelter", ModuleSmelter.class, ModuleType.ADDON, 22).setAllowDuplicate());

        ADVANCED_CRAFTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "advanced_crafter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "advanced_crafter"), "Advanced Crafter", ModuleCrafterAdv.class, ModuleType.ADDON, 42).setAllowDuplicate());

        ADVANCED_SMELTER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "advanced_smelter"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "advanced_smelter"), "Advanced Smelter", ModuleSmelterAdv.class, ModuleType.ADDON, 42).setAllowDuplicate());

        INFORMATION_PROVIDER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "information_provider"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "information_provider"), "Information Provider", ModuleLabel.class, ModuleType.ADDON, 12));

        EXPERIENCE_BANK = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "experience_bank"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "experience_bank"), "Experience Bank", ModuleExperience.class, ModuleType.ADDON, 36));

        CAKE_SERVER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "cake_server"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "cake_server"), "Cake Server", ModuleCakeServer.class, ModuleType.ADDON, 10).addSide(ModuleData.SIDE.TOP).addMessage(Localization.MODULE_INFO.ALPHA_MESSAGE));

        CHUNK_LOADER = StevesCartsAPI.registerModule(new ResourceLocation(Constants.MOD_ID, "chunk_loader"),
                new ModuleData(new ResourceLocation(Constants.MOD_ID, "chunk_loader"), "Chunk Loader", ModuleChunkLoader.class, ModuleType.ADDON, 10));
    }

    public static void setupDefaultGroups()
    {
        DefaultModuleGroups.ENGINE_GROUP = new ModuleDataGroup(Localization.MODULE_INFO.ENGINE_GROUP);
        DefaultModuleGroups.DRILL_GROUP = new ModuleDataGroup(Localization.MODULE_INFO.DRILL_GROUP);
        DefaultModuleGroups.FARMER_GROUP = new ModuleDataGroup(Localization.MODULE_INFO.FARMER_GROUP);
        DefaultModuleGroups.WOODCUTTER_GROUP = new ModuleDataGroup(Localization.MODULE_INFO.CUTTER_GROUP);
        DefaultModuleGroups.TANK_GROUP = new ModuleDataGroup(Localization.MODULE_INFO.TANK_GROUP);
        DefaultModuleGroups.TOOL_GROUP = ModuleDataGroup.getCombinedGroup(Localization.MODULE_INFO.TOOL_GROUP, DefaultModuleGroups.DRILL_GROUP, DefaultModuleGroups.WOODCUTTER_GROUP);
        DefaultModuleGroups.ENTITY_DETECTOR_GROUP = new ModuleDataGroup(Localization.MODULE_INFO.ENTITY_GROUP);
    }
}
