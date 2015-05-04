package me.abrarsyed.animatedoctomeme;

import java.util.concurrent.Callable;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConvertTask implements Callable<String>
{
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final byte[] bytes;
    
    public ConvertTask(byte[] bytes)
    {
        super();
        this.bytes = bytes;
    }
    
    @Override
    public String call() throws Exception
    {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        
        // TODO: Cchange to my own CLassNode
        
        reader.accept(node, ClassReader.SKIP_CODE + ClassReader.SKIP_FRAMES + ClassReader.SKIP_DEBUG);
        
        return GSON.toJson(node);
    }  
}
