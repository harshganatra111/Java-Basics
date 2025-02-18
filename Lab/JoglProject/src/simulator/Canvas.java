package simulator;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.*;
import java.io.*;
import java.util.*;
public class Canvas extends GLCanvas implements GLEventListener {
	FPSAnimator animator;
	 GLU glu;
	Texture texture_obj;
	 ArrayList<Moons> moon_obj;
	 Jupit jup_obj;
	float tempo = 0.0f, Speed = 0.0f, Elevation = 0.0f,jup_rad = 12f,UPx = 0.0f, UPy = 1.0f, UPz = 0.0f,Posx = 0.0f, Posy = 0.0f, Posz = -20.0f;
	public Canvas(int width, int height, GLCapabilities capabilities) {
		setSize(800, 800);
		addGLEventListener(this);
	}
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		glu = new GLU();
		moon_obj = new ArrayList<>();
		animator = new FPSAnimator(this, 100);//change speed
		animator.start();
		texture_obj = getTextu(gl, "res\\black.png");
		jup_obj = new Jupit(gl, glu, getTextu(gl, "res\\jupiter.jpg"));
		Moons moon1 = new Moons(gl, glu, getTextu(gl, "res\\moon1.jpg"), 1.2f, jup_rad+2f, 2.56f);
		Moons moon2 = new Moons(gl, glu, getTextu(gl, "res\\moon2.jpg"), 0.7f, jup_rad + 12f, 3.56f);
		Moons moon3 = new Moons(gl, glu, getTextu(gl, "res\\moon3.jpg"), 0.25f, jup_rad + 28f, 4.56f);
		Moons moon4 = new Moons(gl, glu, getTextu(gl, "res\\moon4.jpg"), 0.3f, jup_rad + 40f, 3.56f);
		Moons moon5 = new Moons(gl, glu, getTextu(gl, "res\\moon5.jpg"), 0.3f, jup_rad + 60f, 4.56f);
		Moons moon6 = new Moons(gl, glu, getTextu(gl, "res\\moon6.jpg"), 0.25f, jup_rad + 75f, 4.56f);
		Moons moon7 = new Moons(gl, glu, getTextu(gl, "res\\moon7.jpg"), 0.275f, jup_rad + 100f, 4.56f);
		Moons moon8 = new Moons(gl, glu, getTextu(gl, "res\\white.png"), 0.275f, jup_rad + 130f, 5.8f);
		moon_obj.add(moon1);
		moon_obj.add(moon2);
		moon_obj.add(moon3);
		moon_obj.add(moon4);
		moon_obj.add(moon5);
		moon_obj.add(moon6);
		moon_obj.add(moon7);
		moon_obj.add(moon8);
	}
	@Override
	public void dispose(GLAutoDrawable glAutoDrawable) {
	}
	@Override
	public void display(GLAutoDrawable glAutoDrawable) {
		final GL2 gl = glAutoDrawable.getGL().getGL2();
		setPos(gl, 300);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		jup_obj.display();
		for (Moons p : moon_obj)
			p.display();
	}
	    Texture getTextu(GL2 gl, String fileName) {
		InputStream stream = null;
		Texture tex = null;
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		try {
			stream = new FileInputStream(new File(fileName));
			TextureData data = TextureIO.newTextureData(gl.getGLProfile(), stream, false, extension);
			tex = TextureIO.newTexture(data);
		} catch (Exception e) {
			System.err.println("Exception Found");
			e.printStackTrace();
		}		return tex;
	}
	
	 void setPos(GL2 gl, float distance) {
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		float widthHeightRatio = (float) getWidth() / (float) getHeight();
		glu.gluPerspective(45, widthHeightRatio, 1, 1000);
		glu.gluLookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		
	}
	public void aimPos(GL2 gl, GLU glu) {
		gl.glLoadIdentity();
	}
	
}