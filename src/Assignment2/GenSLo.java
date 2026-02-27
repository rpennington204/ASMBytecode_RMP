package Assignment2;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

@SuppressWarnings("unused")
public class GenSLo {
	public static void main(String args[]) throws IOException{
        
        ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "SLo", null, "java/lang/Object", null);

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
        
        //push 500 onto the stack, store in 1 and 2
        methodVisitor.visitLdcInsn(500L);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);
        
        //push 200 onto the stack, store in 3 and 4
        methodVisitor.visitLdcInsn(200L);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);
        
        //Load the Longs and subtract them, store result in 5 and 6
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
        methodVisitor.visitInsn(Opcodes.LSUB);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 5);
        
        //prints result of 500 - 300
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 5);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(J)V", false);
        
      //push 37.5 onto the stack, store in 7 and 8
        methodVisitor.visitLdcInsn((Double) 37.5);
        methodVisitor.visitVarInsn(Opcodes.DSTORE, 6);
        
        //push 200 onto the stack, store in 9 and 10
        methodVisitor.visitLdcInsn((Double) 12.5);
        methodVisitor.visitVarInsn(Opcodes.DSTORE, 8);
        
        //Load the Longs and subtract them, store result in 11 and 12
        methodVisitor.visitVarInsn(Opcodes.DLOAD, 6);
        methodVisitor.visitVarInsn(Opcodes.DLOAD, 8);
        methodVisitor.visitInsn(Opcodes.DSUB);
        methodVisitor.visitVarInsn(Opcodes.DSTORE, 11);
        
        //prints result of 500 - 300
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(Opcodes.DLOAD, 11);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  "println", "(D)V", false);
        
        methodVisitor.visitInsn(Opcodes.RETURN);
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
        
        cw.visitEnd();
        
        //save bytecode into disk
		FileOutputStream out=new FileOutputStream("./SLo.class");
		out.write(cw.toByteArray());
		out.close();
	}
}
