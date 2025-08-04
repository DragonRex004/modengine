package de.dragonrex.modengine;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.LibFunction;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ModEngine {
    private final Globals lua;

    public ModEngine() {
        this.lua = JsePlatform.standardGlobals();
    }

    public void registerFunction(String name, LibFunction function) {
        lua.set(name, function);
    }

    public void runScript(String luaCode) {
        LuaValue chunk = lua.load(luaCode);
        chunk.call();
    }

    public void runScript(File file) {
        try {
            LuaValue chunk = lua.load(new FileReader(file), file.getName());
            chunk.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllMods(File modFolder) {
        File[] scripts = modFolder.listFiles((dir, name) -> name.endsWith(".lua"));
        if (scripts != null) {
            for (File script : scripts) {
                runScript(script);
            }
        }
    }

    public void setGlobal(String name, LuaValue value) {
        lua.set(name, value);
    }

    public LuaValue getGlobal(String name) {
        return lua.get(name);
    }

    public Globals getLua() {
        return lua;
    }
}
