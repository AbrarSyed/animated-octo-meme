package me.abrarsyed.animatedoctomeme.representation;

import java.util.List;

import me.abrarsyed.animatedoctomeme.representation.types.Type;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

public class ClassNode extends ClassVisitor
{
    public String               name;
    public List<ClassParameter> parameters;
    public Type                 superType;
    public ClassVariant         type;
    public int                  access;
    public List<String>         interfaces;

    public ClassNode()
    {
        super(Opcodes.ASM5);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
    {
        this.name = name;
        this.access = access;
        
        // TODO: the rest
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible)
    {
        // TODO Auto-generated method stub
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible)
    {
        // TODO Auto-generated method stub
        return super.visitTypeAnnotation(typeRef, typePath, desc, visible);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access)
    {
        // TODO Auto-generated method stub
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
    {
        // TODO Auto-generated method stub
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
    {
        // TODO Auto-generated method stub
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd()
    {
        // TODO Auto-generated method stub
        super.visitEnd();
    }

}
