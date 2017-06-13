package fh.dortmund.logic.entity;

public class Lecture {
	
	
	private String name;
	private int countYes;
	private int countNo;
	private boolean open;
	
	
	public Lecture(String name) {
		this.name=name;
		
		countYes=0;
		countNo=0;
		open=false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCountYes() {
		return countYes;
	}
	public void setCountYes(int countYes) {
		this.countYes = countYes;
	}
	public int getCountNo() {
		return countNo;
	}
	public void setCountNo(int countNo) {
		this.countNo = countNo;
	}
	
	public int getAllCounts()
	{
		return countNo+countYes;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void restart() {
		open=true;
		setCountNo(0);
		setCountYes(0);
	}

	public int[] getPoll() {
		
		int[] poll= new int[3];
		poll[0]=getCountYes();
		poll[1]=getCountNo();
		poll[2]=getAllCounts();
		
		return poll;
		
		
		
	}
	

}
