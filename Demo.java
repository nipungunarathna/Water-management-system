import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
interface Device{
	public void update(int waterLevel);
	}
class Alarm extends JFrame implements Device{
	private JLabel alarmLabel;
	Alarm(){
		setTitle("Alarm");
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		alarmLabel=new JLabel();
		alarmLabel.setHorizontalAlignment(JLabel.CENTER);
		alarmLabel.setFont(new Font("",1,30));
		add("Center",alarmLabel);
		}
	public void update(int waterLevel){
		if(waterLevel>=50){
			alarmLabel.setText("ON");
			}
		else{
		alarmLabel.setText("OFF");
		}
		}
	
	}
class Sensor{
	private int waterLevel;
	private ControlRoom cRoom;
	Sensor(ControlRoom cRoom){
		this.cRoom=cRoom;
		}
	public void setWaterLevel(int waterLevel){
		if (this.waterLevel!=waterLevel){
				this.waterLevel=waterLevel;
				cRoom.updateWaterLevel(waterLevel);
			}
		}
	}
class ControlRoom{
	private ArrayList<Device> list;
	private int waterLevel;
	
	ControlRoom(ArrayList<Device> list){
		this.list=list;
		}
	public void updateWaterLevel(int waterLevel){
		 this.waterLevel=waterLevel;
		 notifyDevice();
		 }
	public void notifyDevice(){
		list.get(0).update(waterLevel);
		}
	
	}
class WaterTank extends JFrame{
	private JSlider slider;
	private Sensor sensor;
	private ControlRoom cRoom;
	WaterTank(ControlRoom cRoom){
		setTitle("Water Tank");
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		this.cRoom=cRoom;
		sensor=new Sensor(cRoom);
		slider=new JSlider();
		add("Center",slider);
		
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int value=slider.getValue();
				sensor.setWaterLevel(value);
				}
			});
		}
	}
class Demo{
	public static void main(String args[]){
		ArrayList <Device> list=new ArrayList<>();
		list.add(new Alarm());
		ControlRoom cRoom=new ControlRoom(list);
		new WaterTank(cRoom);
		}
	}
