import java.io.*;
import java.util.*;
//电影类型
enum MovieType implements Serializable{    
	COMEDY("喜剧片"),SEIENCEFICTION("科幻片");
	String typename;
	MovieType(){
	}

	MovieType(String typename){
		this.typename = typename;
	}

	public String getTypename() {
		return typename;
	}
}


//电影
class Movie implements Serializable{
	String movieName; //电影名字
	MovieType mt;	//电影类型

	Movie(){

	}

	Movie(String movieName,MovieType mt){
		this.movieName = movieName;
		this.mt = mt;
	}
	
}
//电影放映时间段
class MovieSchedule implements Serializable{  
	String theatre;  //放映厅 
	String time;  //放映时间：年月日 、时分

	MovieSchedule(){

	}

	MovieSchedule(String theatre,String time){
		this.theatre = theatre;
		this.time = time;
	}
}

//座位
class Seat implements Serializable{  
	int row;	//排
	int column;	//座座
	Seat(){

	}

	Seat(int row,int column){
		this.row = row;
		this.column = column;
	}
}
//电影票
class Ticket implements Serializable{
	Movie movie;
	MovieSchedule  ms;
	Seat seat;
	Ticket(){

	}

	Ticket(Movie movie,MovieSchedule ms,Seat seat){
		this.movie = movie;
		this.ms = ms;
		this.seat = seat;
	}
	
	public void printTicket(){ //打印票的详细信息 
		System.out.println(movie.movieName);
		System.out.println("类型：" + movie.mt.getTypename());
		System.out.println("放映厅：" + ms.theatre);
		System.out.println("时间：" + ms.time);
		System.out.println("座位：" + seat.row + "排" + seat.column + "座");
		System.out.println("***********************************************");
	}
}
public class TicketText {
	public static void main(String[] args)  {
		//--1 构造电影类型枚举对象
		try {
			MovieType comdey = MovieType.COMEDY;
			MovieType sciencefiction = MovieType.SEIENCEFICTION;

			//--2 构造电影对象
			Movie mv1 = new Movie("疯狂的石头", comdey);
			Movie mv2 = new Movie("2012", sciencefiction);

			//--3 构造放映场次对象
			MovieSchedule ms1 = new MovieSchedule("放映厅1", "2010年7月12日 20时20分");
			MovieSchedule ms2 = new MovieSchedule("放映厅2", "2010年7月14日 19时40分");

			//--4 构造座位对象
			Seat s1 = new Seat(10, 12);
			Seat s2 = new Seat(10, 13);
			Seat s3 = new Seat(8, 8);
			Seat s4 = new Seat(8, 9);

			//--5 根据电影、放映场次、座位构造电影票对象
			Ticket t1 = new Ticket(mv1, ms1, s1);
			Ticket t2 = new Ticket(mv1, ms1, s2);
			Ticket t3 = new Ticket(mv2, ms2, s3);
			Ticket t4 = new Ticket(mv2, ms2, s4);

			//--6 序列化将电影票写入dat二进制文件

			//构造序列化输出流对象
			File file = new File("Ticket.dat");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			t1.printTicket();
			t2.printTicket();
			t3.printTicket();
			t4.printTicket();

			//序列化
			oos.writeObject(t1);
			oos.writeObject(t2);
			oos.writeObject(t3);
			oos.writeObject(t4);

			//关闭流
			oos.close();
			fos.close();
			System.out.println();
			System.out.println("---------------反序列化结果--------------------");
			System.out.println();
			//--7 反序列化将dat二进制里的电影票读出来

			//创建反序列化的输入流
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			//反序列化读取数据
			Ticket t11 = (Ticket) ois.readObject();
			Ticket t22 = (Ticket) ois.readObject();
			Ticket t33 = (Ticket) ois.readObject();
			Ticket t44 = (Ticket) ois.readObject();

			t11.printTicket();
			t22.printTicket();
			t33.printTicket();
			t44.printTicket();

			//关闭流
			ois.close();
			fos.close();
		}catch (ClassNotFoundException ex){
			ex.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}


	}

}
