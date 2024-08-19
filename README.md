# PrisonGameFabric
A remake of `PrisonButBad` made in fabric.

To setup an enviornment, please: 
- create a server with the properties in `example_server.properties` and 
- delete your `world` folder to generate a void world.
- run `/gamerule doMobSpawning false`
- run `/gamerule doMobLoot false`
- run `/gamerule doDaylightCycle false` >> this is because we manually update time and don't want the client doing it themselves, or it looks wonky

## Creating a Prison/Upgrade (WIP)
This will only cover the basics to get a working enviornment of a PrisonGameFabric Prison and Upgrade.

An example prison is provided under `src/main/resources/data/prisongamefabric/(prisons/upgrades)`.
Datapacks should use `prisons` and `upgrades`.


### Prisons

Required Fields:

- "name": string
- "itemIcon": identifier, item (eg; `minecraft:dirt`)
- "cellLocations": [] of PrisonLocations
- "upgrades": List of Upgrades **with Map Specifics**
- "wardenSpawn": PrisonLocation
- "guardSpawn": PrisonLocation
- "computerTeleport": PrisonLocation; yaw and pitch is important!!
#### Extra note on "computerTeleport": to set up a computer, name an item "CreateComputer" in an anvil, then place it in an item frame and click it.

### PrisonLocation
- "x": double
- "y": double
- "z": double


### Upgrade with map Specifics


too lazy to do this right now, here's an extract from `antarctica.json`

**EVERYTHING HERE EXCEPT FOR `upgrade` IS OPTIONAL**

```{
    "upgrade": "prisongamefabric:foodcourt",
    "onLock": "prisongamefabric:ant_cafe_lock",
    "onUnlock": "prisongamefabric:ant_cafe_unlock",
    "safeZone": {
        "zoneStart": {
            "x": -43,
            "y": 22,
            "z": 62
        },
        "zoneEnd": {
            "x": -29,
            "y": 15,
            "z": 76
        }
    }
```

### Upgrades
will do later just look at `foodcourt.json`. this does not include `onGlobalLock` and `onGlobalUnlock` which are identifiers to .mcfunction files