// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.util.misc;

import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

public class WriteFile
{
    File file;
    BufferedWriter out;
    
    public WriteFile(File file) throws IOException {
        file = file;
        this.out = new BufferedWriter(new FileWriter(file));
    }
    
    public void println(final String str) {
        try {
            this.out.write(str);
            this.out.write("\r\n");
            this.out.flush();
        }
        catch (Exception ex) {}
    }
    
    public void createFile(final boolean dir) {
        try {
            if (!dir) {
                this.file.createNewFile();
            }
            else {
                this.file.mkdirs();
            }
        }
        catch (Exception ex) {}
    }
    
    public void flush() {
        try {
            this.out.flush();
        }
        catch (Exception ex) {}
    }
    
    public void close() {
        try {
            this.out.close();
        }
        catch (Exception ex) {}
    }
}
