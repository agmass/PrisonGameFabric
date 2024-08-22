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

### Before we start:
Some things will require **Translations.** Place a file name after the language you want to use (most commonly `en_us.json`) under `lang/` in your datapack.

Your language file should look like a json, like this:
````
{
    "key": "value"
} 
````

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

- "upgrade": Identifier that leads to the main Upgrade data. View the section below to make an upgrade.
- (Optional) "onLock": Identifier to a mcfunction called when this upgrade is locked on this map.
- (Optional) "onUnlock": Ditto, for unlock.
- (Optional) "safeZone": PrisonZone

### Upgrades
- "name": Translated String
- "itemIcon": identifier, item (eg; `minecraft:dirt`)
- "price": integer

### Shop Signs
You can make a custom Shop Sign people can use to buy things.

- "name": string.
- "price": integer,
- "purchase": identifier to an mcfunction, run when the player has enough money.
- "fail": identifier to an mcfunction, run when the player does not have enough money. Use `prisongamefabric:nothing` if you do not want anything to happen.
