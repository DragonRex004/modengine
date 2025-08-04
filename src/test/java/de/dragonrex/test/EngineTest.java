package de.dragonrex.test;

import de.dragonrex.modengine.ModEngine;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

import java.io.File;

public class EngineTest {
    private ModEngine engine;

    public EngineTest() {
        this.engine = new ModEngine();

        this.engine.registerFunction("spawnEntity", new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue type, LuaValue pos) {
                System.out.println("Spawn: " + type + " at " + pos);
                return LuaValue.valueOf("entityId_123");
            }
        });

        this.engine.runScript(new File(getClass().getResource("/test/script.lua").getFile()));
    }

    public static void main(String[] args) {
        new EngineTest();
    }
}
