import java.io.*;
import java.util.*;
//��Ӱ����
enum MovieType implements Serializable{    
	COMEDY("ϲ��Ƭ"),SEIENCEFICTION("�ƻ�Ƭ");
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


//��Ӱ
class Movie implements Serializable{
	String movieName; //��Ӱ����
	MovieType mt;	//��Ӱ����

	Movie(){

	}

	Movie(String movieName,MovieType mt){
		this.movieName = movieName;
		this.mt = mt;
	}
	
}
//��Ӱ��ӳʱ���
class MovieSchedule implements Serializable{  
	String theatre;  //��ӳ�� 
	String time;  //��ӳʱ�䣺������ ��ʱ��

	MovieSchedule(){

	}

	MovieSchedule(String theatre,String time){
		this.theatre = theatre;
		this.time = time;
	}
}

//��λ
class Seat implements Serializable{  
	int row;	//��
	int column;	//����
	Seat(){

	}

	Seat(int row,int column){
		this.row = row;
		this.column = column;
	}
}
//��ӰƱ
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
	
	public void printTicket(){ //��ӡƱ����ϸ��Ϣ 
		System.out.println(movie.movieName);
		System.out.println("���ͣ�" + movie.mt.getTypename());
		System.out.println("��ӳ����" + ms.theatre);
		System.out.println("ʱ�䣺" + ms.time);
		System.out.println("��λ��" + seat.row + "��" + seat.column + "��");
		System.out.println("***********************************************");
	}
}
public class TicketText {
	public static void main(String[] args)  {
		//--1 �����Ӱ����ö�ٶ���
		try {
			MovieType comdey = MovieType.COMEDY;
			MovieType sciencefiction = MovieType.SEIENCEFICTION;

			//--2 �����Ӱ����
			Movie mv1 = new Movie("����ʯͷ", comdey);
			Movie mv2 = new Movie("2012", sciencefiction);

			//--3 �����ӳ���ζ���
			MovieSchedule ms1 = new MovieSchedule("��ӳ��1", "2010��7��12�� 20ʱ20��");
			MovieSchedule ms2 = new MovieSchedule("��ӳ��2", "2010��7��14�� 19ʱ40��");

			//--4 ������λ����
			Seat s1 = new Seat(10, 12);
			Seat s2 = new Seat(10, 13);
			Seat s3 = new Seat(8, 8);
			Seat s4 = new Seat(8, 9);

			//--5 ���ݵ�Ӱ����ӳ���Ρ���λ�����ӰƱ����
			Ticket t1 = new Ticket(mv1, ms1, s1);
			Ticket t2 = new Ticket(mv1, ms1, s2);
			Ticket t3 = new Ticket(mv2, ms2, s3);
			Ticket t4 = new Ticket(mv2, ms2, s4);

			//--6 ���л�����ӰƱд��dat�������ļ�

			//�������л����������
			File file = new File("Ticket.dat");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			t1.printTicket();
			t2.printTicket();
			t3.printTicket();
			t4.printTicket();

			//���л�
			oos.writeObject(t1);
			oos.writeObject(t2);
			oos.writeObject(t3);
			oos.writeObject(t4);

			//�ر���
			oos.close();
			fos.close();
			System.out.println();
			System.out.println("---------------�����л����--------------------");
			System.out.println();
			//--7 �����л���dat��������ĵ�ӰƱ������

			//���������л���������
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			//�����л���ȡ����
			Ticket t11 = (Ticket) ois.readObject();
			Ticket t22 = (Ticket) ois.readObject();
			Ticket t33 = (Ticket) ois.readObject();
			Ticket t44 = (Ticket) ois.readObject();

			t11.printTicket();
			t22.printTicket();
			t33.printTicket();
			t44.printTicket();

			//�ر���
			ois.close();
			fos.close();
		}catch (ClassNotFoundException ex){
			ex.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}


	}

}
