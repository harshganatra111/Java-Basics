import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.JFrame;
public class Cart implements GLEventListener
{
	private float tx=0;
	public void dispose(GLAutoDrawable args0) {
	}
	public void reshape(GLAutoDrawable args0, int arg1, int arg2, int arg3,
	int arg4) {
	}
	public void init(GLAutoDrawable gld) {
	GL2 gl = gld.getGL().getGL2();
	GLU glu = new GLU();
	gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	gl.glViewport(- 500, -250, 500, 250);
	gl.glMatrixMode(GL2.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluOrtho2D(-250.0, 350.0, -150.0, 250.0);
	}
	public void display(GLAutoDrawable draw) 
	{
	GL2 gl = draw.getGL().getGL2();
	gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
	gl.glColor3f(0, 1, 1);
	gl.glPushMatrix();
	gl.glTranslatef(-tx,0,1);
	tx-=1f;
	if(tx==-200){
	tx=0;
	}
	drawShape(gl);
	gl.glColor3f(.7f, .6f, 0);
	drawCircle(gl,-30,-25,30);
	drawCircle(gl,30,-25,30);
	gl.glPopMatrix();
	}
	void drawShape(GL2 gl) {
	gl.glBegin(GL2.GL_POLYGON);
	gl.glVertex3f(-70f, -25f, 0f);
	gl.glVertex3f(70f, -25f, 0f);
	gl.glVertex3f(70f, 25f, 0f);
	gl.glVertex3f(-70f, 25f, 0f);
	gl.glEnd();
	}
	public void drawCircle(GL2 gl,float x1,float y1,float r )
	{
	gl.glPointSize(2.0f);
	gl.glBegin(GL2.GL_POINTS);
	for(int i=0;i<200;i++)
	{
	gl.glVertex2d(x1+r*Math.cos(i*2*3.14/100), y1+r*Math.sin(i*2*3.14/100));
	}
	gl.glEnd();
	}
	public static void main(String[] args)
	{
	final GLProfile profile = GLProfile.get(GLProfile.GL2);
	GLCapabilities capabilities = new GLCapabilities(profile);
	final GLCanvas glcanvas = new GLCanvas(capabilities);
	final FPSAnimator animator=new FPSAnimator(glcanvas,100,true);
	animator.start();
	Cart b5 = new Cart();
	glcanvas.addGLEventListener(b5);
	glcanvas.setSize(400, 400);
	final JFrame frame = new JFrame("Basic frame");
	frame.add(glcanvas);
	frame.setSize(640, 480);
	frame.setVisible(true);
	}
	}