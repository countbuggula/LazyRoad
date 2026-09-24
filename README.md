# LazyRoad (Modernized)
A Minecraft plugin that allows you to quickly build complex roads, bridges, and tunnels effortlessly. Just type a command, start walking, and the road will gracefully build itself along your path, adhering to terrain changes!

Originally created by `creadri`, this project has been fully modernized for modern Minecraft natively supporting the Purpur/Paper API.

## Features
* **Live Generation**: Roads assemble themselves dynamically as you walk.
* **Smart Terrain Adapting**: The plugin naturally slopes and traces the landscape via `maxGradient` rules.
* **Tunnels & Excavation**: Tunnel mode neatly bores precise bounding boxes through mountains with an optional `/lr drops` toggle to naturally harvest the excavated resources!
* **Bridges & Suspensions**: Bridge mode actively locks your elevation, building out ahead of you so you can safely traverse ravines while natively drawing suspension columns/pillars down to the landscape.
* **JSON Templates**: No more binary `.ser` files. Roads and pillars are now entirely JSON-based. Want to design a new road template? You can easily edit or share the structures in the `plugins/LazyRoad/roads` folder.
* **Directional Block Support**: Modern directional items like Stairs, Lanterns, Logs, and Fences naturally rotate and connect themselves as the road changes directions dynamically.
* **Infinite Undo**: Made a mistake? `/lr undo` is completely re-engineered to accurately restore landscapes perfectly backwards regardless of road length.

## Commands
* `/lr <roadName>` - Begin painting a standard road.
* `/lt <roadName>` - Begin tunneling (smoothly paves and bores through terrain).
* `/lb <roadName> <pillarName>` - Build a bridge locking your altitude, spawning pillars underneath!
* `/lr stop` - Stop building.
* `/lr undo` - Revert the most recently built structure.
* `/lr straight` - Toggle forcing the road layout perfectly straight (prevents snaking).
* `/lr drops` - Toggle tunneling excavation drops.
* `/lr reload` - Reload all JSON setups.

## Forward Compatibility
LazyRoad has been re-engineered to use dynamic string parsing for block generation rather than hardcoded enums. Because templates are built entirely from strings (like minecraft:sulfur), the plugin natively supports new Minecraft block versions without ever needing an internal plugin update or SDK recompile! Just enter the correct namespaced ID in your JSON template (e.g., minecraft:sulfur), and the server will natively evaluate it.

## JSON Templating
LazyRoad will extract 6 Pillars and 10 Road templates by default. A template JSON maps blocks mathematically exactly like standard Minecraft `minecraft:blockid[state=value]`. You can easily clone any JSON to design your own architectures!

## Credits
Created by creadri, with architecture patches by VeraLapsa and Z5T1. 

*Special thanks to Google DeepMind\'s Gemini for assisting with the 2026 code modernization and JSON architecture migration of this project.*
