package Assignment2;

import org.objectweb.asm.Label;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

public class GenEcho {
	public static void main(String args[]) throws IOException{
        
        ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "Echo", null, "java/lang/Object", null);

        //Create the class
		{
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(RETURN);
			mv.visitMaxs(0,0);
			mv.visitEnd();
		}
		
		MethodVisitor methodVisitor=cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        methodVisitor.visitCode();
        
        //establishes scanner reference and duplicates it
        methodVisitor.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
        methodVisitor.visitInsn(Opcodes.DUP);
        
        //gets system.in, stores Scanner reference in 1
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
        methodVisitor.visitVarInsn(Opcodes.ASTORE, 1);
        
        //scans next line
        methodVisitor.visitVarInsn(Opcodes.ALOAD,1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(Opcodes.ASTORE, 2);
        
        //store and print string
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 2);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(Ljava/lang/String;)V", false);
        
        methodVisitor.visitInsn(Opcodes.RETURN);
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
        
        cw.visitEnd();
        
        //save bytecode into disk
		FileOutputStream out=new FileOutputStream("./Echo.class");
		out.write(cw.toByteArray());
		out.close();
	}
}
