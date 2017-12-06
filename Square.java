package ARPG;


import java.awt.Rectangle;
import java.util.ArrayList;

public class Square extends Object
{
	
    private int width;
    private int height;
    private boolean gounded=false;
    Rectangle rect = new Rectangle();
    private double velx;
    private double vely;
    /**
     * Constructor for objects of class Square
     */
    public Square()
    {
    	width=50;
    	height=50;
        setX(300);
        setY(0);
        rect.setSize(width, height);
        rect.setBounds(getX(), getY(), width, height);
    }
    
    public void jump(){
    	setVely(-10);
    }
    
    public void gravity(){
    	//setVely(getVely()+.1);
    }
    
    public void friction(){
    	if(getVelx()!=0){
    		if(getVelx()>0){
    			setVelx(getVelx()-.1);
    		}
    		else{
    			setVelx(getVelx()+.1);
    		}
    	}
    }
    
    public boolean getGounded(){
    	return gounded;
    }
    
    public void setGounded(boolean b){
    	gounded=b;
    }
    
    public int getWidth(){
    	return width;
    }
    public int getHeight(){
    	return height;
    }
    public double getVelx(){
        return velx;
    }
    public void setVelx(double x){
        velx=x;
    }
    
    public double getVely(){
        return vely;
    }
    public void setVely(double y){
        vely=y;
    }
    
    public void move(int x, int y){
    	rect.translate(x, y);
    	setX(getX()+x);
    	setY(getY()+y);
    }
    
    public boolean intersectsBlock(Block b){
    	if(b.getRect().intersects(rect)){
    		return true;
    	}
    	//gounded=false;
    	return false;
    }
    
    public boolean[] intersectsBlocks(ArrayList<Block> list){
    	boolean[] blist=new boolean[list.size()];
    	for(int i=0;i<list.size();i++){  
    		Block b=list.get(i);
    		if(intersectsBlock(b)){
    			blist[i]=true;
    			if(b.getY()<(int) rect.getMaxY()){
    				setVely(0);
    			}
    			else if(b.getX()<(int) rect.getMaxX()){
    				setVelx(0);
    			}
    			else if((b.getY()+b.getHeight())>(int)rect.getMinY()){
    				setVely(0);
    			}
    			else if((b.getX()+b.getWidth())>(int)rect.getMinX()){
    				setVelx(0);
    			}
    		}
    		else{
    			blist[i]=false;
    		}
    	}
    	return blist;
    }
    
    /**
     * Adjusts the position of the square so that it doesn't go into the blocks
     * @param b
     */
	public void fixPosition(Block b){
    	int bottomPos=(int) rect.getMaxY();
    	int rightPos=(int) rect.getMaxX();
    	int topPos=(int) rect.getMinY();
    	int leftPos=(int) (rect.getMinX());
    	int xscale=(int)(rect.getWidth()-(rect.getWidth()*.6));
    	int yscale=(int)(rect.getHeight()-(rect.getHeight()*.6));
    	if((b.getY()<bottomPos)&&(rect.getMaxY()-b.getY()<b.getMaxX()-rect.getX())||rect.contains(b.getX(),b.getY(), b.getWidth(), 1))
    	{// add to all and remove top to work||b.getrect().contains((rect.getMinX()+xscale),(rect.getMaxY()), width-xscale, 1)
    		int diff =b.getY()-bottomPos;
    		setVely(0);
    		move(0, diff);
    	}
    	else if((b.getX()<rightPos)&&b.getRect().contains((rect.getMaxX()),(rect.getMinY()+yscale), 1, height-yscale)
    			||rect.contains(b.getX(),b.getY(), 1, b.getHeight()))//((b.getMaxX()-rect.getX())<(b.getMaxY()-rect.getY()))
    	{
    		int diff =b.getX()-rightPos;
    		setVelx(0);
    		move(diff, 0);
    	}
    	else if(((b.getY()+b.getHeight())>topPos)&&b.getRect().contains((rect.getMinX()+xscale),(rect.getMinY()), width-xscale, 1)
    			||rect.contains(b.getX(),b.getY()+b.getHeight(), b.getWidth(), 1))
    	{
    		int diff = (b.getY()+b.getHeight())-topPos;
    		setVely(0);
    		move(0,diff);
    	}
    	else if(((b.getX()+b.getWidth())>leftPos)&&b.getRect().contains((rect.getMinX()),(rect.getMinY()+yscale), 1, height-xscale)
    			||rect.contains(b.getX()+b.getWidth(),b.getY(), 1, b.getHeight()))
    	{
    		int diff = (b.getX()+b.getWidth())-leftPos;
    		setVelx(0);
    		move(diff,0);
    	}
    	else{
    	gounded=false;
    	}
    }




    
}

