# 🔧 LuaModEngine – Lightweight Lua Modding for Java Projects

**LuaModEngine** is a simple and powerful library that enables Lua scripting support in any Java application. It’s perfect for adding modding support to your games or tools — letting users write custom behavior in Lua, safely and easily.

---

## ✨ Features

- ✅ Lightweight and dependency-free (based on LuaJ – pure Java)
- 🧠 Register your own Java functions and objects to be called from Lua
- 📜 Run Lua scripts from files or inline strings
- 📂 Load mod scripts from a folder
- 🔌 Optional `LuaMod` interface for modular plugin-like architecture

---

## 📦 Installation

### Gradle
```kotlin
dependencies {
    implementation("com.yourgroup:luamodengine:1.0.0") // Replace with real coordinates
}
```
### Maven
```xml
<dependency>
  <groupId>com.yourgroup</groupId>
  <artifactId>luamodengine</artifactId>
  <version>1.0.0</version>
</dependency>
```
Requires Java 8+ and no native libraries.

🚀 Getting Started Initialize the engine:
```java
ModdingEngine engine = new ModdingEngine();
```
Register a custom Java function:
```java
engine.registerFunction("sayHello", new OneArgFunction() {
    @Override
    public LuaValue call(LuaValue name) {
        System.out.println("Hello, " + name.tojstring() + "!");
        return LuaValue.NIL;
    }
});
```
Run inline Lua code:
```java
engine.runScript("sayHello('world')");
```
Or load a Lua script file:
```java
engine.runScript(new File("mods/my_script.lua"));
```
📁 Mod Folder Support You can automatically load all .lua scripts in a folder:
```java
engine.loadAllMods(new File("mods/"));
```
🔌 Modular Plugin Interface (Optional) For more structured modding:
```java
public class MyMod implements LuaMod {
    @Override
    public void register(ModdingEngine engine) {
        engine.registerFunction("spawn", new OneArgFunction() {
            public LuaValue call(LuaValue what) {
                System.out.println("Spawning: " + what);
                return LuaValue.valueOf("id_001");
            }
        });
    }
}
```
Then use:

```java
engine.registerMod(new MyMod());
```
🧪 Example Lua Script
```lua
local id = spawn("dragon")
print("Spawned entity with ID: " .. id)
```
🛡️ Security Consideration
This engine does not sandbox Lua by default. Be cautious when executing third-party scripts and consider running them in a restricted environment or process.

🤝 Contributing
Contributions, bug reports and feature requests are welcome!

Fork this repo

Create a branch

Submit a PR

📄 License
MIT – free for personal and commercial use.

🧠 Inspired by
LuaJ

Classic modding frameworks for Minecraft and other games

💬 Contact
Feel free to reach out via GitHub Issues or open a discussion for feature ideas.