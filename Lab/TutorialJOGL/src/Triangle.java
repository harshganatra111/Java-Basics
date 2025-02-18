import java.awt.DisplayMode;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import com.jogamp.opengl.util.FPSAnimator;
public class Triangle implements GLEventListener {
public static DisplayMode dm, dm_old;
private GLU glu = new GLU();
private float rquad = 0.0f;
public void display( GLAutoDrawable drawable ) {
final GL2 gl = drawable.getGL().getGL2();
gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
gl.glLoadIdentity();
gl.glTranslatef( 0f, 0f, -5.0f ); 
gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f); 
gl.glBegin(GL2.GL_POLYGON); 
gl.glColor3f(1f,0f,0f); 
gl.glVertex3f(1.0f, 1.0f, -1.0f); 
gl.glVertex3f( -1.0f, 1.0f, -1.0f); 
gl.glVertex3f( -1.0f, 1.0f, 1.0f );
gl.glColor3f( 0f,1f,0f ); 
gl.glVertex3f( -1.0f, -1.0f, 1.0f ); 
gl.glColor3f( 0f,0f,1f ); 
gl.glVertex3f( -1.0f, 1.0f, 1.0f ); 
gl.glEnd();
rquad -= 0.15f;
}
public void dispose( GLAutoDrawable drawable ) {
}
public void init( GLAutoDrawable drawable ) {
final GL2 gl = drawable.getGL().getGL2();
gl.glShadeModel( GL2.GL_SMOOTH );
gl.glClearColor( 0f, 0f, 0f, 0f );
gl.glClearDepth( 1.0f );
gl.glEnable( GL2.GL_DEPTH_TEST );
gl.glDepthFunc( GL2.GL_LEQUAL );
}
public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {
final GL2 gl = drawable.getGL().getGL2();
if( height <= 0 )
height = 1;
final float h = ( float ) width / ( float ) height;
gl.glViewport( 0, 0, width, height );
gl.glMatrixMode( GL2.GL_PROJECTION );
gl.glLoadIdentity();
glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
gl.glMatrixMode( GL2.GL_MODELVIEW );
gl.glLoadIdentity();
}
public static void main( String[] args ) {
final GLProfile profile = GLProfile.get( GLProfile.GL2 );
GLCapabilities capabilities = new GLCapabilities( profile );
final GLCanvas glcanvas = new GLCanvas( capabilities );
Triangle cube = new Triangle();
glcanvas.addGLEventListener( cube );
glcanvas.setSize( 400, 400 );
final JFrame frame = new JFrame ( " Multicolored cube" );
frame.getContentPane().add( glcanvas );
frame.setSize( frame.getContentPane().getPreferredSize() );
frame.setVisible( true );
final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true);
animator.start();
}
}