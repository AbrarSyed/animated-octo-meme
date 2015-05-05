package me.abrarsyed.animatedoctomeme.representation;

import java.util.List;

import me.abrarsyed.animatedoctomeme.representation.types.ClassType;
import me.abrarsyed.animatedoctomeme.representation.types.Type;
import me.abrarsyed.animatedoctomeme.util.TypeParser;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class ClassNode extends ClassVisitor
{
    public String               name;
    public List<Parameter>      parameters;
    public ClassType            superType;
    public ClassVariant         type;
    public int                  access;
    public List<ClassType>      interfaces;

    public List<AnnotationNode> annotations = Lists.newArrayList();
    public List<FieldNode>      fields      = Lists.newArrayList();

    public ClassNode()
    {
        super(Opcodes.ASM5);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
    {
        this.name = name;
        this.access = access;

        if (Strings.isNullOrEmpty(signature))
        {
            if (!superType.equals("java/lang/Object")) // otherwise leave it null
            {
                superType = new ClassType(superName);
            }

            this.interfaces = Lists.newArrayListWithCapacity(interfaces.length);
            for (String s : interfaces)
                this.interfaces.add(new ClassType(s));
        }
        else
        {
            // TODO: PARSE SIGNATURE! FOR GENERICS
        }
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean runtimeVisible)
    {
        AnnotationNode annot = new AnnotationNode(TypeParser.parseClassType(desc), runtimeVisible);
        annotations.add(annot);
        return annot;
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean runtimeVisible)
    {
        // TODO: these are more complicated than I expected
        return super.visitTypeAnnotation(typeRef, typePath, desc, runtimeVisible);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
    {
        Type type = TypeParser.parseType(desc);
     
        if (!Strings.isNullOrEmpty(signature))
        {
            // TODO: PARSE SIGNATURE! FOR GENERICS
        }
        
        FieldNode node = new FieldNode(type, name, access);
        fields.add(node);
        return node;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
    {
        // TODO Auto-generated method stub
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
    

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access)
    {
        // TODO Auto-generated method stub
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public void visitEnd()
    {
        // TODO Auto-generated method stub
        super.visitEnd();
    }

}
