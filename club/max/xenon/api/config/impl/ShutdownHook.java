// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.config.impl;

import club.max.xenon.XenonClient;

public class ShutdownHook extends Thread
{
    @Override
    public void run() {
        XenonClient.configManager.saveModules();
    }
}
