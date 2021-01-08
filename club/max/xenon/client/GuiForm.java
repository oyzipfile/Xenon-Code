// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class GuiForm extends JFrame
{
    JPanel rootPanel;
    
    public GuiForm() {
        this.add(this.rootPanel);
        this.setTitle("Xenon Loader");
        this.setSize(400, 500);
    }
}
